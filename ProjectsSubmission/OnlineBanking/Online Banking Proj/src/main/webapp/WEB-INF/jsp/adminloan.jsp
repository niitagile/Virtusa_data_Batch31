<%@page import="com.bank.beans.LoanQuery"%>
<%@page import="com.bank.beans.Account"%>
<%@page import="com.bank.beans.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
if (session.getAttribute("userid") == null) {
	response.sendRedirect("/");
}
%>

<head>
<title>Admin-Show Loan Applications</title>
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
					<li ><a href="/showall">MANAGE USERS</a></li>
					<li><a href="/getallquery">MANAGE QUERY</a></li>
					<li class="active"><a href="/adminloan">VIEW LOAN APPLICATIONS</a></li>
					<li><a
						href="/adminprofile">PROFILE</a></li>
					<li><a
						onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout';}">LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>VIEW LOAN APPLICATIONS</h1>
	</div>

	<div class="container-fluid">
		<br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Loan ID</th>
					<th>User ID</th>
					<th>User Name</th>
					<th>Email ID</th>
					<th>Loan Amount</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				List<LoanQuery> loanList = (List<LoanQuery>) request.getAttribute("loanquery");
				for (LoanQuery i: loanList) {
				%>
				<tr>
					<td><%=i.getId()%></td>
					<td><%=i.getUser().getId()%></td>
					<td><%=i.getUser().getName()%></td>
					<td><%=i.getUser().getEmail()%></td>
					<td><%=i.getAmount()%></td>
					<td><a href="/viewdetail?id=<%=i.getUser().getId()%>"
						type="button" class="btn btn-info">View Details</a></td>
				</tr>
				<%
				}
				%>
			</tbody>

		</table>
	</div>

	<footer class="container-fluid text-center">
		<a href="#myPage" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>

</body>

</html>