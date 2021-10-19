package com.pharmacy.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class ShowPrescription
 */
@WebServlet("/ShowPrescription")
public class ShowPrescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdersDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPrescription() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao=new OrdersDAO();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId=Integer.parseInt((String)request.getParameter("orderId"));
		byte prescription[]=dao.getPrescriptionById(orderId);
		OutputStream o=response.getOutputStream();
		response.setContentType("image/png");
		o.write(prescription);
		o.flush();
		o.close();

	}

}
