package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.bean.ItemsBean;
import com.pharmacy.dao.ItemsDAO;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/AddDistributorItem")
public class AddDistributorItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ItemsDAO dao;

	public AddDistributorItem() {
		super();
	}

	@Override
	public void init() throws ServletException {
		dao = new ItemsDAO();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		DistributorItemBean item = new DistributorItemBean();
		ItemsBean i = new ItemsBean();
		if (role.equals("ADMIN")) {
			i.setDistributor(request.getParameter("distributor"));
		} else {
			i.setDistributor((String) request.getSession().getAttribute("username"));
		}
		i.setCategory(request.getParameter("category"));
		item.setItemName(request.getParameter("itemName"));
		item.setDescription(request.getParameter("description"));
		item.setPrice(Float.parseFloat(request.getParameter("price")));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		String result = dao.addItem(item, i);

		session.setAttribute("message", result);
		response.sendRedirect("views/items.jsp");
	}

}
