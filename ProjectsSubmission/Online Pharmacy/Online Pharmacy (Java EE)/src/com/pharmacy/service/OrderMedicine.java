package com.pharmacy.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.pharmacy.bean.OrdersBean;
import com.pharmacy.dao.OrdersDAO;


@WebServlet("/OrderMedicine")
@MultipartConfig(maxFileSize = 16177215)   
public class OrderMedicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdersDAO dao;
    
    public OrderMedicine() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		dao=new OrdersDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String username=(String) request.getSession().getAttribute("username");
		String date=LocalDate.now().toString();
		Part filePart = request.getPart("prescription");
		InputStream prescription = null;
		if (filePart != null) {
            prescription = filePart.getInputStream();
        }
		OrdersBean order=new OrdersBean();
		order.setAddress(address);
		order.setPhoneNumber(phone);
		order.setUsername(username);
		order.setOrderDate(date);
		order.setStatus("PENDING");
		order.setMessage("Order Placed, pending approval");
		order.setMedicine(true);
		int status=dao.orderMedicine(order, prescription);
		if(status>0) {
			response.sendRedirect("views/order-success.jsp");
		}
		else {
			response.sendRedirect("views/error.jsp");
		}
		
	}

}
