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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname=request.getParameter("username");
		String pass=request.getParameter("password");
		HttpSession session=request.getSession();
		if(dao.authenticate(uname, pass)) {
			
			session.setAttribute("username", uname);
			session.setAttribute("role", dao.getRole(uname));
			response.sendRedirect("views/dashboard.jsp");
		}
		else {
			session.setAttribute("status", false);
			response.sendRedirect("views/login.jsp");
		}
	}

	@Override
	public void init() throws ServletException {
		ApplicationContext context = new AnnotationConfigApplicationContext(com.pharmacy.JdbcConfig.class);
		dao = context.getBean("userDao", UserDAO.class);
		super.init();
	}

}
