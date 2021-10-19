package com.pharmacy.service;

import java.io.IOException;
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
import com.pharmacy.bean.ItemsBean;
import com.pharmacy.dao.DistributorItemDAO;
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
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		dao = context.getBean("ItemsDao", ItemsDAO.class);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DistributorItemBean item=new DistributorItemBean();
		ItemsBean i=new ItemsBean();
		i.setDistributor((String)request.getSession().getAttribute("username"));
		i.setCategory(request.getParameter("category"));
		item.setItemName(request.getParameter("itemName"));
		item.setDescription(request.getParameter("description"));
		item.setPrice(Float.parseFloat(request.getParameter("price")));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		String result=dao.addItem(item,i);
		HttpSession session=request.getSession();
		session.setAttribute("message", result);
		response.sendRedirect("views/items.jsp");
	}

}
