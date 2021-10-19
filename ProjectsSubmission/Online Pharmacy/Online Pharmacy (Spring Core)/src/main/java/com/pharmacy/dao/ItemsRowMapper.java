package com.pharmacy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pharmacy.bean.ItemsBean;

public class ItemsRowMapper implements RowMapper<ItemsBean>{

	public ItemsBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ItemsBean item = new ItemsBean();
		item.setId(rs.getInt("id"));
		item.setCategory(rs.getString("category"));
		item.setDistributor(rs.getString("distributor"));
		return item;
	}

}
