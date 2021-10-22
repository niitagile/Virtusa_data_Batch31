<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">

				<p style="color:green;">${success}</p>
				<p style="color:red;">${exits}</p>
				<h1>Registration</h1>
<form action="save" method="post" modelAtrribute="register">

					<div class="form-group">
						<label class="control-label" for="fullname"> First Name </label>
						<input id="fullname" class="form-control" name="fullname" type="text" 
						pattern="[A-Za-z]{5,10}[0-9]{0,2}" title="Name Should be Minimum 5 leters and Maximum 10 Letters"
						placeholder="Enter Your Full Name"	required autofocus="autofocus" />
					</div>

					<div class="form-group">
						<label class="control-label" for="email"> Email </label> <input
							type="email" id="email" class="form-control" name="email"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" 
							title="Valid Format : abc@gmail.com" required
							  placeholder="Enter Your Email Id" autofocus="autofocus" />
					</div>

					<div class="form-group">
						<label class="control-label" for="password"> Password </label> <input
							id="password" class="form-control" type="password"
							 name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
		      title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" 
		      placeholder="Enter the Password"
		       required autofocus="autofocus" />
					</div>
					
					<div class="form-group">
						<label class="control-label" for="email"> Mobile Number </label> <input
							type="tel"  id="mobileNumber" class="form-control" name="mobileNumber" placeholder="9876-453-210" pattern="[789][0-9]{9}"  
							  title="Must contain valid 10 digit Indian phone number Format" required  name="mobileNumber"
							autofocus="autofocus" />
					</div>
					
					
					
					<div class="form-group">
						<label class="control-label" for="gender"> Gender </label> <input
							type="radio"  id="gender" name="gender" 
							 value="male" /> Male
				<input
							type="radio"  id="gender" name="gender" 
							 value="female"/> Female
					</div>
					<input type="hidden" name="role" value="user"></td></tr>
					
					<div class="form-group">
						<input type="submit" class="btn btn-success" value="Register">Register</button>
						<span>Already registered? <a href="/">Login
								here</a></span>
					</div>
					
				
				</form>
					</div>
					</div>
					</div>
<!--  
<table class="center">

<tr><td>Full Name :</td><td><input type="text" name="fullname" /></td></tr>
<tr><td>Email :</td><td><input type="text" name="email" /></td></tr>
<tr><td>Password :</td><td><input type="text" name="password" /></td></tr>
<tr><td>Mobile :</td><td><input type="text" name="mobileNumber" /></td></tr>
<tr><td>Gender :</td><td><input type="radio" name="gender" value="male"> Male</td></tr>
<tr><td></td><td><input type="radio" name="gender" value="female"> Female</td></tr>
<tr><td></td><td><input type="radio" name="gender" value="other"> Other.</td></tr>
<tr><td></td><td><input type="hidden" name="role" value="user"></td></tr>
<tr><td colspan="2"><input type="submit" value="Register"/></td></tr>
</table>
</form></div></div>
-->
</body>
</html>