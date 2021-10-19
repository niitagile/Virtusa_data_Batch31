package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.util.DBUtil;

public class DistributorItemDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;
	public DistributorItemDAO() {
		con = DBUtil.getDBConn();
	}
	
	public String insertItem(DistributorItemBean item) {
		sql="INSERT INTO distributor_item (items_id,item_name,price,description,quantity) VALUES (?,?,?,?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, item.getItemsId());
			ps.setString(2,item.getItemName());
			ps.setFloat(3, item.getPrice());
			ps.setString(4,item.getDescription());
			ps.setInt(5, item.getQuantity());
			row=ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
		return result;
	}
	
	public int deleteItem(int id,int itemsId) {
		sql="DELETE FROM distributor_item WHERE id=? AND items_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, itemsId);
			row=ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			row=0;
		}
		return row;
	}
	
	public String updateItemById(DistributorItemBean item) {
		sql="UPDATE distributor_item set item_name=?,price=?,description=?,quantity=? WHERE id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, item.getItemName());
			ps.setFloat(2, item.getPrice());
			ps.setString(3, item.getDescription());
			ps.setInt(4, item.getQuantity());
			ps.setInt(5, item.getId());
			row=ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			result= "FAIL";
		}
		return result;
	}
	public List<DistributorItemBean> getAllItems(int items_id){
		sql="SELECT * FROM distributor_item WHERE items_id=?";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, items_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				DistributorItemBean item=new DistributorItemBean();
				item.setId(rs.getInt(1));
				item.setItemsId(items_id);
				item.setItemName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getFloat("price"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
			return items;
		}
		catch(Exception e) {
			return null;
		}	
	}
	public DistributorItemBean getItemById(int id){
		sql="SELECT * FROM distributor_item WHERE id=?";
		DistributorItemBean item=new DistributorItemBean();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				item.setId(rs.getInt(1));
				item.setItemsId(rs.getInt(2));
				item.setItemName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getFloat("price"));
				item.setQuantity(rs.getInt("quantity"));
			}
			return item;
		}
		catch(Exception e) {
			return null;
		}	
	}
	
	public List<DistributorItemBean> getCartProducts(List<DistributorItemBean> items){
		List<DistributorItemBean> products =new ArrayList<DistributorItemBean>();
		if(items.size()>0 && items!=null) {
			for(DistributorItemBean item:items) {
				DistributorItemBean cart=this.getItemById(item.getId());
				cart.setPrice(item.getQuantity()*cart.getPrice());
				cart.setQuantity(item.getQuantity());
				products.add(cart);
			}
		}
		return products;
	}
	public List<DistributorItemBean> getItems(){
		sql="SELECT * FROM distributor_item";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				DistributorItemBean item=new DistributorItemBean();
				item.setId(rs.getInt(1));
				item.setItemsId(rs.getInt(2));
				item.setItemName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getFloat("price"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
			return items;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<DistributorItemBean> getItemsByCategory(String category,String distributor){
		ItemsDAO dao=new ItemsDAO();
		int items_id=dao.getIdByCategory(category, distributor);
		sql="SELECT * FROM distributor_item WHERE items_id=?";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, items_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				DistributorItemBean item=new DistributorItemBean();
				item.setId(rs.getInt(1));
				item.setItemsId(rs.getInt(2));
				item.setItemName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getFloat("price"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
			return items;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getItemsIdbyId(int id) {
		sql="SELECT * FROM distributor_item WHERE id=?";
		int itemsId=0;
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				itemsId= rs.getInt(2);
			}
			return itemsId;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	public int getQuantityByItemIdAndName(int itemsId,String itemName) {
		sql="SELECT quantity FROM distributor_item WHERE items_id=? AND item_name=?";
		try {
			con=DBUtil.getDBConn();
			ps=con.prepareStatement(sql);
			ps.setInt(1, itemsId);
			ps.setString(2, itemName);
			rs=ps.executeQuery();
			if(rs.next()) {
				row=rs.getInt("quantity");
			}
			return row;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public String deleteByItemsId(int itemsId) {
		sql="DELETE FROM distributor_item WHERE items_id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemsId);
			row=ps.executeUpdate();
			return (row>0)? "SUCCESS":"FAILED";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
		
	}
	public void updateItemAfterOrder(int itemsId,String itemName,int quantity) {
		int itemQuantity=this.getQuantityByItemIdAndName(itemsId, itemName);
		quantity=itemQuantity-quantity;
		sql="UPDATE distributor_item SET quantity=? WHERE items_id=? AND item_name=?";
		try {
			con=DBUtil.getDBConn();
			ps=con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, itemsId);
			ps.setString(3, itemName);
			row=ps.executeUpdate();
			if(row>0) {
				System.out.print("SUCCESS");
			}
			else {
				System.out.print("FAIL");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
