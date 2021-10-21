package com.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection getDBConn()
	{
		Connection con=null;
		//write code here
		try{
			//Class.forName("oracle.jdbc.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		if(con==null)
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"connectGlobe","root","Radha@6420");
		}catch(Exception e){
			e.printStackTrace();
		}
			return con;
	}

}
