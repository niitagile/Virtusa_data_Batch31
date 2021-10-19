package com.pharmacy.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		boolean isMedicine=Boolean.parseBoolean(request.getParameter("medicine"));
		OrdersDAO orderDAO=new OrdersDAO();
		String result="";
		result=orderDAO.deleteOrderByID(id,isMedicine);
		if(result.equals("SUCCESS")) {
			response.sendRedirect("views/orders.jsp");
		}
		else {
			response.sendRedirect("views/error.jsp");
		}
	}


}
