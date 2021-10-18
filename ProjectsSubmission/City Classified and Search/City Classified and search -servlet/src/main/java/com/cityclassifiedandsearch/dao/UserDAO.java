package com.cityclassifiedandsearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.util.DBUtil;

public class UserDAO {
	public static void addUser(User user) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "Insert into user (userName, userEmail, password, mobile,userAddress,userCity,Role,enabled) values(?, ?, ?, ?,?,?,?,?);";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, user.getUserName());
        pstm.setString(2,user.getUserEmail());
        pstm.setString(3,user.getPassword());
        pstm.setString(4,user.getMobile());   
        pstm.setString(5,user.getUserAddress());
        pstm.setString(6,user.getUserCity());   
        pstm.setString(7,user.getRole());  
        pstm.setBoolean(8,user.isEnabled());   
        pstm.executeUpdate();
	}
	public static User check(String userEmail, String password) {		
		try {
			Connection con = DBUtil.getConnection();
			String query = "Select * from user where userEmail=? and password=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, userEmail);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(password);
				user.setUserEmail(userEmail);
				user.setEnabled(rs.getBoolean("enabled"));
				user.setMobile(rs.getString("mobile"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserCity(rs.getString("userCity"));
				user.setRole(rs.getString("Role"));
				return user;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static User findByUserId(int userId) throws SQLException {
		Connection con = DBUtil.getConnection();
		String query = "Select * from User where userId=?"; 
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setInt(1, userId);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()){
			User user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setUserEmail(rs.getString("userEmail"));
			user.setEnabled(rs.getBoolean("enabled"));
			user.setMobile(rs.getString("mobile"));
			user.setUserAddress(rs.getString("userAddress"));
			user.setUserCity(rs.getString("userCity"));
			user.setRole(rs.getString("Role"));
			return user;
		}
		return null;
	}
	public static boolean findEmail(String userEmail) throws SQLException {
		Connection con = DBUtil.getConnection();
		String query = "Select * from User where userEmail=?"; 
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setString(1, userEmail);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()){
			return true;
		}
		return false;
	}
}
