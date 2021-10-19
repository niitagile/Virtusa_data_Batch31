package com.pharmacy.service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pharmacy.bean.UserBean;
import com.pharmacy.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	UserDAO dao;
	@Override
	public void init() throws ServletException {
		super.init();
		ApplicationContext context = new AnnotationConfigApplicationContext(com.pharmacy.JdbcConfig.class);
		dao= context.getBean("userDao", UserDAO.class);
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String no=request.getParameter("mobile");
		String accType=request.getParameter("usertype").toUpperCase();
		System.out.println(accType);
		//RequestDispatcher rd=request.getRequestDispatcher("signup.jsp");
		UserBean user=new UserBean(name,uname,email,no,password,accType);
		String result=dao.addUser(user);
		if(result.equals("SUCCESS")) {
			request.getSession().setAttribute("msg", "Registered Successfully");
			response.sendRedirect("views/signup.jsp");
		}
		else {
			request.getSession().setAttribute("msg", "Registration Failed");
			response.sendRedirect("views/signup.jsp");
		}
	} 

}
