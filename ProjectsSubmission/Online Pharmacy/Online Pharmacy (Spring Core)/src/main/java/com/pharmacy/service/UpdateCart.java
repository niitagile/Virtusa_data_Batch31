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

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DistributorItemDAO dao=new DistributorItemDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public List<DistributorItemBean> updateCart(List<DistributorItemBean> items,String values[]) {
		int i=0;
		for(DistributorItemBean item:items) {
			item.setQuantity(Integer.parseInt(values[i]));
			items.set(i, item);
			i++;
		}
		return items;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String values[]=request.getParameterValues("quantity");
		String check=request.getParameter("checkbox");
		HttpSession session=request.getSession();
		RequestDispatcher rd=request.getRequestDispatcher("views/cart.jsp");
		@SuppressWarnings("unchecked")
		List<DistributorItemBean> items=(ArrayList<DistributorItemBean>) session.getAttribute("cartList");
		boolean redirect=false;
		if(check.equals("update")) {
			items=this.updateCart(items, values);
			session.setAttribute("cartList", items);
			redirect=true;
			
		}
		else if(check.equals("clear")) {
			session.setAttribute("cartList", null);
			session.removeAttribute("category");
			session.removeAttribute("distributor");
			redirect=true;
		}
		else if(check.equals("checkout")) {
			items=this.updateCart(items, values);
			session.setAttribute("cartList", items);
			redirect=false;
			
		}
		
		if(redirect) {
			rd.forward(request, response);
		}
		else {
			rd=request.getRequestDispatcher("views/bill.jsp");
			rd.forward(request, response);
		}
	}

}
