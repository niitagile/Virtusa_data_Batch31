package com.jsp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.StringUtils;

import com.jsp.beans.detailsRegister;
import com.jsp.classes.MyReport;
import com.jsp.classes.OverAllPosts;
import com.jsp.classes.SuggestionClass;
import com.jsp.classes.commnetModel;
import com.jsp.classes.registrationClass;
import com.jsp.classes.blobModel;
import com.jsp.util.DbConnection;

@MultipartConfig(maxFileSize = 4194304)
public class controller {
	private Connection con;
	private int useId;
	private int pId;
	private int userId;
	private String tagLine;
	private int uId;
	private String base64Image="";
	private String self="";
	private int ImpId;
	private int Identify;
    public controller(){
    	
    }
    public Connection connect(){
    	con=DbConnection.getDBConn();
		System.out.println("Connected");
		return con;
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	System.out.print("next");
    	String fulname=request.getParameter("username");
    	String email=request.getParameter("email");
    	String password=request.getParameter("password");
    	String mobile=request.getParameter("mobileNumber");
    	String gender=request.getParameter("gender");
    	String rol="USER";
    	Statement statement=con.createStatement();
    	int i=statement.executeUpdate("insert into registration (fullname,email,password,mobileNumber,gender,roles) values ('"+fulname+"','"+email+"','"+password+"','"+mobile+"','"+gender+"','"+rol+"')");
    	if(i>0){
    		response.sendRedirect("adminServlet");
    	}
    }
    public void registeration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	System.out.print("next");
    	String fulname=request.getParameter("username");
    	String email=request.getParameter("email");
    	String password=request.getParameter("password");
    	String mobile=request.getParameter("mobileNumber");
    	String gender=request.getParameter("gender");
    	String rol="USER";
    	Statement statement=con.createStatement();
    	int i=statement.executeUpdate("insert into registration (fullname,email,password,mobileNumber,gender,roles) values ('"+fulname+"','"+email+"','"+password+"','"+mobile+"','"+gender+"','"+rol+"')");
    	if(i>0){
    		RequestDispatcher dispatcher=request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request,response);
    	}
    }
    public void loginCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<detailsRegister> l=new ArrayList<>();
    	String email=request.getParameter("email");
    	String password=request.getParameter("psw");
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	ResultSet rs=stmt.executeQuery("select * from registration where email='"+email+"'");
    	if(rs.next()==false) {
    		request.setAttribute("error","Invalid Password or invalid username!!");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request,response);
    	}
    	//rs.next();
    	System.out.print("checking");
		String CPassword = rs.getString("password");
		System.out.println(password);
		if(CPassword.equals(password)){
			ResultSet rw=stmt2.executeQuery("select * from registration where email='"+email+"'");
			while(rw.next()){	
				uId=rw.getInt("userId");
				String fullname=rw.getString("fullname");
				String Email=rw.getString("email");
				String mobile=rw.getString("mobileNumber");
				String gen=rw.getString("gender");
				String rol=rw.getString("roles");
				detailsRegister d=new detailsRegister(uId,fullname,Email,mobile,gen,rol);
				l.add(d);
				String id=String.valueOf(uId);
				Cookie ck18=new Cookie("Id",id);
				Cookie ck19=new Cookie("roles",rol);
				response.addCookie(ck18);
				response.addCookie(ck19);
			}
			request.setAttribute("details", l);
			response.sendRedirect("homeServlet");
		}else{
			request.setAttribute("error","Invalid Password or invalid username!!");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request,response);
		}
    }
    public void uploadPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	PreparedStatement pstmt = null;
    	FileInputStream fis=null;
    	ResultSet rs=null;
    	int i=0;
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String tag=request.getParameter("tag");
    	Part filePart=request.getPart("da");
    	InputStream inputStream=null;
    	if(filePart != null){
    		System.out.println(filePart.getName());
    		System.out.println(filePart.getSize());
    		System.out.println(filePart.getContentType());
    		inputStream=filePart.getInputStream();
    	}
    	pstmt=con.prepareStatement("insert into posts (userId,fileStore,tagLine)"+"values(?,?,?)");
    	pstmt.setInt(1, userId);
    	pstmt.setBinaryStream(2,inputStream);
    	pstmt.setString(3, tag);
    	i=pstmt.executeUpdate();
    	if(i>0){
    		System.out.println("File saved Successfully");
    	}
    	response.sendRedirect("MyPosts");
    }
    public void getMyPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<blobModel> li=new ArrayList<>();
    	System.out.println("hello..madam");
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();
//    	response.setContentType("image/jpeg");
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
		while(rs.next()){
			pId=rs.getInt("pId");
			userId=rs.getInt("userId");
			tagLine=rs.getString("tagLine");
			image=rs.getBlob("fileStore");
			imgData=image.getBytes(1, (int)image.length());
			byte[] by=Base64.getEncoder().encode(imgData);
			System.out.println(by);
			base64Image=new String(by,"UTF-8");
			blobModel b=new blobModel(pId,userId,base64Image,tagLine);
			li.add(b);
		}
		ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+useId+"'");
		rw.next();
		String fullname=rw.getString("fullname");
		request.setAttribute("name", fullname);
		request.setAttribute("postList", li);
		ResultSet r=stmt3.executeQuery("select roles from registration where userId='"+useId+"'");
		r.next();
		String role=r.getString("roles");
		System.out.print("roles"+role);
		request.setAttribute("roles", role);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/Posts.jsp");
		dispatcher.forward(request,response);
    }
    public void commentPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int pId=Integer.parseInt(request.getParameter("postId"));
    	System.out.println("pid"+pId);
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String comment=request.getParameter("comment");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("Insert into answersTable (pId,userId,reply) values ('"+pId+"','"+userId+"','"+comment+"')");
    	if(i>0){
    		request.setAttribute("success", "Commented successfully");
    	}else{
    		request.setAttribute("error", "Comment is not saved");
    	}
    	response.sendRedirect("homeServlet");
    	
    }
    public void getAllPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	System.out.println("AllPosts....");
    	List<OverAllPosts> l=new ArrayList<>();
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();

    	Blob image=null;
		byte[] imgData=null;
    	ResultSet rs=stmt.executeQuery("select * from posts");
    	while(rs.next()){
    		pId=rs.getInt("pId");
			userId=rs.getInt("userId");
			System.out.println("user "+userId);
			tagLine=rs.getString("tagLine");
			image=rs.getBlob("fileStore");
			imgData=image.getBytes(1, (int)image.length());
			byte[] by=Base64.getEncoder().encode(imgData);
			System.out.println(by);
			base64Image=new String(by,"UTF-8");
			ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+userId+"'");
			rw.next();
			String name=rw.getString("fullname");
			OverAllPosts b=new OverAllPosts(pId,userId,base64Image,tagLine,name);
			System.out.println(b.toString());
			l.add(b);
    	}
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				ImpId=Integer.parseInt(ck[i].getValue());
			}
		}
		request.setAttribute("ImpId", ImpId);
		System.out.print("id"+ImpId);

		ResultSet r=stmt3.executeQuery("select roles from registration where userId='"+ImpId+"'");
		r.next();
		String role=r.getString("roles");
		System.out.print("roles"+role);
		request.setAttribute("roles", role);
    	request.setAttribute("AllPostsList", l);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request,response);
    }
    public void getAllComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<commnetModel> l=new ArrayList<>();
    	List<blobModel> li=new ArrayList<>();
    	System.out.print("comments");
    	int id=Integer.parseInt(request.getParameter("id"));
    	System.out.println("id"+id);
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();
    	ResultSet rs=stmt.executeQuery("select * from answersTable where pId='"+id+"'");
    	while(rs.next()){
    		int UId=rs.getInt("userId");
    		System.out.println(UId);
    		String reply=rs.getString("reply");
    		ResultSet rt=stmt2.executeQuery("select fullname from registration where userId='"+UId+"'");
    		rt.next();
    		String name=rt.getString("fullname");
    		System.out.println("n"+name);
    		commnetModel c=new commnetModel(name,reply);
    		l.add(c);
    	}
    	Blob image=null;
    	ResultSet rw=stmt3.executeQuery("select * from posts where pId='"+id+"'");
    	System.out.print("r"+rw);
    	rw.next();
    	int userId=rw.getInt("userId");
    	String tag=rw.getString("tagLine");
    	image=rw.getBlob("fileStore");
		byte[] imgData=image.getBytes(1, (int)image.length());
		byte[] by=Base64.getEncoder().encode(imgData);
		System.out.println(id+" "+userId+" "+tag);
		String base64=new String(by,"UTF-8");
		blobModel b=new blobModel(id,userId,base64,tag);
		li.add(b);
		Statement stmt4=con.createStatement();
		ResultSet r=stmt4.executeQuery("select roles from registration where userId='"+userId+"'");
		r.next();
		String role=r.getString("roles");
		System.out.print("roles"+role);
		request.setAttribute("roles", role);
		request.setAttribute("post", li);
    	request.setAttribute("comments", l);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/Comment.jsp");
		dispatcher.forward(request,response);
    	
    }
    public void getMyReports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<MyReport> l=new ArrayList<>();
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				useId=Integer.parseInt(ck[i].getValue());
			}
		}
		request.setAttribute("userId", useId);
		System.out.print("userId"+ useId);
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from issues where userId='"+useId+"'");
		while(rs.next()){
			int rId=rs.getInt("rId");
			int userId=rs.getInt("userId");
			String issue=rs.getString("issue");
			MyReport r=new MyReport(rId,userId,issue," ");
			l.add(r);
		}
		ResultSet rw=stmt.executeQuery("select fullname from registration where userId='"+useId+"'");
		rw.next();
		self=rw.getString("fullname");
		Statement stmt3=con.createStatement();

		ResultSet r=stmt3.executeQuery("select roles from registration where userId='"+useId+"'");
		r.next();
		String role=r.getString("roles");
		System.out.print("roles"+role);
		request.setAttribute("roles", role);
		request.setAttribute("ReportList", l);
		request.setAttribute("name", self);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/MyReports.jsp");
		dispatcher.forward(request,response);
    }
    public void addReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int id=Integer.parseInt(request.getParameter("rId"));
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String issue=request.getParameter("issue");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("insert into issues (rId,userId,issues) values ('"+id+"','"+userId+"','"+issue+"')");
    	if(i>0){
    		response.sendRedirect("MyReports");
    		request.setAttribute("success", "Report posted successfully");
    	}
    }
    public void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<registrationClass> l=new ArrayList<>();
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				Identify=Integer.parseInt(ck[i].getValue());
			}
		}
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from registration where userId='"+Identify+"'");
		rs.next();
		int id=rs.getInt("userId");
		String fullname=rs.getString("fullname");
		String email=rs.getString("email");
		String mobile=rs.getString("mobileNumber");
		String gender=rs.getString("gender");
		String role=rs.getString("roles");
		String password=rs.getString("password");
		registrationClass r=new registrationClass(id,fullname,email,mobile,gender,role,password);
		l.add(r);
		
		request.setAttribute("roles", role);
		request.setAttribute("userDetails", l);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request,response);
    }
    public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<registrationClass> l=new ArrayList<>();
		Identify=Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("id"));
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from registration where userId='"+Identify+"'");
		rs.next();
		int id=rs.getInt("userId");
		String fullname=rs.getString("fullname");
		String email=rs.getString("email");
		String mobile=rs.getString("mobileNumber");
		String gender=rs.getString("gender");
		String role=rs.getString("roles");
		String password=rs.getString("password");
		registrationClass r=new registrationClass(id,fullname,email,mobile,gender,role,password);
		l.add(r);
		request.setAttribute("roles", role);
		request.setAttribute("userDetails", l);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request,response);
    }
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<registrationClass> l=new ArrayList<>();
		Identify=Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("id"));
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from registration where id='"+Identify+"'");
		rs.next();
		int id=rs.getInt("id");
		String fullname=rs.getString("fullname");
		String email=rs.getString("email");
		String mobile=rs.getString("mobile");
		String gender=rs.getString("gender");
		String role=rs.getString("roles");
		String password=rs.getString("password");
		registrationClass r=new registrationClass(id,fullname,email,mobile,gender,role,password);
		l.add(r);
		request.setAttribute("userDetails", l);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/edit.jsp");
		dispatcher.forward(request,response);
    }
    public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<registrationClass> l=new ArrayList<>();
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				Identify=Integer.parseInt(ck[i].getValue());
			}
		}
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from registration");
		while(rs.next()){
			int id=rs.getInt("userId");
			String fullname=rs.getString("fullname");
			String email=rs.getString("email");
			String mobile=rs.getString("mobileNumber");
			String gender=rs.getString("gender");
			String role=rs.getString("roles");
			String password=rs.getString("password");
			registrationClass r=new registrationClass(id,fullname,email,mobile,gender,role,password);
			l.add(r);
    	}
		request.setAttribute("userDetails", l);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(request,response);
    }
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int id=Integer.parseInt(request.getParameter("id"));
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
    	String phone=request.getParameter("phone");
    	String gend=request.getParameter("gend");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("update registration set fullname='"+name+"',email='"+email+"',mobileNumber='"+phone+"',gender='"+gend+"' where userId="+id+"");
    	if(i>0){
    		response.sendRedirect("profileServlet");
    	}
    }
    public void getAllReports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<MyReport> l=new ArrayList<>();
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				userId=Integer.parseInt(ck[i].getValue());
			}
		}
    	System.out.print(userId);
    	
    	ResultSet rs=stmt.executeQuery("select * from issues");
    	while(rs.next()){
    		int rId=rs.getInt("rId");
    		int userId=rs.getInt("userId");
    		String issue=rs.getString("issue");
    		ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+userId+"'");
    		rw.next();
    		String name=rw.getString("fullname");
    		MyReport r=new MyReport(rId,userId,issue,name);
    		l.add(r);
    	}
    	request.setAttribute("AllReports", l);
		

    	ResultSet r=stmt3.executeQuery("select roles from registration where userId='"+userId+"'");
		r.next();
		String role=r.getString("roles");
		System.out.print("roles"+role);
		request.setAttribute("roles", role);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/Reports.jsp");
		dispatcher.forward(request,response);
    }
    public void suggestToReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int rId=Integer.parseInt(request.getParameter("rId"));
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String suggest=request.getParameter("suggest");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("insert into suggestions (rId,userId,suggestion) values ('"+rId+"','"+userId+"','"+suggest+"')");
    	if(i>0){
    		response.sendRedirect("AllReports");
    		System.out.println("Suggestions saved");
    	}
    }
    public void getAllSuggestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<SuggestionClass> l=new ArrayList<>();
    	int rId=Integer.parseInt(request.getParameter("id"));
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();
    	ResultSet rs=stmt.executeQuery("select * from suggestions where rId='"+rId+"'");
    	while(rs.next()){
    		int userId=rs.getInt("userId");
    		String suggest=rs.getString("suggestion");
    		ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+userId+"'");
    		rw.next();
    		String name=rw.getString("fullname");
    		SuggestionClass s=new SuggestionClass(userId,suggest,name);
    		System.out.println(s.toString());
    		l.add(s);
    	}
    	System.out.println(l);
    	ResultSet rq=stmt3.executeQuery("select *  from issues where rId='"+rId+"'");
    	rq.next();
    	String issue=rq.getString("issue");
    	System.out.println("issue : "+issue);
    	request.setAttribute("suggestionList", l);
    	request.setAttribute("issue", issue);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/suggestions.jsp");
		dispatcher.forward(request,response);
    	
    }
    public void uploadReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String issue=request.getParameter("issue");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("insert into issues (userId,issue) values ('"+userId+"','"+issue+"')");
    	if(i>0){
    		response.sendRedirect("MyReports");
    		request.setAttribute("success", "Report posted successfully");
    	}
    }
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Identify=Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("id"));
		Statement stmt=con.createStatement();
		int rs=stmt.executeUpdate("delete from registration where userId='"+Identify+"'");
		response.sendRedirect("adminServlet");
		
	}
	public void deletep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Identify=Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("id"));
		Statement stmt=con.createStatement();
		int rs1=stmt.executeUpdate("delete from answerstable where pId='"+Identify+"'");

		int rs=stmt.executeUpdate("delete from posts where pId='"+Identify+"'");

		response.sendRedirect("homeServlet");
		
	}
	public void deleter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Identify=Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("id"));
		Statement stmt=con.createStatement();
		int rs1=stmt.executeUpdate("delete from suggestions where rId='"+Identify+"'");

		int rs=stmt.executeUpdate("delete from issues where rId='"+Identify+"'");

		response.sendRedirect("AllReports");
		
	}
}
