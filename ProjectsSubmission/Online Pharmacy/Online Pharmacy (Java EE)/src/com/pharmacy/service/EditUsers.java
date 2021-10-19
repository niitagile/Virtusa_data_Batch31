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
 * Servlet implementation class EditUsers
 */
@WebServlet("/EditUsers")
public class EditUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
	public void init() throws ServletException {
		dao=new UserDAO();
	}
    public EditUsers() {
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
		int id=Integer.parseInt((String)request.getParameter("id"));
		String role=(String)request.getParameter("role");
		String username=(String)request.getParameter("username");
		if(role==null) {
			role=dao.getRole(username);
		}
		UserBean user=new UserBean();
		user.setId(id);
		user.setUsername(username);
		user.setPhoneNumber((String)request.getParameter("phoneNumber"));
		user.setEmail((String)request.getParameter("email"));
		user.setPassword((String)request.getParameter("password"));
		user.setRole(role);
		String result=dao.updateUser(user,id);
		request.getSession().setAttribute("message", result);
		response.sendRedirect("views/dashboard.jsp");
	}

	

}
