package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pharmacy.dao.DistributorItemDAO;

/**
 * Servlet implementation class DeleteItemServlet
 */
@WebServlet("/DeleteDistributorItem")
public class DeleteDistributorItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DistributorItemDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDistributorItem() {
		super();
	}
	
	

	@Override
	public void init() throws ServletException {
		dao=new DistributorItemDAO();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		int itemsId=Integer.parseInt(request.getParameter("itemsId"));
		String result="";
		if(dao.deleteItem(id, itemsId)>0) {
			result="SUCCESS";
		}
		else {
			result="FAIL";
		}
		HttpSession session=request.getSession();
		session.setAttribute("message", result);
		response.sendRedirect("views/items.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
