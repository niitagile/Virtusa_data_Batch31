package com.pharmacy.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.service.ItemsService;
import com.pharmacy.service.OrdersService;
import com.pharmacy.service.ParticularOrderProductService;

@Controller
@Transactional
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ParticularOrderProductService particularOrderService;

	@Autowired
	private ItemsService itemService;

	// Display the particular order page
	@GetMapping("/particularOrder/{orderId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView particularOrder(@PathVariable("orderId") int orderId) {
		OrdersBean ordersBean = this.ordersService.getOrderByOrdeId(orderId);
		List<ParticularOrderBean> particularOrderBean = this.particularOrderService.getPartByOrderId(orderId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", particularOrderBean);
		modelAndView.addObject("order", ordersBean);
		modelAndView.setViewName("particularorder");
		return modelAndView;
	}

	@GetMapping("/showOrdersAdmin/{username}/{role}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView showOrdersAdmin(@PathVariable("username") String username, @PathVariable String role) {
		ModelAndView modelAndView = new ModelAndView();
		List<OrdersBean> allOrders = ordersService.getAllOrders();
		modelAndView.addObject("orders", allOrders);
		modelAndView.addObject("role", role);
		modelAndView.setViewName("orders");
		return modelAndView;
	}

	@GetMapping("/medicinerequest")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView showMedicineRequest() {
		List<OrdersBean> orders = ordersService.getAllOrders();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("medicinerequest");
		return modelAndView;
	}

	// Display orders based on role and username
	@GetMapping("/showOrders/{username}/{role}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView showOrders(@PathVariable("username") String username, @PathVariable("role") String role,
			Model m) {
		List<OrdersBean> orders = ordersService.getOrdersByNameAndRole(username, role);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("role", role);
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("orders");
		return modelAndView;
	}

	@GetMapping("/DeleteOrder")
	@PreAuthorize("hasAnyRole('ADMIN','USER','DISTRIBUTOR')")
	public ModelAndView delete(@RequestParam("id") int userId,@RequestParam boolean medicine, Model model) {
		particularOrderService.deleteInTable(userId,medicine);
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("dashboard");
	}
	
    
	@PostMapping("/UpdateOrder/{id}/{medicine}")
	@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
	public ModelAndView updateOrderStatus(@PathVariable("id") int id, @PathVariable("medicine") boolean medicine,
			HttpServletRequest request, Model model) {
		String role = (String) request.getSession().getAttribute("role");
		String status = request.getParameter("status");
		String message = request.getParameter("message");
		String username = (String) request.getSession().getAttribute("username");
		OrdersBean order=ordersService.getOrderByOrdeId(id);
		if (medicine && role.equals("DISTRIBUTOR") ) {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			float price = Float.parseFloat(request.getParameter("price"));
			order.setStatus(status);
			order.setMessage(message);
			order.setTotalQuantity(quantity);
			order.setPrice(price);
			order.setDistributorName(username);
			ordersService.updateOrder(order);
			
		}
		else {
			ordersService.updateOrderStatus(id, status,message);
			if (status.equals("ACCEPTED") && role.equals("DISTRIBUTOR")) {
				List<ParticularOrderBean> products = particularOrderService.getPartByOrderId(id);
				itemService.updateDistributorItem(username, products);
			}
			
		}

		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("order-success");
	}

	@PostMapping("/orderplaced")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView saveOrderDetails(HttpServletRequest request, Model model) {

		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession();
		List<ParticularOrderBean> products = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<DistributorItemBean> items = (ArrayList<DistributorItemBean>) session.getAttribute("cartList");
		float totalPrice = 0.0f;
		int totalQuantity = 0;
		ItemsBean itemBean = new ItemsBean();
		String username = (String) session.getAttribute("username");
		String date = LocalDate.now().toString();
		String distributor = "";
		for (DistributorItemBean item : items) {
			ParticularOrderBean product = new ParticularOrderBean();
			product.setItemName(item.getItemName());
			int quantity = item.getQuantity();
			float price = item.getPrice();
			product.setPrice(price);
			product.setQuantity(quantity);
			totalQuantity += quantity;
			totalPrice += price;
			itemBean.setId(item.getItemBean().getId());

			products.add(product);
		}
		distributor = itemService.getDistributorName(itemBean.getId());
		OrdersBean order = new OrdersBean();
		order.setDistributorName(distributor);
		order.setAddress(address);
		order.setPrice(totalPrice);
		order.setTotalQuantity(totalQuantity);
		order.setPhoneNumber(phone);
		order.setUsername(username);
		order.setMessage("Order placed, Pending Approval");
		order.setOrderDate(date);
		order.setStatus("PENDING");
		String result = ordersService.addOrder(order, products);
		if (result.equals("SUCCESS")) {
			session.removeAttribute("cartList");
			session.removeAttribute("category");
			return new ModelAndView("order-success");
		}
		return new ModelAndView("order-success");

	}
	
	@PostMapping("/fileUpload")
	@PreAuthorize("hasRole('USER')")
    public ModelAndView saveFile(HttpServletRequest request, Model model,@RequestParam("prescription") MultipartFile prescription) throws IOException 
    {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String date=LocalDate.now().toString();
		byte[] image = prescription.getBytes();
		OrdersBean order = new OrdersBean();
		order.setAddress(address);
		order.setPhoneNumber(phone);
		order.setUsername(username);
		order.setOrderDate(date);
		order.setStatus("PENDING");
		order.setMessage("Order Placed, pending approval");
		order.setMedicine(true);
		order.setPrescription(image);
		ordersService.addFile(order);
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("order-success");
		
    }
    
	@GetMapping("/pdfConvert/{role}")
	@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
	public void pdfConvert(@PathVariable("role") String role,HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException, com.itextpdf.text.DocumentException
	{
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<OrdersBean> listOrders = new ArrayList<>();
        if(role.equals("ADMIN")) {
        	listOrders=ordersService.getAllOrders();
        } 
        else {
        	String username=(String)request.getSession().getAttribute("username");
        	listOrders=ordersService.getOrdersByDistributor(username);
        }
        OrderPDFExporter exporter = new OrderPDFExporter(listOrders);
        exporter.export(response);
	}
	
	@PostMapping("/pdfConvertByDate/{role}")
	@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
	public void pdfConvertByDate(@PathVariable("role") String role,HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException, com.itextpdf.text.DocumentException
	{
		String date = request.getParameter("date");
		String username=(String)request.getSession().getAttribute("username");
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date()); 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<OrdersBean> listOrders = 	this.ordersService.getOrdersByDate(date,role,username);
        OrderPDFExporter exporter = new OrderPDFExporter(listOrders);
        exporter.export(response);
	}
}
