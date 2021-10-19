package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.bean.DistributorItemBean;
import com.pharmacy.bean.ItemsBean;
import com.pharmacy.bean.ParticularOrderProductBean;
import com.pharmacy.util.DBUtil;

public class ItemsDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	DistributorItemDAO itemDAO;
	private String result;
	private int row;

	public ItemsDAO() {
		itemDAO = new DistributorItemDAO();
	}

	public String addItem(DistributorItemBean distributorItem, ItemsBean item) {
		int itemsId = this.getItemIdByDistributor(item.getDistributor());
		distributorItem.setItemsId(itemsId);
		if (itemsId == 0) {
			this.insertIntoItems(item);
			distributorItem.setItemsId(this.getItemIdByDistributor(item.getDistributor()));
			result = itemDAO.insertItem(distributorItem);
		} else {

			result = itemDAO.insertItem(distributorItem);
		}
		return result;
	}

	public void insertIntoItems(ItemsBean item) {
		sql = "INSERT INTO items (distributor,category) VALUES (?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, item.getDistributor());
			ps.setString(2, item.getCategory());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String deleteItemByDistributor(String distributor) {
		String query = "DELETE FROM items WHERE distributor=?";
		int itemsId = this.getItemIdByDistributor(distributor);
		result = itemDAO.deleteByItemsId(itemsId);
		if (result.equals("SUCCESS")) {
			try {
				con = DBUtil.getDBConn();
				ps = con.prepareStatement(query);
				ps.setString(1, distributor);
				row = ps.executeUpdate();
				result = (row > 0) ? "SUCCESS" : "FAILED";
			} catch (Exception e) {
				e.printStackTrace();
				result = "FAILED";
			}
		}
		else {
			result="FAILED";
		}
		return result;
	}

	public int getItemIdByDistributor(String distributor) {
		sql = "SELECT id FROM items WHERE distributor=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			rs = ps.executeQuery();
			if (rs.next()) {
				row = rs.getInt("id");
			}
		} catch (Exception e) {
			row = 0;
		}
		return row;
	}

	public List<ItemsBean> getItemsByDistributor(String distributor) {
		sql = "SELECT * FROM items WHERE distributor=?";
		List<ItemsBean> items = new ArrayList<>();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			rs = ps.executeQuery();
			while (rs.next()) {
				ItemsBean item = new ItemsBean();
				item.setId(rs.getInt(1));
				item.setDistributor(rs.getString(2));
				items.add(item);
			}
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
		List<DistributorItemBean> items = new ArrayList<>();
		itemDAO = new DistributorItemDAO();
		int itemsId = this.getItemIdByDistributor(distributor);
		items = itemDAO.getAllItems(itemsId);
		if (items.isEmpty()) {
			return null;
		}
		return items;

	}

	public List<ItemsBean> getAllItems() {
		List<ItemsBean> items = new ArrayList<ItemsBean>();
		sql = "SELECT * FROM items";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ItemsBean item = new ItemsBean();
				item.setId(rs.getInt(1));
				item.setDistributor(rs.getString(2));
				item.setCategory(rs.getString(3));
				items.add(item);
			}
			if (items.isEmpty()) {
				return null;
			} else {
				return items;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public int getIdByCategory(String category, String distributor) {
		sql = "SELECT id FROM items WHERE distributor=? AND category=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, distributor);
			ps.setString(2, category);
			rs = ps.executeQuery();
			if (rs.next()) {
				row = rs.getInt("id");
			}
		} catch (Exception e) {
			row = 0;
		}
		return row;
	}

	public String getDistributorName(int id) {
		sql = "SELECT distributor FROM items WHERE id=?";
		String distributor = "";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				distributor = rs.getString("distributor");
			}
			return distributor;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void updateDistributorItem(String distributor, List<ParticularOrderProductBean> products) {
		int itemsId = this.getItemIdByDistributor(distributor);
		for (ParticularOrderProductBean product : products) {
			itemDAO.updateItemAfterOrder(itemsId, product.getItemName(), product.getQuantity());
		}
	}

}
