package com.cityclassifiedandsearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cityclassifiedandsearch.bean.Citydetails;
import com.cityclassifiedandsearch.util.DBUtil;

public class CityDAO {
	public static boolean addCity(Citydetails city) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Insert into Citydetails (userId, category,name,address, city, link) values(?, ?, ?, ?, ?, ?);"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setInt(1, city.getUserId());
	        pstm.setString(2, city.getCategory());
	        pstm.setString(3, city.getName());
	        pstm.setString(4, city.getAddress());
	        pstm.setString(5, city.getCity());
	        pstm.setString(6, city.getLink());
	        pstm.executeUpdate();
	        return true;
		}
        catch(SQLException e) {
        	e.printStackTrace();
        }
		return false;
	}
	public static boolean updateCity(Citydetails city) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Update Citydetails set category=?, name=?, address=?,city=?, link=? where cityId=?;"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setString(1, city.getCategory());
	        pstm.setString(2, city.getName());
	        pstm.setString(3, city.getAddress());
	        pstm.setString(4, city.getCity());
	        pstm.setString(5, city.getLink());
	        pstm.setInt(6, city.getCityId());
	        pstm.executeUpdate();
	        return true;
		}
        catch(SQLException e) {
        	e.printStackTrace();
        }
		return false;
	}
	public static boolean deleteCity(int cityId) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Delete from citydetails where cityId=?;"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setInt(1, cityId);
	        pstm.executeUpdate();
	        return true;
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
		return false;
	}
	public static List<Citydetails> getAllCity() {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Select * from citydetails";
			List<Citydetails> cities = new ArrayList<Citydetails>();
			PreparedStatement st=con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Citydetails city = new Citydetails();
				city.setCityId(rs.getInt("cityId"));
				city.setCategory(rs.getString("category"));
				city.setName(rs.getString("name"));
				city.setAddress(rs.getString("address"));
				city.setCity(rs.getString("city"));
				city.setLink(rs.getString("link"));
				cities.add(city);
			}
			return cities;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
