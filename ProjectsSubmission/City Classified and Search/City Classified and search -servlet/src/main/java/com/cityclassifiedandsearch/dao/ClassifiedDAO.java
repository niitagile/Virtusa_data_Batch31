package com.cityclassifiedandsearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.util.DBUtil;

public class ClassifiedDAO {
	public static boolean addClassified(Classified classified) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Insert into classified (userId, classifiedCategory,classifiedTitle,description) values(?, ?, ?, ?);"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setInt(1,classified.getUserId() );
	        pstm.setString(2, classified.getClassifiedCategory());
	        pstm.setString(3, classified.getClassifiedTitle());
	        pstm.setString(4, classified.getDescription());
	        pstm.executeUpdate();
	        return true;
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		return false;
	}
	public static boolean updateClassified(Classified classified) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Update classified set classifiedCategory=?, classifiedTitle=?, description=? where classifiedId=?;"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setString(1, classified.getClassifiedCategory());
	        pstm.setString(2, classified.getClassifiedTitle());
	        pstm.setString(3, classified.getDescription());
	        pstm.setInt(4, classified.getClassifiedId());
	        pstm.executeUpdate();
	        return true;
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		return false;
	}
	public static boolean deleteClassified(int classifiedId) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Delete from classified where classifiedId=?;"; 
	        PreparedStatement pstm = con.prepareStatement(query);
	        pstm.setInt(1, classifiedId);
	        pstm.executeUpdate();
	        return true;
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
		return false;
	}
	public static List<Classified> getAllClassified() {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Select * from classified";
			List<Classified> classifieds = new ArrayList<Classified>();
			PreparedStatement st=con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Classified classified = new Classified();
				classified.setClassifiedId(rs.getInt("classifiedId"));
				classified.setUserId(rs.getInt("userId"));
				//setting user
				User user=UserDAO.findByUserId(rs.getInt("userId"));
				classified.setClassifiedCategory(rs.getString("classifiedCategory"));
				classified.setDescription(rs.getString("description"));
				classified.setClassifiedTitle(rs.getString("classifiedTitle"));	
				classified.setUser(user);
				classifieds.add(classified);
			}
			return classifieds;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Classified> getUserClassified(int userId) {
		try {
			Connection con = DBUtil.getConnection();
			String query = "Select * from classified where userId=?";
			List<Classified> classifieds = new ArrayList<Classified>();
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Classified classified = new Classified();
				classified.setClassifiedId(rs.getInt("classifiedId"));
				classified.setUserId(userId);
				User user=UserDAO.findByUserId(rs.getInt("userId"));
				classified.setClassifiedCategory(rs.getString("classifiedCategory"));
				classified.setDescription(rs.getString("description"));
				classified.setClassifiedTitle(rs.getString("classifiedTitle"));
				classified.setUser(user);
				classifieds.add(classified);
			}
			return classifieds;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
