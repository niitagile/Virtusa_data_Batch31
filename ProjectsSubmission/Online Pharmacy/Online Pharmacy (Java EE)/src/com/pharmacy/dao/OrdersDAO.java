package com.pharmacy.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.bean.OrdersBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.util.DBUtil;

public class OrdersDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;
	ParticularProductDAO popb;

	public OrdersDAO() {
		popb = new ParticularProductDAO();
	}

	public int orderMedicine(OrdersBean order, InputStream prescription) {
		sql = "INSERT INTO orders (username,orderDate,address,phone_number,status,prescription,medicine,message) VALUES (?,?,?,?,?,?,?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getUsername());
			ps.setString(2, order.getOrderDate());
			ps.setString(3, order.getAddress());
			ps.setString(4, order.getPhoneNumber());
			ps.setString(5, order.getStatus());
			ps.setBlob(6, prescription);
			ps.setBoolean(7, true);
			ps.setString(8, order.getMessage());
			row = ps.executeUpdate();
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getOrderID(OrdersBean order) {
		sql = "SELECT * from orders where username=? and distributor_name=? and orderDate=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getUsername());
			ps.setString(2, order.getDistributorName());
			ps.setString(3, order.getOrderDate());
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("order_id");
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public byte[] getPrescriptionById(int orderId) {
		Blob image = null;
		byte[] img = null;
		sql = "SELECT prescription FROM orders WHERE order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			if (rs.next()) {
				image = rs.getBlob(1);
				img = image.getBytes(1, (int) image.length());
			}
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String addOrder(OrdersBean order, List<ParticularOrderProductBean> products) {
		sql = "INSERT INTO orders (username,orderDate,total_quantity,price,address,distributor_name,status,phone_number,medicine,message) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getUsername());
			ps.setString(2, order.getOrderDate());
			ps.setInt(3, order.getTotalQuantity());
			ps.setFloat(4, order.getPrice());
			ps.setString(5, order.getAddress());
			ps.setString(6, order.getDistributorName());
			ps.setString(7, order.getStatus());
			ps.setString(8, order.getPhoneNumber());
			ps.setBoolean(9,order.isMedicine());
			ps.setString(10, order.getMessage());
			int productRow = 0;
			row = ps.executeUpdate();
			if (row > 0) {
				int orderId = this.getOrderID(order);
				popb = new ParticularProductDAO();
				for (ParticularOrderProductBean product : products) {
					product.setOrderId(orderId);
					;
					productRow = popb.insertItem(product);
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
		List<OrdersBean> orders = new ArrayList<>();
		sql = "SELECT * FROM orders";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
				orders.add(order);
			}
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
		List<OrdersBean> orders = new ArrayList<>();
		sql = "SELECT * FROM orders where username = ?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
				orders.add(order);
			}
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String deleteOrderByID(int orderid, boolean isMedicine) {
		sql = "DELETE FROM orders WHERE order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderid);
			if (!isMedicine) {
				row = popb.deleteItem(orderid);
				if (row > 0) {
					row = ps.executeUpdate();
				}
			}
			else {row = ps.executeUpdate();}
			result = (row > 0) ? "SUCCESS" : "FAIL";

		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	public String updateOrder(int orderID, String message, String status) {
		sql = "UPDATE orders set status=?,message=? WHERE order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, message);
			ps.setInt(3, orderID);
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			result = "FAIL";
		}
		return result;

	}

	public String updateOrderBean(OrdersBean order) {
		sql = "UPDATE orders set distributor_name=?,total_quantity=?,price=?,message=?,status=? WHERE order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getDistributorName());
			ps.setInt(2, order.getTotalQuantity());
			ps.setFloat(3, order.getPrice());
			ps.setString(4, order.getMessage());
			ps.setString(5, order.getStatus());
			ps.setInt(6, order.getOrderId());
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			result = "FAIL";
		}
		return result;

	}

	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) {
		List<OrdersBean> orders = new ArrayList<>();
		if (role.equals("USER")) {
			sql = "SELECT * FROM orders WHERE username=?";
		} else if (role.equals("DISTRIBUTOR")) {
			sql = "SELECT * FROM orders WHERE distributor_name=?";
		} else if (role.equals("ADMIN")) {
			return this.getAllOrders();
		}
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
				orders.add(order);
			}
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
		sql = "SELECT * FROM orders WHERE order_id=?";
		OrdersBean order = new OrdersBean();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			if (rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
			}

			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<OrdersBean> getAllOrdesByDate(String date){
		sql="SELECT * FROM orders WHERE orderDate=?";
		List<OrdersBean> orders=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
				orders.add(order);
			}
			return orders;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<OrdersBean> getDistributorOrdersByDate(String distributor,String date){
		sql="SELECT * FROM orders WHERE distributor_name=? AND orderDate=?";
		List<OrdersBean> orders=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			ps.setString(2,date);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setUsername(rs.getString("username"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setTotalQuantity(rs.getInt("total_quantity"));
				order.setPrice(rs.getInt("price"));
				order.setAddress(rs.getString("address"));
				order.setDistributorName(rs.getString("distributor_name"));
				order.setStatus(rs.getString("status"));
				order.setMedicine(rs.getBoolean("medicine"));
				order.setMessage(rs.getString("message"));
				order.setPhoneNumber(rs.getString("phone_number"));
				orders.add(order);
			}
			return orders;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<OrdersBean> getOrdersByDate(String date,String role,String distributor){
		if(role.equals("ADMIN")) {
			return this.getAllOrdesByDate(date);
		}
		else if(role.equals("DISTRIBUTOR")) {
			return this.getDistributorOrdersByDate(distributor, date);
		}
		else {
			return null;
		}
	}
}
