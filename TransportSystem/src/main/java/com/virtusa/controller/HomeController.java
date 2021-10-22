package com.virtusa.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;
import com.virtusa.export.ExportPdf;
import com.virtusa.export.TruckExcelExporter;
import com.virtusa.export.UserExcelExporter;
import com.virtusa.model.AuthRequest;
import com.virtusa.model.AvailableTrucks;
import com.virtusa.model.BookedTrucks;
import com.virtusa.model.QueriesAndResponses;
import com.virtusa.model.TruckModel;
import com.virtusa.model.Trucks;
import com.virtusa.model.User;
import com.virtusa.repository.AvailableTruckRepository;
import com.virtusa.repository.BookedTrucksRepository;
import com.virtusa.repository.QueriesAndResponsesRepository;
import com.virtusa.repository.TruckModelRepository;
import com.virtusa.repository.TruckRepository;
import com.virtusa.repository.UserRepository;
import com.virtusa.util.JwtUtil;

@Controller
public class HomeController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepo;

	@Autowired
	TruckRepository truckRepo;

	@Autowired
	QueriesAndResponsesRepository qarRepo;

	@Autowired
	TruckModelRepository truckModelRepo;

	@Autowired
	BookedTrucksRepository bookedTrucksRepo;

	@Autowired
	AvailableTruckRepository availableRepo;

	@RequestMapping("/login")
	public String home() {

		return "login.jsp";
	}

	@PostMapping("/signUp")
	public String Signup(User user) {

		if (userRepo.existsByUserName(user.getEmail()) || userRepo.existsByUserName(user.getUserName())) {
			return ("login.jsp");
		}
		userRepo.save(user);
		return ("login.jsp");
	}
	
	@PostMapping("/checkUserNameAvailability")
	@ResponseBody
	public String checkUserNameAvailability(@RequestParam("userName") String userName) {

		if (userRepo.existsByUserName(userName)) {
			return ("User Name Already Exists");
		}
		
		return ("User Name Available");
	}
	
	@PostMapping("/checkTruckIdAvailability")
	@ResponseBody
	public String checkTruckNameAvailability(@RequestParam("tid") int tid) {

		if (truckRepo.existsById(tid)) {
			return ("Truck ID Already Exists");
		}
		
		return ("Truck ID Available");
	}

	@PostMapping("/checkTruckNumberAvailability")
	@ResponseBody
	public String checkTruckNumberAvailability(@RequestParam("truckNum") String truckNum) {

		if (truckRepo.existsByTruckNum(truckNum)) {
			return ("Truck Number Already Exists");
		}
		
		return ("Truck Number Available");
	}

	
	@PostMapping("/checkUserIdAvailability")
	@ResponseBody
	public String checkUserIdAvailability(@RequestParam("id") int id) {

		if (userRepo.existsById(id)) {
			return ("ID Already Exists");
		}
		
		return ("ID Available");
	}

	@PostMapping("/authenticate")
	public String generateToken(HttpServletRequest request, AuthRequest authRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			System.out.println(ex);
			return("login.jsp");
			
		}
		String token = jwtUtil.generateToken(authRequest.getUserName());
		request.getSession().setAttribute("token", token);
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (user.getRole().equals("USER")) {
			return("userpage.jsp");
		}
		
		else if (user.getRole().equals("MANAGER")) {
			return("manager.jsp");
		}
		
		else{
			return("admin.jsp");
		}
		
	}

	@PostMapping(value = "/searchManager", produces = "application/json")
	@ResponseBody
	public User searchManager(@RequestParam("token") String token, @RequestParam("userName") String userName) {
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (user.getRole().equals("ADMIN")) {
			System.out.println(userName);
			return (userRepo.findByUserNameAndRole(userName, "MANAGER"));
		} else {
			System.out.println(user.getUserName());
			user.setUserName("Access denied");
			return (user);
		}
	}

	@PostMapping(value = "/searchAllManager", produces = "application/json")
	@ResponseBody
	public List<User> searchAllManager(@RequestParam("token") String token) {
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (user.getRole().equals("ADMIN")) {
			return (userRepo.findAllByRole("MANAGER"));
		} else {
			ArrayList<User> list = new ArrayList<User>();
			User newUser = new User();
			newUser.setUserName("Access Denied");
			list.add(newUser);
			return (list);
		}
	}

	@RequestMapping(value = "/updateManager",method= RequestMethod.GET)
	@ResponseBody
	public String updateManager(@RequestParam("token") String token, @RequestParam("userName") String userName,
			User user) {
		user.setId(userRepo.findByUserName(userName).getId());
		user.setRole("MANAGER");
		int updateFlag = 0;
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN")) {
			if (userRepo.existsByUserName(user.getUserName())) {
				System.out.println("value exists");
				if (user.getRole().equals("MANAGER")) {
					userRepo.save(user);
					System.out.println(userRepo.findByUserName(user.getUserName()));
					updateFlag = 1;
				} else {
					return ("Manager Not Found");
				}
			}
			System.out.println(user);
			if (updateFlag == 1) {
				return ("Manager Details Updated Successfully");
			} else {
				return ("Manager Not Found");
			}
		} else {
			return ("Access Denied");
		}
	}

	@RequestMapping(value = "/deleteManager")
	@ResponseBody
	public String deleteManager(@RequestParam("token") String token, @RequestParam("userName") String userName,
			User user) {
		int updateFlag = 0;
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN")) {
			if (userRepo.existsByUserName(userName)) {
				User u =userRepo.findByUserName(userName);
				userRepo.delete(u);
				updateFlag = 1;
			}
			if (updateFlag == 1) {
				return ("deleted seccessfully");
			} else {
				return ("not found");
			}

		} else {
			return ("Access denied");
		}
	}

	@PostMapping(value = "/createManager")
	@ResponseBody
	public String createManager(@RequestParam("token") String token, User user) {
		System.out.println("\n-----------------------\n" + user + "\n------------------------------");
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN")) {
			user.setRole("MANAGER");
			if (user.getRole().equals("MANAGER")) {
				if (!userRepo.existsByUserName(user.getUserName())) {
					if(!userRepo.existsById(user.getId())) {
					userRepo.save(user);
					System.out.println("\n##############################" + user + "\n############################");
					System.out.println(user.getUserName());
					System.out.println(userRepo.findByUserNameAndRole(user.getUserName(), "MANAGER"));
					return ("details updated successfully");}
					else {return ("manager already exists");}
				} else {
					return ("manager already exists");
				}
			} else {
				return ("Access denied");
			}

		} else {
			return ("Access denied");
		}
	}

	/*-----------------TRUCKS----------------*/

	@PostMapping(value="/addTruck")
    @ResponseBody
	public String addTrucks(@RequestParam("token") String token,Trucks truck) {
    	User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("MANAGER")||currentUser.getRole().equals("ADMIN")) {
			if(!truckRepo.existsByTruckNum(truck.getTruckNum())) {
				if(!truckRepo.existsByTid(truck.getTid())) {
				truck.setLocation(currentUser.getBranch());
    	//if(user.getRole().equals("MANAGER")||user.getRole().equals("ADMIN")) {
    	TruckModel truckModel=new TruckModel();
    	AvailableTrucks availTruck = new AvailableTrucks();
    	if(truckModelRepo.existsByModelName(truck.getModel())) {
    		truckModel.setTid(truckModelRepo.findByModelName(truck.getModel()).getTid());
    		truckModel.setModelName(truck.getModel());
        	truckModel.setNumberOfTrucks(truckModelRepo.findByModelName(truck.getModel()).getNumberOfTrucks()+1);
        	availTruck.setTid(truck.getTid());
        	availTruck.setTruckModel(truckModel.getModelName());
        	availTruck.setTruckNum(truck.getTruckNum());
        	}
    	else {
    		truckModel.setTid(truck.getTid());
    		truckModel.setModelName(truck.getModel());
        	truckModel.setNumberOfTrucks(1);
        	availTruck.setTid(truck.getTid());
        	availTruck.setTruckModel(truckModel.getModelName());
        	availTruck.setTruckNum(truck.getTruckNum());
    		}
    
    	truckModelRepo.save(truckModel);
    	truckRepo.save(truck);
    	availableRepo.save(availTruck);
		return (truckRepo.findByTruckNum(truck.getTruckNum()).getTruckNum()+"Added successfully ");
		}
				else {return("Truck already exists");}}
			else {
			return("Truck already exists");}
		}
    	else {
    	return("Access denied");}
	}
    
    @RequestMapping(value="/updateTruck")
    @ResponseBody
	public String updateTruck(@RequestParam("token") String token,@RequestParam("truckNum") String truckNum,Trucks truck) {
    	int updateFlag=0;
    	truck.setTid(truckRepo.findByTruckNum(truckNum).getTid());
    	
    	User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
    	if(user.getRole().equals("MANAGER")||user.getRole().equals("ADMIN")) {
    		
    		System.out.print(truckRepo.existsByTruckNum(truckNum));
    		
		if (truckRepo.existsByTruckNum(truckNum)) {
			truck.setStatus(true);
			truck.setLocation(truck.getLocation());
			truck.setModel(truck.getModel());
			//if model is changed , change in TruckModel
			if(!(truckRepo.findByTruckNum(truckNum).getModel().equals(truck.getModel()))) {
				//decrease count in old model
				TruckModel truckModel = truckModelRepo.findByModelName(truckRepo.findByTruckNum(truckNum).getModel());
				truckModel.setNumberOfTrucks(truckModel.getNumberOfTrucks()-1);
				truckModelRepo.save(truckModel);
				//check and increment in new model name
				if(truckModelRepo.existsByModelName(truck.getModel())) {
					truckModel.setTid(truckModelRepo.findByModelName(truck.getModel()).getTid());
			    	truckModel.setModelName(truck.getModel());
			    	truckModel.setNumberOfTrucks(truckModelRepo.findByModelName(truck.getModel()).getNumberOfTrucks()+1);
			    	truckModelRepo.save(truckModel);
			    	
			    	if(bookedTrucksRepo.existsByTruckNum(truckNum)) {
						
						BookedTrucks bookTruck = bookedTrucksRepo.findByTruckNum(truckNum);
						bookTruck.setTruckModel(truck.getModel());
						bookedTrucksRepo.save(bookTruck);
					}
			    	
			    	if(availableRepo.existsByTruckNum(truckNum)) {
						
						AvailableTrucks availableTruck = availableRepo.findByTruckNum(truckNum);
						availableTruck.setTruckModel(truck.getModel());
						availableRepo.save(availableTruck);
					}
				}
				else {
					truck.setTruckNum("You are trying to create a new model. Delete this truck in the delete window and add newly");
				}
			}	
			truckRepo.save(truck);
			updateFlag=1;
		}
		if(updateFlag==1) {
			return truckRepo.findByTruckNum(truckNum).getTruckNum();
		}
		else {
			
			return("No truck found");
			}
		}
    	else {
		return("Access Denied");}
    	}
	@RequestMapping(value = "/deleteTruck")
	@ResponseBody
	public String deleteTruck(@RequestParam("token") String token, @RequestParam("truckNum") String truckNum) {
		int updateFlag = 0;
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("MANAGER")||currentUser.getRole().equals("ADMIN")) {
			Trucks truck = truckRepo.findByTruckNum(truckNum);
			if (truckRepo.existsByTruckNum(truckNum)) {
				String mtruck = truckRepo.findByTruckNum(truckNum).getModel();
				if(truckModelRepo.findByModelName(mtruck).getNumberOfTrucks()==1) {
					truckModelRepo.delete(truckModelRepo.findByModelName(mtruck));
				}
				else {
					TruckModel tm = truckModelRepo.findByModelName(mtruck);
					tm.setNumberOfTrucks(truckModelRepo.findByModelName(mtruck).getNumberOfTrucks()-1);
					truckModelRepo.save(tm);
				}
				truckRepo.delete(truck);
				//availableRepo.delete(availableRepo.findByTruckNum(truckNum));
				updateFlag = 1;
			}
			if (updateFlag == 1) {
				return ("deleted seccessfully");
			} else {
				return ("not found");
			}

		} else {
			return ("Access denied");
		}
	}
    
    @RequestMapping(value="/allTrucks",produces="application/json")
    @ResponseBody
	public List<Trucks> getTrucks(@RequestParam("token") String token) {
    	User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
    	if(user.getRole().equals("MANAGER")||user.getRole().equals("ADMIN")) {
    		
    	if(user.getRole().equals("MANAGER")) {
		return truckRepo.findAllByLocation(user.getBranch());}
    	
    	else {
    		return truckRepo.findAll();}
    	
	}
    	else { ArrayList<Trucks> list=new ArrayList<Trucks>();
    	Trucks truck=new Trucks();
    	truck.setModel("Access Denied");
    	list.add(truck);
    	return(list);}
		
    }
  
    @PostMapping("/findTruck")
    @ResponseBody
    public Trucks findTruck(@RequestParam("token") String token,@RequestParam("truckNum") String truckNum) {
    	User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
    	if(user.getRole().equals("MANAGER")||user.getRole().equals("ADMIN")) {
    	System.out.println(truckNum);
		return (truckRepo.findByTruckNum(truckNum));
	}
    	else {Trucks truck=new Trucks();
    	truck.setTruckNum("Access Denied");
    	return(truck);}
    
}

	@PostMapping("/truckService")
	@ResponseBody
	public Trucks truckService(@RequestParam("token") String token) {
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (user.getRole().equals("MANAGER") || user.getRole().equals("ADMIN") || user.getRole().equals("USER")) {
			Trucks truck = new Trucks();
			truck.setModel("chennai to mumbai on 25/10/2021");
			truck.setLocation("mumbai to chennai on 30/10/2021");
			return (truck);
		} else {
			Trucks truck = new Trucks();
			truck.setModel("Access Denied");
			return (truck);
		}
	}

	@RequestMapping("/askQueries")
	@ResponseBody
	public String askQueries(@RequestParam("token") String token, @RequestParam("query") String query) {
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (user.getRole().equals("USER")) {
			QueriesAndResponses qar = new QueriesAndResponses();
			qar.setUserName(user.getUserName());
			qar.setQuery(query);
			qar.setResponse("");
			qar.setFlag(true);
			qarRepo.save(qar);
			return ("query updated");
		} else {
			return ("Access denied");
		}
	}

	@RequestMapping(value="/viewQueries",produces="application/json")
	@ResponseBody
	public List<QueriesAndResponses> viewQueries(@RequestParam("token") String token) {
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("MANAGER")) {
			return (qarRepo.findAllByFlag(true));
		} else {
			ArrayList<QueriesAndResponses> list = new ArrayList<QueriesAndResponses>();
			QueriesAndResponses qar = new QueriesAndResponses();
			qar.setUserName("Access Denied");
			list.add(qar);
			return (list);
		}
	}

	@RequestMapping("/answerQueries")
	@ResponseBody
	public String answerQueries(@RequestParam("token") String token, List<QueriesAndResponses> qarList) {
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("MANAGER")) {
			for (QueriesAndResponses i : qarList) {
				QueriesAndResponses forLoopQar = qarRepo.findBySerialNumber(i.getSerialNumber());
				forLoopQar.setResponse(i.getResponse());
				forLoopQar.setFlag(false);
				qarRepo.save(forLoopQar);
			}
			return ("updated");
		} else {
			return ("Access denied");
		}
	}
	
	@RequestMapping("/answerQuery")
	@ResponseBody
	public String answerQuery(@RequestParam("token") String token,@RequestParam("serialNumber") int serialNumber,@RequestParam("response")String response) {
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("MANAGER")) {
			QueriesAndResponses qar = qarRepo.findBySerialNumber(serialNumber);
			qar.setResponse(response);
			qar.setFlag(false);
			qarRepo.save(qar);
			return ("updated");
		} else {
			return ("Access denied");
		}
	}

	@RequestMapping(value="/viewResponses",produces="application/json")
	@ResponseBody
	public List<QueriesAndResponses> viewResponses(@RequestParam("token") String token) {
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("USER")) {
			return (qarRepo.findAllByUserName(currentUser.getUserName()));
		} else {
			ArrayList<QueriesAndResponses> list = new ArrayList<QueriesAndResponses>();
			QueriesAndResponses qar = new QueriesAndResponses();
			qar.setUserName("Access Denied");
			list.add(qar);
			return (list);
		}
	}
	@RequestMapping("/bookTruck")
	@ResponseBody
	public String bookTruck(@RequestParam("token") String token, @RequestParam("model") String model,
			@RequestParam("date") String date) {
		
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("USER")) {
			
			List<BookedTrucks> bookedList = bookedTrucksRepo.findAllByTruckModel(model);
			System.out.println(bookedList.size()+"is the size of booked list");
			List<BookedTrucks> bookedList2 = new ArrayList<BookedTrucks>(); 
			for( BookedTrucks i : bookedList) {
				if(!i.getBookedDate().equals(date)) {
					bookedList2.add(i);
				}
			}
			
			TruckModel truckModel = truckModelRepo.findByModelName(model);
			if (bookedList2.size() >= truckModel.getNumberOfTrucks()) {
				return("All Trucks in this model are booked");
			}

			else {
				
				System.out.println(model);
				if (availableRepo.existsByTruckModel(model)) {
					System.out.println("equals model");
					BookedTrucks bookTruck = new BookedTrucks();
					bookTruck.setTruckModel(model);
					List<AvailableTrucks> availableList=availableRepo.findAllByTruckModel(model);
					for(AvailableTrucks i : availableList) {
					bookTruck.setTid(i.getTid());
					bookTruck.setTruckNum(i.getTruckNum());
					bookTruck.setBookedDate(date);
					bookTruck.setBookedBy(currentUser.getUserName());
					availableRepo.delete(availableRepo.findByTruckNum(i.getTruckNum()));
					bookedTrucksRepo.save(bookTruck);
					//break;
					}
					return("Your Truck is Booked and here is your truck number :"+bookTruck.getTruckNum());
				} else {
					return("No Trucks are available in this model");
				}

			}
		}
		else {
			return("Access Denied");
		}
	}
	
	@RequestMapping("/checkTruckAvailablity")
	@ResponseBody
	public String checkTruckAvailablity(@RequestParam("token") String token, @RequestParam("model") String model,
			@RequestParam("date") String date) {
		System.out.println(model+date);
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("USER")) {
			List<BookedTrucks> bookedList = bookedTrucksRepo.findAllByTruckModel(model);
			List<BookedTrucks> bookedList2 = new ArrayList<BookedTrucks>(); 
			for( BookedTrucks i : bookedList) {
				if(!i.getBookedDate().equals(date)) {
					bookedList2.add(i);
				}
			}

			TruckModel truckModel = truckModelRepo.findByModelName(model);
			if (bookedList2.size() >= truckModel.getNumberOfTrucks()) {
				System.out.println("in");
				return(0+"");
			}

			else {
					return(truckModel.getNumberOfTrucks()-bookedList2.size()+"");	}
		
	}
		else {
			return("Access Denied");
		}

}
	
	@RequestMapping(value="viewMyBookings",produces="application/json")
	@ResponseBody
	public List<BookedTrucks> viewMyBookings(@RequestParam("token") String token) {
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		if (currentUser.getRole().equals("USER")) {
			List<BookedTrucks> bookedList = bookedTrucksRepo.findAllByBookedBy(currentUser.getUserName());
			return(bookedList);
	}
		else {
			ArrayList<BookedTrucks> list = new ArrayList<BookedTrucks>();
			BookedTrucks bt = new BookedTrucks();
			bt.setBookedBy("Access Denied");
			list.add(bt);
			return (list);
		}

}
	
	@RequestMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
	public ResponseEntity<InputStreamResource> userReports(HttpServletResponse response,HttpServletRequest request) throws IOException, DocumentException {
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		System.out.println(token);
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		//int nFlag=0;
		if (currentUser.getRole().equals("ADMIN")||currentUser.getRole().equals("MANAGER")) {
		System.out.println("exporting pdf");
		List<Trucks> alltruck= truckRepo.findAll();
		ByteArrayInputStream bis = ExportPdf.truckReport(alltruck);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment;filename=trucks.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));}
		else{
			//nFlag=1;
			throw new IOException("Access Denied"); }
		//if(nFlag==1) {System.exit(1);}
	}
	@RequestMapping(value = "/exportpdfManager", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
	public ResponseEntity<InputStreamResource> ManagerReports(HttpServletResponse response,HttpServletRequest request) throws IOException, DocumentException {
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		System.out.println(token);
		User currentUser = userRepo.findByUserName(jwtUtil.extractUsername(token));
		//int nFlag=0;
		if (currentUser.getRole().equals("ADMIN")||currentUser.getRole().equals("MANAGER")) {
		System.out.println("exporting pdf");
		List<User> allManager= userRepo.findAllByRole("MANAGER");
		ByteArrayInputStream bis = ExportPdf.managerReport(allManager);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment;filename=managers.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));}
		else{
			//nFlag=1;
			throw new IOException("Access Denied"); }
		//if(nFlag==1) {System.exit(1);}
	}
	
	@RequestMapping(value = "/exportCSV")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		System.out.println("in csv");
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=managers.xlsx";

		response.setHeader(headerKey, headervalue);
		List<User> list= userRepo.findAllByRole("MANAGER");
		UserExcelExporter exp = new UserExcelExporter(list);
		exp.export(response);

	}
	@RequestMapping(value = "/exportCSVTruck")
	public void exportToExcelTruck(HttpServletResponse response) throws IOException {
		System.out.println("in csv");
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=trucks.xlsx";

		response.setHeader(headerKey, headervalue);
		List<Trucks> list = truckRepo.findAll();
		TruckExcelExporter exp = new TruckExcelExporter(list);
		exp.export(response);

	}

	
	
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return("login.jsp");
}

}