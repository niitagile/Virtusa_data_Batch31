<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	try {
		String name = "";
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		name=(String)session.getAttribute("userId");
		if (name == "") {
			response.sendRedirect("/");
		} else {
		}
	} catch (Exception ex) {
		out.println(ex);
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="../home.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

 <style>
 	.log{
 		position:relative;
 		float:right;
 		right:10px;
 	}
 	.container{
	margin:0;
	float:down;
	}
	.container-fluid {
	  padding-right: 15px;
	  padding-left: 15px;
	  margin-right: auto;
	  margin-left: auto;
	}
	.r{
		background-color:red;
	}
	.pi{
		background-color:pink;
	}
	
	.topnav {
	  background-color: #333;
	  overflow: hidden;
	  top-padding:0%;
	  margin:0px;
	}
	
	/* Style the links inside the navigation bar */
	.topnav a {
	  float: left;
	  color: #f2f2f2;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	  font-size: 17px;
	}
	
	/* Change the color of links on hover */
	.topnav a:hover {
	  background-color: #ddd;
	  color: blue;
	}
	.heading{
		backgroud-color:blue;
	}
 	
</style> 
</head>
<body>
<%@ include file="heading.jsp" %> 
<div class="topnav">
  	<a class="active" href="/home">Home</a>
    <a href="/MyPosts">MyPosts</a>
  	<a href="/AllReports">Problems</a>
    <a href="/MyReports">MyProblems</a>
    <a href="/Profile">Profile</a>
    <a class="login" href="/logout">Logout</a>
    
</div>
</body>
</html>