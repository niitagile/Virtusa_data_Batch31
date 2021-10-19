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
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDAO ordersDao;
    public DeleteOrder() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		ordersDao = context.getBean("ordersDao", OrdersDAO.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String result="";
		result=ordersDao.deleteOrderByID(id);
		if(result.equals("SUCCESS")) {
			response.sendRedirect("views/orders.jsp");
		}
		else {
			response.sendRedirect("views/error.jsp");
		}
	}


}
