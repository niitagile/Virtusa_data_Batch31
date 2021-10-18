package com.cityclassifiedandsearch.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.dao.UserDAO;
import com.cityclassifiedandsearch.util.MyUtil;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = UserDAO.check(email, password);
		if(user==null) {
			request.setAttribute("error","Invalid User Name or Password");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else {
			HttpSession session=request.getSession();
			MyUtil.storeLoginedUser(session, user);
			System.out.print(user);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/welcome.jsp");
			dispatcher.forward(request, response);
		}
	}

}
