<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
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