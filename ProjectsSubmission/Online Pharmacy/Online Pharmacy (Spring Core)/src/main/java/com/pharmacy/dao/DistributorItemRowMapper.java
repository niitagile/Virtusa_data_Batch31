package com.pharmacy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pharmacy.bean.DistributorItemBean;

public class DistributorItemRowMapper implements RowMapper<DistributorItemBean>{

	public DistributorItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		DistributorItemBean distributorBean = new DistributorItemBean();
		distributorBean.setId(rs.getInt("id"));
		distributorBean.setItemsId(rs.getInt("items_id"));
		distributorBean.setItemName(rs.getString("item_name"));
		distributorBean.setPrice(rs.getFloat("price"));
		distributorBean.setDescription(rs.getString("description"));
		distributorBean.setQuantity(rs.getInt("quantity"));
		return distributorBean;
	}

}
