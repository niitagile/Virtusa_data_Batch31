<%@page import="java.util.List, com.cityclassifiedandsearch.bean.CityDetails, com.cityclassifiedandsearch.bean.User, org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
    <title>City Classified And Search</title>
    <style type="text/css">
    span{color:red;}</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="/index">City Classified And Search</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/index">Classifieds</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/index2">City Details</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/user/viewupdates">News Updates</a>
            </li>
          </ul>
           <button type="button" class="btn btn-outline-light me-2" data-bs-toggle="modal" data-bs-target="#signinmodal">
          	Sign in
		  </button>
		  <button type="button" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#signupmodal">
          	Sign up
		  </button>
        </div>
      </div>
    </nav>
    
    <div class="container mt-3">
    	<% 
    		CityDetails cityDetails = (CityDetails)request.getAttribute("cityDetails");
    	    User user = (User)request.getAttribute("userDetails");
    	    String img = "";
	      	String cityImage = cityDetails.getCityimage();
	      	if(cityImage == null) {
	      		img = "/images/noimage.jpg";
	      	}
	      	else {
	      		img = "data:image/jpeg;base64," + cityImage;
	      	} 
    	%>
    	<div class="card">
		  <div class="card-body">
			<img src="<%= img %>" class="img-fluid d-block mx-auto">
			<h5 class="mt-3 card-title"> <%= cityDetails.getName() %> </h5>
			<p>
				Category: <%= cityDetails.getCategory() %> <br>
				City: <%= cityDetails.getCity() %> <br>
				Address: <%= cityDetails.getAddress() %> <br>
				<a href="<%= cityDetails.getLink() %>" target="_blank">Website</a>
			</p>
			<br>
			<h5 class="card-title"> Posted By: <%= user.getUserName() %> </h5>
			<p>
				Mobile: <%= user.getMobile() %> <br>
				Email: <%= user.getUserEmail() %>
			</p>
		  </div>	
		</div>
	</div>
	
	<!-- Sign in Modal -->
	<div class="modal fade" id="signinmodal" tabindex="-1" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Sign in</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="container">
	      	<form class="my-3" name="login" action="login" method="post" >
			  <div class="mb-3">
			    <label for="useremail" class="form-label">Email address</label>
			    <span id=mail></span>
			    <input type="email" class="form-control" id="useremail" name="username">
			  </div>
			  <div class="mb-3">
			    <label for="password" class="form-label">Password</label>
			    <input type="password" class="form-control" id="password" name="password">
			  </div>
		  	  <button type="submit" class="btn btn-outline-dark">Login</button>
		  	</form>
		 </div>
	    </div>
	  </div>
	</div>
	
	<!-- Sign up Modal -->
	<div class="modal fade" id="signupmodal" tabindex="-1" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Sign up</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="container">
	      		<form class="my-3" name="register" action="register" method="post" onsubmit="return signupmodalcheck()">
			  <div class="mb-3">
			    <label for="userName" class="form-label">User Name</label>
			    <input type="text" class="form-control" id="userName" name="userName" required>
			    <span id="user"></span>
			  </div>
			  <div class="mb-3">
			    <label for="userEmail" class="form-label">Email Address</label>
			    <input type="email" class="form-control" id="userEmail" name="userEmail" required>
			       <span id="mail"></span>
			  </div>
			  <div class="mb-3">
			    <label for="mobile" class="form-label">Mobile</label>
			    <input type="tel" class="form-control" id="mobile" name="mobile" required>
			       <span id="Mobile"></span>
			  </div>
			  <div class="mb-3">
			    <label for="userAddress" class="form-label">Address</label>
			    <input type="text" class="form-control" id="userAddress" name="userAddress" required>
			  </div>
			  <div class="mb-3">
			    <label for="userCity" class="form-label">City</label>
			    <input type="text" class="form-control" id="userCity" name="userCity" required>
			  </div>
			  <div class="mb-3">
			    <label for="password" class="form-label">Password</label>
			    <input type="password" class="form-control" id="password1" name="password" required>
			  </div>
			  <div class="mb-3">
			    <label for="confirmPassword" class="form-label">Confirm Password</label>
			    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
			       <span id="message"></span>
			  </div>
			  <p>
			  	Already have an account? <a href="login">Log In</a>
			  </p>
		  	  <button type="submit" class="btn btn-outline-dark">Submit</button>
		  	</form>
		 </div>
	    </div>
	  </div>
	</div>
	
	<script>
		
		function signupmodalcheck(){
			var userName = document.getElementById("userName").value;
			var nameformat=/^[A-Za-z]+$/;
			
			if(!userName.match(nameformat)){  
			      document.getElementById("user").innerHTML = "Only characters are allowed";  
			      return false;  
			}
			
			if(userName.length<3){  
			      document.getElementById("user").innerHTML = "Enter valid name";  
			      return false;  
			}
			
			var userEmail=document.getElementById("userEmail").value;
			var mailformat =/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			
			if(!userEmail.match(mailformat)){
				document.getElementById("mail").innerHTML = "Invalid Email";  
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
			
			var password = document.getElementById("password1").value;
			var confirmPassword = document.getElementById("confirmPassword").value;
			if(password!=confirmPassword){
				document.getElementById("message").innerHTML = "Password Mismatch";  
			    return false; 
			}
			
			    return true;
			}
	</script>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</body>
</html>