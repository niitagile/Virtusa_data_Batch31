package com.cityclassifiedandsearch.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 * 
 * @author www.codejava.net
 * 
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		//ServletContext context = getServletContext();
		host = "smtp.gmail.com";
		port = "587";
		user = "cityclassifiedsearch@gmail.com";
		pass = "virtusaproject";
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		String recipient = request.getParameter("userEmail");
		String email=request.getParameter("userEmail");
		int randomPIN = (int)(Math.random()*9000)+1000;
		String val = ""+randomPIN;
		request.setAttribute("val", val);
		System.out.println("email: "+recipient);
		String subject = val;
		System.out.println("otp: "+subject);
		String content = "Your otp for city classfied search is "+val;
		
		String resultMessage = "";

		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
					content);
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("Message", resultMessage);
			request.setAttribute("val", val);
			HttpSession session = request.getSession();
			session.setAttribute("gen_otp", val);

			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("emailverification.jsp");
			dispatcher.forward(request, response);
//			getServletContext().getRequestDispatcher("emailverification.jsp").forward(
//					request, response);
			System.out.println(resultMessage);
		}
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	
	
}