package com.pharmacy.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pharmacy.JdbcConfig;
import com.pharmacy.bean.OrdersBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.util.DBUtil;

@Component("ordersDao")
public class OrdersDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ParticularProductDAO popb;
	
	public ParticularProductDAO getPopb() {
		return popb;
	}

	public void setPopb(ParticularProductDAO popb) {
		this.popb = popb;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;
	
	
	public OrdersDAO() {
		
	}
	public int getOrderID(OrdersBean order) {
		sql = "SELECT * from orders where username=? and distributor_name=? and orderDate=?";
		try {
			OrdersRowMapper rowMapper = new OrdersRowMapper();
			OrdersBean orderResult = this.jdbcTemplate.queryForObject(sql, rowMapper, order.getUsername(),
					order.getDistributorName(), order.getOrderDate());
			return orderResult.getOrderId();
		} catch (Exception e) {
			return 0;
		}
	}

	public String addOrder(OrdersBean order, List<ParticularOrderProductBean> products) {
		sql = "INSERT INTO orders (username,orderDate,total_quantity,price,address,distributor_name,status,phone_number) VALUES (?,?,?,?,?,?,?,?)";
		try {
			int productRow = 0;
			row = this.jdbcTemplate.update(sql, order.getUsername(), order.getOrderDate(), order.getTotalQuantity(),
					order.getPrice(), order.getAddress(), order.getDistributorName(), order.getStatus(), order.getPhoneNumber());
			if (row > 0) {
				int orderId = this.getOrderID(order);
				for (ParticularOrderProductBean product : products) {
					product.setOrderId(orderId);
					productRow = this.popb.insertItem(product);
				}
			}
			result = (row > 0 && productRow > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	public List<OrdersBean> getAllOrders() {
		List<OrdersBean> orders = new ArrayList();
		sql = "SELECT * FROM orders";
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper());
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<OrdersBean> getAllOrdersByUsername(String username) {
		List<OrdersBean> orders = new ArrayList();
		sql = "SELECT * FROM orders where username = ?";
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper(), username);
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String deleteOrderByID(int orderid) {
		sql = "DELETE FROM orders WHERE order_id=?";
		try {
			row = this.popb.deleteItem(orderid);
			if (row>0) {
				row=this.jdbcTemplate.update(sql, orderid);
			}
			result = (row > 0) ? "SUCCESS" : "FAIL";

		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	public String updateOrder(int orderID,String status) {
		sql = "UPDATE orders set status=? WHERE order_id=?";
		try {
//			con = DBUtil.getDBConn();
//			ps = con.prepareStatement(sql);
//			ps.setString(1, status);
//			ps.setInt(2, orderID);
//			row = ps.executeUpdate();
			row = this.jdbcTemplate.update(sql, status, orderID);
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			result = "FAIL";
		}
		return result;

	}

	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) {
		List<OrdersBean> orders = new ArrayList();
		if (role.equals("USER")) {
			sql = "SELECT * FROM orders WHERE username=?";
		} else if (role.equals("DISTRIBUTOR")) {
			sql = "SELECT * FROM orders WHERE distributor_name=?";
		}
		else if(role.equals("ADMIN")) {
			return this.getAllOrders();
		}
		try {
			orders = this.jdbcTemplate.query(sql,new OrdersRowMapper(), name);
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public OrdersBean getOrderByOrderID(int orderId) {
		sql="SELECT * FROM orders WHERE order_id=?";
		OrdersBean order=new OrdersBean();
		try {
			OrdersRowMapper rowMapper = new OrdersRowMapper();
			order = this.jdbcTemplate.queryForObject(sql, rowMapper, orderId);
			return order;
		}	
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}	
	}
}
