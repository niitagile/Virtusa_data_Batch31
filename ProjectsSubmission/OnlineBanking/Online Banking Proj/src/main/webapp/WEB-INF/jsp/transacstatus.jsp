<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>

<%
if (session.getAttribute("userid") == null) {
	response.sendRedirect("/");
}
%>

<head>
<title>Transfer Funds Status</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {
	font: 400 15px Lato, sans-serif;
	line-height: 1.8;
	color: #818181;
}

.jumbotron {
	background-color: #f4511e;
	color: #fff;
	padding: 100px 25px;
	font-family: Montserrat, sans-serif;
}

.container-fluid {
	padding: 60px 50px;
}

.bg-grey {
	background-color: #f6f6f6;
}
</style>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Online Banking</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">HOME</a></li>
					<%
					if (session.getAttribute("userid") != null) {
					%>
					<li><a href="/dashboard">CHECK BALANCE</a></li>
					<li class="active"><a href="/fundtransfer">FUND TRANSFER</a></li>
					<li><a
						href="/upitransfer">UPI</a></li>
					<li ><a href="/loan">LOAN</a></li>
					
					<li><a href="/passbook">PASSBOOK</a></li>
					<li><a href="/profile">PROFILE</a></li>
					<li><a
						onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout'}">LOGOUT</a></li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1><%=request.getAttribute("msg")%></h1>
		<br /> <a href="/fundtransfer">Go Back</a>
	</div>



	<footer class="container-fluid text-center">
		<a href="/" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>



</body>


</html>