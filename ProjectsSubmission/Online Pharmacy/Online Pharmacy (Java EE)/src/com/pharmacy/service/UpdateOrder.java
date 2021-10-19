package com.pharmacy.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pharmacy.bean.OrdersBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.OrdersDAO;
import com.pharmacy.dao.ParticularProductDAO;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticularProductDAO productDao;
	private ItemsDAO itemDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public UpdateOrder() {
		super();
	}

	@Override
	public void init() throws ServletException {
		productDao = new ParticularProductDAO();
		itemDao = new ItemsDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String role = request.getParameter("role");
		String status = request.getParameter("status");
		String message=request.getParameter("message");
		String username = (String) request.getSession().getAttribute("username");
		OrdersDAO dao = new OrdersDAO();
		OrdersBean order = dao.getOrderByOrderID(orderId);
		String result="";
		if (role.equals("USER")) {
			response.sendRedirect(request.getContextPath() + "/views/dashboard.jsp");
		} else {
			if (order.isMedicine() && role.equals("DISTRIBUTOR")) {
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				order.setDistributorName(username);
				order.setTotalQuantity(quantity);
				order.setPrice(Float.parseFloat(request.getParameter("price")));
				order.setMessage(message);
				order.setStatus(status); 
				result=dao.updateOrderBean(order);
			} 
			else {
				result = dao.updateOrder(orderId,message, status);
				if (status.equals("ACCEPTED") && role.equals("DISTRIBUTOR")) {
					List<ParticularOrderProductBean> products = productDao.getAllItemsOfOrder(orderId);
					itemDao.updateDistributorItem(username, products);
				}
					
			}
			if (result.equals("SUCCESS")) {
				response.sendRedirect("views/orders.jsp");
			} else {
				response.sendRedirect("views/error.jsp");
			}
		}
	}

}
