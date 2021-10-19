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

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.dao.DistributorItemDAO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DistributorItemDAO dao=new DistributorItemDAO();
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
