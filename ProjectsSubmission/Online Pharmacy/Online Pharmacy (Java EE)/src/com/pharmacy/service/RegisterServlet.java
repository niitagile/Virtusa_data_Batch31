package com.pharmacy.service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		dao=new UserDAO();
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
