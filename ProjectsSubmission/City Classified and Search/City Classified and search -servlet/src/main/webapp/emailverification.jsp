<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Email Verification</title>
</head>
<style type="text/css">

body{
  background-image: url('background.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;  
  background-size: cover;
}
div{
text-align:center
}
form{
display:inline-block;
margin-left:auto;
margin-right:auto;
text-align:right;
}
h4{  
background-color:black;
opacity:0.7;
color:white;
padding:15px;
font-size:25px;	
text-align:center;
}
</style>
<body>
	<h4>OTP Verification:</h4>
	<div>
		<form action="otp_check" method="post" onsubmit="">
		OTP*:<input type="number" id="otp" name="otp" required><br><br>
		<button type="submit" value="submit" >Submit</button>
		
		</form>	
	</div>

</body>
</html>