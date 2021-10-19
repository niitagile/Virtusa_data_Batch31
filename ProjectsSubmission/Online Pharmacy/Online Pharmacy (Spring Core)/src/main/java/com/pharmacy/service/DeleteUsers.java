package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        // TODO Auto-generated constructor stub
    }
    @Override
	public void init() throws ServletException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(com.pharmacy.JdbcConfig.class);
		dao = context.getBean("userDao", UserDAO.class);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		String result="";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
