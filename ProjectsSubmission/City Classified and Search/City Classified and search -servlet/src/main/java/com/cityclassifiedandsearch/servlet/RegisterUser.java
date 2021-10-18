package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.dao.UserDAO;

@WebServlet("/Register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterUser() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userEmail=request.getParameter("userEmail");
		String userAddress=request.getParameter("userAddress");
		String userCity=request.getParameter("userCity");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		User user= new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(password);
		user.setUserAddress(userAddress);
		user.setMobile(mobile);
		user.setUserCity(userCity);
		user.setRole("user");
		user.setEnabled(true);//if(otp is valid//
		try {
			UserDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/***call SendEmail()***/
		
		
		//response.sendRedirect("login.jsp");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmailSendingServlet");
		dispatcher.forward(request, response);
		
	}

}
