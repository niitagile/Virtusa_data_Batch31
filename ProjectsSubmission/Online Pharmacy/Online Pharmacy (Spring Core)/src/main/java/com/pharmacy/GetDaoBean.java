package com.pharmacy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pharmacy.JdbcConfig;
import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.bean.ItemsBean;
import com.pharmacy.bean.OrdersBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.bean.UserBean;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.OrdersDAO;
import com.pharmacy.dao.ParticularProductDAO;
import com.pharmacy.dao.UserDAO;

public class GetDaoBean {
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		ItemsDAO items=context.getBean("ItemsDao",ItemsDAO.class);
		ItemsBean bean=new ItemsBean();
		bean.setDistributor("madhu");
		bean.setCategory("medicine");
		DistributorItemBean distributorBean =new DistributorItemBean(1, 101,"Water bottle", 30, "Stores water", 3);
		ItemsBean itemBean = new ItemsBean(101,"Madhu", "Medicine");
		//String addItem = items.addItem(distributorBean, itemBean);
		//System.out.println(addItem);
		List<ItemsBean> i=items.getAllItems();
		for(ItemsBean b:i)
		{
			System.out.println(b);
		}
		/*OrdersDAO ordersDao = context.getBean("ordersDao", OrdersDAO.class);
		OrdersBean bean = new OrdersBean();
		bean.setUsername("sam_fisher");
		bean.setOrderDate("2021-10-07");
		bean.setTotalQuantity(20);
		bean.setPrice(3000);
		bean.setAddress("Chennai, Tamil Nadu");
		bean.setPhoneNumber("7945612301");
		bean.setDistributorName("sid_distributor");
		bean.setStatus("PENDING");*/
//		
//		ParticularOrderProductBean prod1 = new ParticularOrderProductBean();
//		prod1.setItemName("Gloves");
//		prod1.setPrice(40);
//		prod1.setQuantity(4);
//		
//		ParticularOrderProductBean prod2 = new ParticularOrderProductBean();
//		prod2.setItemName("Hand Sanitizer");
//		prod2.setPrice(50);
//		prod2.setQuantity(2);
//		
//		List<ParticularOrderProductBean> prodList = new ArrayList<ParticularOrderProductBean>();
//		prodList.add(prod1);
//		prodList.add(prod2);
		
//		int orderID = ordersDao.getOrderID(bean);
//		System.out.println(orderID);
//		List<OrdersBean> allOrders = ordersDao.getAllOrdersByUsername("sam_fisher");
//		for(OrdersBean beans : allOrders)
//		System.out.println(beans);
//		String deleteOrderByID = ordersDao.deleteOrderByID(23);
//		System.out.println(deleteOrderByID);
		
//		String updateOrder = ordersDao.updateOrder(19, "DELIVERED");
//		System.out.println(); 
		//OrdersBean orderByOrderID = ordersDao.getOrderByOrderID(6);
		//System.out.println(orderByOrderID);
	}
}
