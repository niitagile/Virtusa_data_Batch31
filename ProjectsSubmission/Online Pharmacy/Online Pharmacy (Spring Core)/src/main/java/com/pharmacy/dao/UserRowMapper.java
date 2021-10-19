package com.pharmacy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pharmacy.bean.UserBean;

public class UserRowMapper implements RowMapper<UserBean>{

	public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBean user = new UserBean();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("phoneNumber"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		return user;
	}

}
