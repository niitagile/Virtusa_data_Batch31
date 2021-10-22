<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>

 <style><%@include file="css/results.css"%></style>
<body>
	
	<div class="quize">
	<div id="showscore" class="scoreArea">
		<h3>You scored</h3>
		<div class="scoreBoard">
		<span class="scoreCount">${result}</span>
		<div class="footer-button">
		<a href="/" id="submit" class="submitBtn">
		LogOut
		</a>

    </div>
		
	</div>
	</div>
	</div>
</body>
</html>