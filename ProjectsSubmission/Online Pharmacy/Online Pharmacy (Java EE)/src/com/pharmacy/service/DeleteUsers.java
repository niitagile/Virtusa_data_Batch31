package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.UserDAO;

/**
 * Servlet implementation class DeleteUsers
 */
@WebServlet("/DeleteUsers")
public class DeleteUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UserDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUsers() {
        super();
    }
    @Override
	public void init() throws ServletException {
		dao=new UserDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String distributor=request.getParameter("username");
		String role=dao.getRole(distributor);
		int id=Integer.parseInt(request.getParameter("id"));
		ItemsDAO itemDAO=new ItemsDAO();
		String result="";
		if(role.equals("DISTRIBUTOR")) {
			itemDAO.deleteItemByDistributor(distributor);
			
		}
		if(dao.deleteUserByUsername(id)!=null) {
			result="SUCCESS";
		}
		else {
			result="FAILED";
		}
		HttpSession session=request.getSession();
		session.setAttribute("message", result);
		response.sendRedirect("views/dashboard.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
