package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.dao.DistributorItemDAO;

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
    } 
    
	@Override
	public void init() throws ServletException {
		dao=new DistributorItemDAO();
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
