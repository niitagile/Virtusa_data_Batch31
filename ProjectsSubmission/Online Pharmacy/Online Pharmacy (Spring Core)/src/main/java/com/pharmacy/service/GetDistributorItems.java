package com.pharmacy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pharmacy.JdbcConfig;
import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.dao.DistributorItemDAO;
import com.pharmacy.dao.ItemsDAO;

/**
 * Servlet implementation class GetItemServlet
 */
@WebServlet("/GetDistributorItems")
public class GetDistributorItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsDAO dao;
	private DistributorItemDAO itemDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDistributorItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void init() throws ServletException {
		dao=new ItemsDAO();
		itemDao=new DistributorItemDAO();
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		itemDao = context.getBean("ItemsDao", DistributorItemDAO.class);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String role=(String) session.getAttribute("role");
		List<DistributorItemBean> items=new ArrayList();
		RequestDispatcher rd=request.getRequestDispatcher("views/itemstable.jsp");
		if(role.equals("USER")) {
			response.sendRedirect("views/dashboard.jsp");
		}
		else if(role.equals("DISTRIBUTOR")) {
		String distributor=(String) session.getAttribute("username");
		items=dao.getAllDistributorItems(distributor);
		request.setAttribute("items", items);
		rd.forward(request, response);
		}
		else if(role.equals("ADMIN")) {
			items=itemDao.getItems();
			request.setAttribute("items", items);
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
