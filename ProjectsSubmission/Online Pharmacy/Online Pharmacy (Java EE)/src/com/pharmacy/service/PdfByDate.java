package com.pharmacy.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.pharmacy.bean.OrdersBean;
import com.pharmacy.dao.OrdersDAO;
import com.pharmacy.util.OrderPDFExporter;


@WebServlet("/PdfByDate")
public class PdfByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDAO dao=new OrdersDAO();
		String role=request.getParameter("role");
		String date = request.getParameter("date");
		String username=(String)request.getSession().getAttribute("username");
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date()); 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<OrdersBean> listOrders = 	dao.getOrdersByDate(date,role,username);
        OrderPDFExporter exporter = new OrderPDFExporter(listOrders);
        try {
			exporter.export(response);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}


}
