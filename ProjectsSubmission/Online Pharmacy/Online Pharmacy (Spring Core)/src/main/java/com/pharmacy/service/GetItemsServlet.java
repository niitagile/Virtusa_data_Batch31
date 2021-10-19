package com.pharmacy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

/**
 * Servlet implementation class GetItemsServlet
 */
@WebServlet("/GetItemsServlet")
public class GetItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DistributorItemDAO dao;
    public GetItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao=new DistributorItemDAO();
		
	}
	@Override
    public void init() throws ServletException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		dao = context.getBean("DistributorDAO", DistributorItemDAO.class);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String category=request.getParameter("category");
		String distributor=request.getParameter("distributor");
		if(category!=null && distributor!=null) {
			session.setAttribute("category", category);
			session.setAttribute("distributor", distributor);
		}
		else {
			category=(String) session.getAttribute("category");
			distributor=(String) session.getAttribute("distributor");
		}
		List<DistributorItemBean> items=dao.getItemsByCategory(category, distributor);
		RequestDispatcher rd=request.getRequestDispatcher("views/itemslist.jsp");
		if(items!=null && !items.isEmpty()) {
			
			session.setAttribute("items", items);
			rd.forward(request, response);
		}
		else {
			request.getSession().setAttribute("message", "FAILED");
			rd.forward(request, response);
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
