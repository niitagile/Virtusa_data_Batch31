package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.bean.ItemsBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.util.DBUtil;
@Component("ItemsDao")
public class ItemsDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
//	DistributorItemDAO itemDAO;
	private String result;
	private int row;
    @Autowired
    DistributorItemDAO itemDAO;
    @Autowired
	private JdbcTemplate jdbcTemplate;
	

	public DistributorItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(DistributorItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public ItemsDAO()
    {
    	
    }
	public String addItem(DistributorItemBean distributorItem,ItemsBean item) {
		int itemsId=this.getItemIdByDistributor(item.getDistributor());
		System.out.println(itemsId);
		distributorItem.setItemsId(itemsId);
		if ( itemsId == 0) {
				this.insertIntoItems(item);
				distributorItem.setItemsId(this.getItemIdByDistributor(item.getDistributor()));
				result=itemDAO.insertItem(distributorItem);
		} else {
			
			result=itemDAO.insertItem(distributorItem);
		}
		return result;
	}

	public void insertIntoItems(ItemsBean item) {
		sql = "INSERT INTO items (distributor,category) VALUES (?,?)";
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, item.getDistributor());
			ps.setString(2, item.getCategory());
			ps.executeUpdate();*/
			row=this.jdbcTemplate.update(sql,item.getDistributor(),item.getCategory());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public String deleteItemByDistributor(String distributor) {
		sql = "DELETE FROM items WHERE distributor=?";
		try {
			
			row = this.itemDAO.deleteItem(distributor);
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			result = "FAIL";
		}
		return result;
	}

	public int getItemIdByDistributor(String distributor) {
		sql = "SELECT id FROM items WHERE distributor=?";
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			rs = ps.executeQuery();
			if (rs.next()) {
				row = rs.getInt("id");
			}*/
			ItemsBean item=this.jdbcTemplate.queryForObject(sql,new ItemsRowMapper(),distributor);
			return item.getId();
		} catch (Exception e) {
			return 0;
		}
		
	}

	public List<ItemsBean> getItemsByDistributor(String distributor) {
		sql = "SELECT * FROM items WHERE distributor=?";
		List<ItemsBean> items = new ArrayList();
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			rs = ps.executeQuery();
			while (rs.next()) {
				ItemsBean item = new ItemsBean();
				item.setId(rs.getInt(1));
				item.setDistributor(rs.getString(2));
				items.add(item);
			}*/
			items = this.jdbcTemplate.query(sql, new ItemsRowMapper(),distributor);
			if (items.isEmpty()) {
				return null;
			} else {
				return items;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<DistributorItemBean> getAllDistributorItems(String distributor) {
		List<DistributorItemBean> items = new ArrayList();
		itemDAO=new DistributorItemDAO();
		int itemsId=this.getItemIdByDistributor(distributor);
		items=itemDAO.getAllItems(itemsId);
		if(items.isEmpty()) {
			return null;
		}
		return items;
		
	}
	
	public List<ItemsBean> getAllItems() {
		List<ItemsBean> items=new ArrayList<ItemsBean>();
		sql = "SELECT * FROM items";
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ItemsBean item = new ItemsBean();
				item.setId(rs.getInt(1));
				item.setDistributor(rs.getString(2));
				item.setCategory(rs.getString(3));
				items.add(item);
			}*/
			items = this.jdbcTemplate.query(sql, new ItemsRowMapper());
			if (items.isEmpty()) {
				return null;
			} else {
				return items;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public int getIdByCategory(String category,String distributor) {
		sql = "SELECT id FROM items WHERE distributor=? AND category=?";
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			ps.setString(2,category);
			rs = ps.executeQuery();
			if (rs.next()) {
				row = rs.getInt("id");
			}*/
			ItemsBean item=this.jdbcTemplate.queryForObject(sql,new ItemsRowMapper(),category,distributor);
			return item.getId();
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public String getDistributorName(int id) {
		sql = "SELECT distributor FROM items WHERE id=?";
		
		try {
			/*con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				distributor=rs.getString("distributor");
			}*/
			ItemsBean distributor = this.jdbcTemplate.queryForObject(sql, new ItemsRowMapper(),id);
			return distributor.getDistributor();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateDistributorItem(String distributor,List<ParticularOrderProductBean> products) {
		int itemsId=this.getItemIdByDistributor(distributor);
		System.out.println(distributor+":"+itemsId);
		for(ParticularOrderProductBean product:products) {
		itemDAO.updateItemAfterOrder(itemsId, product.getItemName(), product.getQuantity());
		}
	}
	
	
}
