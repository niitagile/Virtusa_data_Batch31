<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%@page import="java.util.*,java.io.*" %>
    <%@page import="com.cityclassifiedandsearch.bean.*,com.cityclassifiedandsearch.dao.*,com.cityclassifiedandsearch.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
*{
	margin:0;
	padding:0;
}
body{
	background-image: url('background.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;  
  background-size: cover;
} 
.topnav {
  overflow: visible;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active,.topnav-right a {
 background-color:#72A0C1;
  color: white;
}

.topnav-right {
  float: right;
}
.dropbtn {
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: black;
  opacity:0.7;
  color:white;
  width:500px;
  height:100px; 
  z-index: 1;
}

.dropdown:hover .dropdown-content {display: inherit;}

.dropdown:hover .dropbtn {background-color:#72A0C1 ;}

select{
  width: 70%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text], input[type=password],input[type=email],input[type=number]  {
  width: 70%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus,input[type=email]:focus,input[type=number]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
button {
  background-color: #72A0C1;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100px;
}

button:hover {
  opacity:1;
}


/* Float cancel and signup buttons and add an equal width */
 .signupbtn {
  float: left;
  width: 50%;
  align-self: center; 
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: transparent;
  padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}
 
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: #f1f1f1;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
.display{
background-color: black;
opacity: 0.9;
color:white;
width: 1430px;
height:1000px;
padding: 50px;
margin: 20px;
}
.classified{
background:white;
color:black;
padding: 20px 30px 50px;
}
</style>
</head>
<body>
<%
response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
response.setHeader("Pragma","no-cache");

if(session.getAttribute("user")==null)
{
	response.sendRedirect("index.jsp");
}
%>
	<div class="topnav">
  		<a class="active" href="welcome.jsp">Home</a>
  		<div class="dropdown">
  			<div class="dropbtn">About</div>
  			<div class="dropdown-content">
    		Welcome to CitY Classified search!<br>
    		Register yourselves to post classifieds<br>
    		Get Latest Informations in and around your city<br>    			
    	</div>
		</div>
		<a href="citydetails.jsp">View City Details</a>
  		<a href="myclassifieds.jsp">View My Classifieds</a>
  		<a href="logout">Logout</a>  		
  			<div class="topnav-right">
    			<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Post Classifieds</button>
    			<%if(MyUtil.getLoginedUser(session).getRole().equalsIgnoreCase("admin")) {%>
    			<button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Post CityDetails</button>
    			<%} %>
 			</div>
	</div>
	
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="PostClassified" method="post" >
    <div class="container">
      <h1>Post Classified</h1>
      
		<select id="classifiedCategory" name="classifiedCategory">
		<option value="buy">Buy</option>
		<option value="sell">Sell</option>
		<option value="rent">Rent</option>
		<option value="partime">Part Time Jobs</option>
		</select><br>
		<input type="text" id="classifiedTitle" placeHolder="Title" name="classifiedTitle" required>
		
		<input type="text" id="description" placeholder="Description" name="description" required >
		 
      <div class="clearfix">
              <button type="submit" class="signupbtn">Post</button>
      </div>
    </div>
  </form>
 </div>
 <div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="AddCity" method="post"  >
    <div class="container">
      <h1>Post CityDetails</h1>
      
		<select id="category" name="category">
		<option value="mall">Mall</option>
		<option value="hospital">Hospital</option>
		<option value="school">School</option>
		<option value="hotel">Hotel</option>
		</select>
		
		<input type="text" id="name" placeholder="Name" name="name" required>
		
		<input type="text" id="address" placeholder="Adress" name="address" required >
		
		<input type="text" id="cityName" placeholder="city" name="city" required >
		
		<input type="text" id="link" placeholder="Link" name="link" required  >
		
		 
      <div class="clearfix">
              <button type="submit" class="signupbtn">Post</button>
      </div>
    </div>
  </form>
 </div>
</body>
</html>