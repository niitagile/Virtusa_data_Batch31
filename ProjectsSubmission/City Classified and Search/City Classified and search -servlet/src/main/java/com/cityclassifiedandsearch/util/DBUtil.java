package com.cityclassifiedandsearch.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CitySearch", "root", "abc");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
