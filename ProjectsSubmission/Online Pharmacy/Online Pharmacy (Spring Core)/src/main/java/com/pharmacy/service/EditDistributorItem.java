package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pharmacy.JdbcConfig;
import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.dao.DistributorItemDAO;
import com.pharmacy.dao.ItemsDAO;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/EditDistributorItem")
public class EditDistributorItem extends HttpServlet {
	DistributorItemDAO dao;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDistributorItem() {
        super();
        // TODO Auto-generated constructor stub
    } 
    
	@Override
	public void init() throws ServletException {
		dao=new DistributorItemDAO();
		
			
			ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
			dao = context.getBean("ItemsDao", DistributorItemDAO.class);
		
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
		int id=Integer.parseInt((String)request.getParameter("id"));
		DistributorItemBean item=new DistributorItemBean();
		item.setId(id);
		item.setItemName((String)request.getParameter("itemName"));
		float price=Float.parseFloat((String)request.getParameter("price"));
		item.setPrice(price);
		item.setDescription((String)request.getParameter("description"));
		int quantity=Integer.parseInt((String)request.getParameter("quantity"));
		item.setQuantity(quantity);
		String result=dao.updateItemById(item);
		request.getSession().setAttribute("message", result);
		response.sendRedirect("views/items.jsp");
	}

}
