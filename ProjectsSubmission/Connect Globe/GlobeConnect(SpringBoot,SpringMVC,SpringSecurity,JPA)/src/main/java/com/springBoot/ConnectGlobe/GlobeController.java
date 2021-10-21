package com.springBoot.ConnectGlobe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class GlobeController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	GlobeService userDetailsService;

	
	@Autowired
	GlobeService service;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	AuthenticationManager authenticationManager;

	
	private int qids;
	@GetMapping("/")
	public String home(HttpSession session) {
		System.out.println(session.getAttribute("userId"));
		session.setAttribute("userId","");
		return "Login";
	}
	@GetMapping("/register")
	public String registerrr(Model m) {
		m.addAttribute("register",new UserModel());
		return "Registration";
	}
	@PostMapping("/save")
	public ModelAndView saveToRegister(@ModelAttribute("register") UserModel m) {
		List<UserModel> l=new ArrayList<>();
//		String fullname=request.getParameter("username");
//		String email=request.getParameter("email");
//		String password=request.getParameter("")
//		String username=model.getEmail();
//		String password=model.getPassword();
//		String roles=model.getRoles();
		String username=m.getEmail();
		String password=m.getPassword();
		String roles=m.getRoles();
		if(service.checkemailexists(m.getEmail()))
		{
			ModelAndView mav = new ModelAndView("/Registration");
			mav.addObject("exits", "Email I'd Already Exists");
			return mav;
		}
		CredentialModel data=new CredentialModel(username,password,roles);
		UserModel u=service.saveToUser(m);
		CredentialModel c=service.saveToCredential(data);
		ModelAndView mav = new ModelAndView("/Registration");
		mav.addObject("success", "SuccesFully Registered and Click Login Button");
		return mav;
	}
	@PostMapping(value = "/login", produces = "application/json")
	public ModelAndView createAuthenticationToken(@RequestParam("email") String email,@RequestParam("psw") String password,HttpSession session,HttpServletRequest request) throws Exception{
		System.out.println("authenticated");
		System.out.println(password);
		AuthenticationRequest a=new AuthenticationRequest(email,password);
		System.out.println(a.getUsername());
		System.out.println(a.getPassword());
		try {
			System.out.println("check..");
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(a.getUsername(),a.getPassword())
				);
			System.out.println(authentication.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("checked..");
		}catch(BadCredentialsException e) {
			ModelAndView mav = new ModelAndView("/Login");
			mav.addObject("error", "Invalid username and password!");
			return mav;
		}
		System.out.println("done...");
		final UserDetails userDetails=userDetailsService.loadUserByUsername(a.getUsername());
		final CredentialModel model=userDetailsService.findByUsername(a.getUsername());
		System.out.println(model.getRoles());
		if(model.getRoles().equals("ROLE_USER")) {
			System.out.println("Hello patient");
			final UserModel p=userDetailsService.findByEmail(a.getUsername());
			ModelAndView mod = new ModelAndView("/Home");
			mod.addObject("userDetails",p);
			int id=p.getUserId();
			qids=id;
			UserModel uo=repo.getById(qids);
			String uId=String.valueOf(id);
			session.setAttribute("userId", uId);
			List<imageEntityClass> l=service.getAllPosts();
			System.out.println(l);
			mod.addObject("AllPosts",l);
			mod.addObject("Roles", uo);
//			final String jwt=jwtTokenUtil.generateToken(userDetails);
//			System.out.println(jwt);
//			mod.addObject("jwt", jwt);
//			HttpHeaders header= new HttpHeaders();
//			header.add("Authorization", "Bearer "+jwt);
//			try {
//				request.getSession().setAttribute("token", jwt);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			return mod;
		}else {
			System.out.println("Hello admin");
			final UserModel p=userDetailsService.findByEmail(a.getUsername());
//			final String jwt=jwtTokenUtil.generateToken(userDetails);
			List<UserModel> li=service.getAllUsers();
			ModelAndView mod = new ModelAndView("/admin");
			System.out.println(li);
			mod.addObject("userDetails", li);
			int id=p.getUserId();
			qids=id;
			UserModel uo=repo.getById(qids);
			String uId=String.valueOf(id);
			session.setAttribute("userId", uId);
			List<imageEntityClass> l=service.getAllPosts();
			System.out.println(l);
			mod.addObject("AllPosts",l);
			mod.addObject("Roles", uo);
//			try {
//				request.getSession().setAttribute("token", jwt);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			return mod;
		}
	}
	@GetMapping("/MyPosts")
	public ModelAndView getMyPosts() throws UnsupportedEncodingException {
		
		System.out.println("Hello"+qids);
		//int Uid=Integer.parseInt(id);
		List<imageEntityClass> l =service.getMyPosts(qids);
		ModelAndView mi=new ModelAndView("/Posts");
		UserModel u=repo.getById(qids);
		mi.addObject("MyPosts", l);
		mi.addObject("userModel", u);
		return mi;
	}
	
	@PostMapping("/upload")
	public ModelAndView uploadPost(@RequestParam("image") MultipartFile p,@RequestParam("tag") String tag,@RequestParam("userId") int id) throws IOException {
		System.out.println("Upload method");
		byte[] i=p.getBytes();
		uploadEntity u=new uploadEntity(id,tag,i);
		uploadEntity t=service.saveToMyPosts(u);
		int Uid=t.getUserId();
		List<imageEntityClass> l=service.getMyPosts(Uid);
		ModelAndView mk=new ModelAndView("/Posts");
		UserModel ui=repo.getById(Uid);
		mk.addObject("MyPosts", l);
		mk.addObject("userModel",ui);
		System.out.println("file uploaded succes");
		return mk;
	}
	
	@GetMapping("/home")
	public ModelAndView homecoming() throws UnsupportedEncodingException
	{
		List<imageEntityClass> t = service.getAllPosts();
		ModelAndView mav = new ModelAndView("/Home");
		UserModel u=repo.getById(qids);
		mav.addObject("AllPosts",t);
		mav.addObject("Roles", u);
		return mav;
	}
	
	@GetMapping("/AllReports")
	public ModelAndView AllReports()
	{
		List<MyReportEntityClass> re = service.getAllReports();
		ModelAndView mav = new ModelAndView("/Reports");
		UserModel u=repo.getById(qids);
		mav.addObject("AllReports",re);
		mav.addObject("Roles", u);
		return mav;
	}
	@GetMapping("/MyReports")
	public ModelAndView MyReports()
	{
		//int Uid=Integer.parseInt(id);
		System.out.println("Hello"+qids);
		List<MyReportEntityClass> re = service.getMyReports(qids);
		ModelAndView mav = new ModelAndView("/MyReports");
		UserModel u=repo.getById(qids);
		mav.addObject("ReportList",re);
		mav.addObject("userModel", u);
		return mav;
	}
	
	@PostMapping("/uploadReport")
	public ModelAndView uploadReport(@RequestParam("issue") String issue,@RequestParam("userId") int id)
	{
		System.out.println("Upload Report");
		MyReportEntity my = new MyReportEntity(id, issue);
		MyReportEntity m1 = service.saveToMyReport(my);
		int Uid=m1.getUserId();
		List<MyReportEntityClass> re = service.getMyReports(Uid);
		ModelAndView mav = new ModelAndView("/MyReports");
		UserModel ui=repo.getById(Uid);
		mav.addObject("ReportList",re);
		mav.addObject("userModel",ui);
		return mav;
	}
	@PostMapping("/comment")
	public ModelAndView postComment(@RequestParam("postId") int pId,@RequestParam("comment") String comment) throws UnsupportedEncodingException {
		commentEntity u=new commentEntity(pId,qids,comment);
		commentEntity c=service.saveToComment(u);
		List<imageEntityClass> t = service.getAllPosts();
		ModelAndView mav = new ModelAndView("/Home");
		UserModel ui=repo.getById(qids);
		mav.addObject("AllPosts",t);
		mav.addObject("Roles", ui);
		return mav;
	}
	@GetMapping("/AllComments/{id}")
	public ModelAndView viewComments(@PathVariable("id") int id) throws UnsupportedEncodingException {
		List<commentEntityClass> t=service.viewComments(id);
		List<imageEntityClass> g=service.getMyPostsComment(id);
		ModelAndView mav=new ModelAndView("/comment");
		mav.addObject("comments",t);
		mav.addObject("post",g);
		return mav;
	}
	@GetMapping("/back")
	public ModelAndView getBack() throws UnsupportedEncodingException
	{
		List<imageEntityClass> t = service.getAllPosts();
		ModelAndView mav = new ModelAndView("/Home");
		UserModel u=repo.getById(qids);
		mav.addObject("AllPosts",t);
		mav.addObject("Roles", u);
		return mav;
	}
	@PostMapping("/suggest")
	public ModelAndView PostSuggestion(@RequestParam("rId") int rId,@RequestParam("userId") int userId,@RequestParam("suggest") String suggest) {
		suggestEntity s=new suggestEntity(rId,userId,suggest);
		suggestEntity t=service.saveToSuggestions(s);
		List<MyReportEntityClass> re = service.getAllReports();
		ModelAndView mav = new ModelAndView("/Reports");
		UserModel u=repo.getById(qids);
		mav.addObject("AllReports",re);
		mav.addObject("Roles", u);
		return mav;
	}
	@GetMapping("/getAllSuggest/{id}")
	public ModelAndView viewSuggestions(@PathVariable("id") int id) {
		System.out.println(id+" hello");
		List<suggestionClass> l=service.viewSuggestions(id);
		MyReportEntity h=service.getMyReportSuggest(id);
		ModelAndView mav=new ModelAndView("/suggestions");
		mav.addObject("suggestions", l);
		mav.addObject("Report", h);
		return mav;
	}
	@GetMapping("/return")
	public ModelAndView getBackToReports() {
		List<MyReportEntityClass> re = service.getAllReports();
		ModelAndView mav = new ModelAndView("/Reports");
		UserModel u=repo.getById(qids);
		mav.addObject("AllReports",re);
		mav.addObject("Roles",u);
		return mav;
	}
	@GetMapping("/delete/{id}")
	public ModelAndView deleteReport(@PathVariable("id") int id) {
		ModelAndView mav = null;
//		int i=service.deleteInSuggestions(id);
		int j=service.deleteInReports(id);
		if(j>0) {
			List<MyReportEntityClass> re = service.getMyReports(qids);
			UserModel ui=repo.getById(qids);
			mav = new ModelAndView("/MyReports");
			mav.addObject("ReportList",re);
			mav.addObject("userModel", ui);
		}
		return mav;
	}
	@GetMapping("/deletePost/{id}")
	public ModelAndView deletePost(@PathVariable("id") int id) throws UnsupportedEncodingException {
		ModelAndView mav=null;
		System.out.println();
//		int i=service.deleteInComments(id);
		int j=service.deleteInPost(id);
		if( j>0) {
			List<imageEntityClass> l =service.getMyPosts(qids);
			mav=new ModelAndView("/Posts");
			UserModel ui=repo.getById(qids);
			mav.addObject("MyPosts", l);
			mav.addObject("userModel", ui);
		}
		return mav;
	}
	@GetMapping("/Profile")
	public ModelAndView MyProfile()
	{
		List<UserModel> q = service.getProfileDetails(qids);
		ModelAndView mav= new ModelAndView("/profile");
		UserModel m=repo.getById(qids);
		mav.addObject("userDetails",q);
		mav.addObject("Roles",m);
		return mav;
	}
	@PostMapping("/Edit")
	public ModelAndView profileeditdetails(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("pass") String password,@RequestParam("phone") String phone)
	{
		System.out.println(password);
		UserModel u=service.getDetails(id);
		System.out.println(u);
		u.setFullname(name);
		u.setPassword(password);
		System.out.println(u.getPassword());
		u.setMobileNumber(phone);
		CredentialModel c=service.getCredential(id);
		c.setUsername(u.getEmail());
		c.setPassword(password);
		CredentialModel model=service.saveToCredential(c);
		UserModel newusermodel = service.saveToUser(u);
		System.out.println(newusermodel);
		List<UserModel> q = service.getProfileDetails(id);
		ModelAndView mav= new ModelAndView("/profile");
		UserModel ui=repo.getById(id);
		mav.addObject("userDetails",q);
		mav.addObject("Roles", ui);
		return mav;
	}
	@GetMapping("/getUserProfile/{id}")
	public ModelAndView getUserProfile(@PathVariable("id") int id) {
		List<UserModel> q = service.getProfileDetails(id);
		ModelAndView mav= new ModelAndView("/ProfileAdmin");
		mav.addObject("userDetails",q);
		return mav;
	}
	@GetMapping("/deleteUserProfile/{id}")
	public ModelAndView deleteUser(@PathVariable("id") int id) {
		int i=service.deleteUser(id);
		List<UserModel> li=service.getAllUsers();
		ModelAndView mod = new ModelAndView("/admin");
		mod.addObject("userDetails", li);
		return mod;
	}
	@GetMapping("/adminHome")
	public ModelAndView getBackAdmin() {
		List<UserModel> li=service.getAllUsers();
		ModelAndView mod = new ModelAndView("/admin");
		mod.addObject("userDetails", li);
		return mod;
	}
}
