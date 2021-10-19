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
import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DistributorItemDAO dao=new DistributorItemDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		dao = context.getBean("DistributorItemDao", DistributorItemDAO.class);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<DistributorItemBean> items=new ArrayList<DistributorItemBean>();
		int id=Integer.parseInt(request.getParameter("id"));
		DistributorItemBean item=new DistributorItemBean();
		item.setId(id);
		HttpSession session=request.getSession();
		RequestDispatcher rd=request.getRequestDispatcher("GetItemsServlet");
		@SuppressWarnings("unchecked")
		List<DistributorItemBean> existingCart=(ArrayList<DistributorItemBean>)session.getAttribute("cartList");
		if(existingCart==null) {
			item=dao.getItemById(id);
			items.add(item);
			session.setAttribute("cartList", items);
			session.setAttribute("message", "Added to cart");
			rd.forward(request, response);
		}
		else {
			items=existingCart;
			boolean  exist=false;
			for(DistributorItemBean i:items) {
				if(i.getId()==id) {
					exist =true;
					session.setAttribute("message", "Already existing in cart");
					
				}
			}
			if(!exist) {
				item=dao.getItemById(id);
				existingCart.add(item);
				session.setAttribute("cartList", existingCart);
				session.setAttribute("message", "Added to cart");		
			}
			rd.forward(request, response);
		}	

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}
