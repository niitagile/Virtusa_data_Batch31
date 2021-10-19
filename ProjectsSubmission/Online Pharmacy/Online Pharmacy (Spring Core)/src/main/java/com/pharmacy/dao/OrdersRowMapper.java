package com.pharmacy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pharmacy.bean.OrdersBean;

public class OrdersRowMapper implements RowMapper<OrdersBean>{

	public OrdersBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrdersBean order = new OrdersBean();
		order.setOrderId(rs.getInt("order_id"));
		order.setUsername(rs.getString("username"));
		order.setOrderDate(rs.getString("orderDate"));
		order.setTotalQuantity(rs.getInt("total_quantity"));
		order.setPrice(rs.getFloat("price"));
		order.setAddress(rs.getString("address"));
		order.setPhoneNumber(rs.getString("phone_number"));
		order.setDistributorName(rs.getString("distributor_name"));
		order.setStatus(rs.getString("status"));
		
		return order;
	}

}
