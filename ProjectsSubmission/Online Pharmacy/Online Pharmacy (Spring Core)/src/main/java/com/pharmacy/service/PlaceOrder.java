package com.pharmacy.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.pharmacy.bean.OrdersBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdersDAO ordersDao;
	ItemsDAO itemDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		ordersDao = context.getBean("ordersDao", OrdersDAO.class);
		itemDao = new ItemsDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		List<DistributorItemBean> items=(ArrayList<DistributorItemBean>) session.getAttribute("cartList");
		List<ParticularOrderProductBean> products=new ArrayList();
		float totalPrice=0.0f;
		int itemsId=0;
		int totalQuantity=0;
		String address=request.getParameter("address");
		String username=(String) session.getAttribute("username");
		String phone=request.getParameter("phone");
		String date=LocalDate.now().toString();
		String distributor="";
		OrdersBean order=new OrdersBean();
		for(DistributorItemBean item:items) {
			ParticularOrderProductBean product=new ParticularOrderProductBean();
			int quantity=item.getQuantity();
			float price=item.getPrice();	
			product.setItemName(item.getItemName());
			product.setPrice(price);
			product.setQuantity(quantity);
			totalQuantity+=quantity;
			totalPrice+=price;
			itemsId=item.getItemsId();
			products.add(product);
		}
		distributor=itemDao.getDistributorName(itemsId);
		order.setDistributorName(distributor);
		order.setAddress(address);
		order.setPrice(totalPrice);
		order.setTotalQuantity(totalQuantity);
		order.setPhoneNumber(phone);
		order.setUsername(username);
		order.setOrderDate(date);
		order.setStatus("PENDING");
		String result=ordersDao.addOrder(order, products);
		if(result.equals("SUCCESS")) {
			session.removeAttribute("cartList");
			session.removeAttribute("category");
			response.sendRedirect("views/order-success.jsp");
		}
	}

}
