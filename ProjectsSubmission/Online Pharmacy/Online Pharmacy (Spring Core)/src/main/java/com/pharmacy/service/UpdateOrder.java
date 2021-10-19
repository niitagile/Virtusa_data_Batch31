package com.pharmacy.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pharmacy.JdbcConfig;
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
    private OrdersDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	public void init() throws ServletException {
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		productDao = context.getBean("particularProductDao", ParticularProductDAO.class);
		dao = context.getBean("ordersDao", OrdersDAO.class);
		itemDao=new ItemsDAO();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		String role=request.getParameter("role");
		String status=request.getParameter("status");
		String username=(String) request.getSession().getAttribute("username");
		if(role.equals("USER")) {
			response.sendRedirect(request.getContextPath()+"/views/dashboard.jsp");
		}
		else {
			String result=dao.updateOrder(orderId, status);
			System.out.println(result);
			if(status.equals("ACCEPTED") && role.equals("DISTRIBUTOR")) {
				List<ParticularOrderProductBean> products=productDao.getAllItemsOfOrder(orderId); 
				itemDao.updateDistributorItem(username, products);
			}
			if(result.equals("SUCCESS")) {
				response.sendRedirect("views/orders.jsp");
			}
			else {
				response.sendRedirect("views/error.jsp");
			}
		}
	}

}
