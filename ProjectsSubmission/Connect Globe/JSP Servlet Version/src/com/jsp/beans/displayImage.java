package com.jsp.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.controller;

/**
 * Servlet implementation class displayImage
 */
@WebServlet("/displayImage")
public class displayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int useId;
	private int userId;
	private int pId;
	private String tagLine;
	private String base64Image;
	private Connection con;
	private controller d=new controller();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection con=d.connect();
			System.out.println("hello..madam");
	    	Statement stmt=con.createStatement();
//	    	response.setContentType("image/jpeg");
	    	Cookie ck[]=request.getCookies();
			for(int i=0;i<ck.length;i++){
				if(ck[i].getName().equals("Id")){
					useId=Integer.parseInt(ck[i].getValue());
				}
			}
			request.setAttribute("userId", useId);
			System.out.println("userId "+useId);
			Blob image=null;
			byte[] imgData=null;
			ResultSet rs=stmt.executeQuery("select * from posts where userId='"+useId+"'");
			List<String> list=new ArrayList<>();
			if(rs.next()){
				pId=rs.getInt("pId");
				userId=rs.getInt("userId");
				System.out.println("inside while"+userId);
				tagLine=rs.getString("tagLine");
				image=rs.getBlob("fileStore");
				imgData=image.getBytes(1, (int)image.length());
				OutputStream output=response.getOutputStream();
				response.setContentType("image/jpeg");
				output.write(imgData);
				output.flush();
				output.close();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

}
