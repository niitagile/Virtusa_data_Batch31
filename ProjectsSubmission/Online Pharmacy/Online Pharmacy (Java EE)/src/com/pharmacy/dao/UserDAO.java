package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.bean.UserBean;
import com.pharmacy.util.DBUtil;

public class UserDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;

	public String addUser(UserBean user) {
		sql = "INSERT INTO users (username,email,phoneNumber,password,role,name) VALUES (?,?,?,?,?,?)";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setNString(2, user.getEmail());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			ps.setString(6, user.getName());
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;

	}

	public String updateUser(UserBean user, int id) {
		sql = "UPDATE users SET username=?,email=?,phoneNumber=?,password=?,role=? WHERE id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			ps.setInt(6, id);
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAILED";

		} catch (Exception e) {
			e.printStackTrace();
			result = "FAILED";
		}
		return result;

	}

	public UserBean getUserById(int id) {
		sql = "SELECT * FROM users WHERE id=?";
		UserBean user = new UserBean();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				user.setRole(rs.getString(7));
			}
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public String updateUser(UserBean user, String oldUsername, String oldPassword) {
		sql = "UPDATE users SET username=?,email=?,phoneNumber=?,password=?,name=? WHERE username=? AND password=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getName());
			ps.setString(6, oldUsername);
			ps.setString(7, oldPassword);
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAILED";

		} catch (Exception e) {
			result = "FAILED";
		}
		return result;

	}

	public String deleteUserByUsername(String username, String password) {
		sql = "DELETE FROM users WHERE username=? AND password=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;
	}

	public String deleteUserByUsername(int id) {
		sql = "DELETE FROM users WHERE id=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			row = ps.executeUpdate();
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;
	}

	public List<UserBean> getAllUsers() {
		List<UserBean> users = new ArrayList<>();
		sql = "SELECT * FROM users";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				user.setRole(rs.getString(7));
				users.add(user);
			}
			if (users.isEmpty()) {
				return null;
			} else {
				return users;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public UserBean getUserByUsername(String username) {
		sql = "SELECT * FROM users WHERE username=?";
		UserBean user = new UserBean();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setRole(rs.getString(7));
			}
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public String getRole(String username) {
		sql = "SELECT role FROM users WHERE username=?";
		String role = "";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				role = rs.getString("role");
			}
		} catch (Exception e) {
			role = null;
		}
		return role;
	}

	public boolean authenticate(String username, String password) {
		sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
