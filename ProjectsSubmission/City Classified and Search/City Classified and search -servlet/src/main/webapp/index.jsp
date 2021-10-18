<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
     <%@page import="java.util.*,java.io.*" %>
    <%@page import="com.cityclassifiedandsearch.bean.*,com.cityclassifiedandsearch.dao.*,com.cityclassifiedandsearch.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CitY Search</title>
<style type="text/css">
body {
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

.topnav a.active {
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
width: 1000px;
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
	<div class="topnav">
  		<a class="active" href="#home">Home</a>
  		<div class="dropdown">
  			<div class="dropbtn">About</div>
  			<div class="dropdown-content">
    		Welcome to CitY Classified search!<br>
    		Register yourselves to post classifieds<br>
    		Get Latest Informations in and around your city<br>    			
    	</div>
		</div>
  		<a href="#contact">Contact</a>
  			<div class="topnav-right">
    			<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign In</button>
    			<button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Sign Up</button>
 			</div>
	</div>
	

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="Login" method="post" onsubmit="return logincheck()">
    <div class="container">
      <h1>Sign In</h1>
      
		<input type="email" placeholder="Email" name="email" required>
		<br><span id=mail></span><br>
		<input type="password" placeholder="password" name="password" required><br>
		<h6 style="color:red">${error}</h6>
      <div class="clearfix">
              <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>

<div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="Register" method="post" onsubmit="return check()">
    <div class="container">
      <h1>Sign Up</h1>
      
      	<input type="text" id="userName" placeholder="User Name" name="userName" required>
		<span id=name></span>
		
		<input type="text" id="userEmail" placeholder="Email" name="userEmail" required  >
		<span id=email></span>
		<input type="text" id="userAddress" placeholder="Address" name="userAddress" required >
		
		<input type="text" id="userCity" placeholder="City" name="userCity" required >
		
		<input type="number" id="mobile" placeholder="Mobile " name="mobile" required  >
		<span id=Mobile></span>
		
		<input type="password" id="password" placeholder="Password " name="password" required  >
		
		<input type="password" id="confirmPassword" placeholder="Confirm Password " name="confirmPassword" required >
		<span id=message></span>
		<h6 style="color:red">${error}</h6>
      <div class="clearfix">
              <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>

<div class=display>
 <%List<Classified> list=ClassifiedDAO.getAllClassified();
 if(list!=null){
		for(Classified cs:list){%>
		<div class=classified>
			<%User user=cs.getUser(); %>
			Category:<%=cs.getClassifiedCategory() %><br>
			Title:<%=cs.getClassifiedTitle() %><br>
			Name:<%=user.getUserName() %><br>
			Description:<%=cs.getDescription() %><br>
			Address:<%=user.getUserAddress() %><br>
			City:<%=user.getUserCity() %><br>
			Contact:<%=user.getMobile() %><br>
			
	    </div>  
	    <br>   
	<%}
}%>
 </div>

<script type="text/javascript">
function check(){
	var userName = document.getElementById("userName").value;
	var nameformat=/^[A-Za-z]+$/;
	
	if(!userName.match(nameformat)){  
	      document.getElementById("name").innerHTML = "Only characters are allowed";  
	      return false;  
	}
	
	if(userName.length<3){  
	      document.getElementById("name").innerHTML = "Enter valid name";  
	      return false;  
	}
	
	var userEmail=document.getElementById("userEmail").value;
	var mailformat =/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	
	if(!userEmail.match(mailformat)){
		document.getElementById("email").innerHTML = "Invalid Email";  
	     return false;  
	}
	
	var mobile = document.getElementById("mobile").value;
	var mobileformat=/^[0-9]+$/;
	
	if(!mobile.match(mobileformat)){  
	      document.getElementById("Mobile").innerHTML = "Only numbers allowed";  
	      return false;  
	}
	if(mobile.length!=10){  
	      document.getElementById("Mobile").innerHTML = "Enter valid Mobile Number";  
	      return false;  
	}
	
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;
	if(password!=confirmPassword){
		document.getElementById("message").innerHTML = "Password Mismatch";  
	    return false; 
	}
	else{
	    return true;
	}
}
</script>	
</body>
</html>