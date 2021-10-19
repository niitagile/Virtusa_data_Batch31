package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.util.DBUtil;

public class ParticularProductDAO {

	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;
	public ParticularProductDAO() {
		con = DBUtil.getDBConn();
	}
	public int insertItem(ParticularOrderProductBean prodOrder) {
		sql="INSERT INTO particular_order_prod (order_id,item_name,price,quantity) VALUES (?,?,?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, prodOrder.getOrderId());
			ps.setString(2,prodOrder.getItemName());
			ps.setFloat(3, prodOrder.getPrice());
			
			ps.setInt(4, prodOrder.getQuantity());
			row=ps.executeUpdate();
			return row;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteItem(int orderID) {
		sql="DELETE FROM particular_order_prod WHERE order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			row=ps.executeUpdate();
		}
		catch(Exception e) {
			row=0;
		}
		return row;
	}
	
	
	public String updateProductByIdAndOrderID(ParticularOrderProductBean prod) {
		sql="UPDATE particular_order_prod set item_name=?,price=?,quantity=? WHERE id=? and order_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, prod.getItemName());
			ps.setFloat(2, prod.getPrice());
			ps.setInt(3, prod.getQuantity());
			ps.setInt(4, prod.getId());
			ps.setInt(5, prod.getOrderId());
			row=ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			result= "FAIL";
		}
		return result;
	}
	public List<ParticularOrderProductBean> getAllItemsOfOrder(int orderID){
		sql="SELECT * FROM particular_order_prod WHERE order_id=?";
		List<ParticularOrderProductBean> prods=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			rs=ps.executeQuery();
			while(rs.next()) {
				ParticularOrderProductBean prod=new ParticularOrderProductBean();
				prod.setId(rs.getInt(1));
				prod.setOrderId(orderID);
				prod.setItemName(rs.getString("item_name"));
				
				prod.setPrice(rs.getFloat("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prods.add(prod);
			}
			return prods;
		}
		catch(Exception e) {
			return null;
		}	
	}
	public List<ParticularOrderProductBean> getProds(){
		sql="SELECT * FROM particular_order_prod";
		List<ParticularOrderProductBean> prods=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				ParticularOrderProductBean prod=new ParticularOrderProductBean();
				prod.setId(rs.getInt(1));
				prod.setOrderId(rs.getInt("order_id"));
				prod.setItemName(rs.getString("item_name"));
				prod.setPrice(rs.getFloat("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prods.add(prod);
			}
			return prods;
		}
		catch(Exception e) {
			return null;
		}	
	}
}
