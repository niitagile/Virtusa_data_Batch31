package com.cityclassifiedandsearch.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cityclassifiedandsearch.bean.User;

/**
 * Servlet implementation class email_otp
 */
@WebServlet("/otp_check")
public class otp_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public otp_check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String otp=request.getParameter("otp");
		System.out.println("user given otp:"+otp);
		HttpSession session = request.getSession();
		String gen_otp=(String) session.getAttribute("gen_otp");
		System.out.println("generated otp:"+gen_otp);
		//PrintWriter out=response.getWriter();
		if(otp.equals(gen_otp)) {
			
			response.sendRedirect("index.jsp");
		}
		else {
			//response.sendRedirect("register.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
