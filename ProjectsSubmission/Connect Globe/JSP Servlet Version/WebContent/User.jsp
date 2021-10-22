<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.bod{
width:80%;
padding-left:30%;
}
.con{
background-color:#CCCCFF;
text-align:center;
padding-top:5%;
padding-bottom:5%;
width:50%;
border-radius:15%;
}
.center {
  margin-left: auto;
  margin-right: auto;
 
}
</style>
</head>
<body>
<%@ include file="heading.jsp" %> 
<div class="bod">
<div class="container my-5 con">
<h1>Registration</h1>
<form action="regServlet" method="post">
<table class="center">
<tr><td>Full Name :</td><td><input type="text" name="username" required/></td></tr>
<tr><td>Email :</td><td><input type="text" name="email" required/></td></tr>
<tr><td>Password :</td><td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
		      title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
		      required/></td></tr>
<tr><td>Mobile :</td><td><input type="text" name="mobile" required/></td></tr>
<tr><td>Gender :</td><td><input type="radio" name="gender" value="male" required> Male</td><td><input type="radio" name="gender" value="female"> Female</td></tr>
<tr><td></td><td><input type="hidden" name="role" value="user"></td></tr>
<tr><td colspan="2"><input type="submit" value="Register"/></td></tr>
</table>
</form></div></div>
</body>
</html>