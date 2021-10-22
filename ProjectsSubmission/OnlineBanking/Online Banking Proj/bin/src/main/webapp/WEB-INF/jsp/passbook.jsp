<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.beans.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<%if(session.getAttribute("userid") == null){
	response.sendRedirect("/");
}
%>

<head>
<title>Passbook</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
					<%if(session.getAttribute("userid")!=null){ %>
					<li ><a href="/dashboard">CHECK BALANCE</a></li>
					<li><a href="/fundtransfer">FUND TRANSFER</a></li>
					<li class="active"><a href="/passbook?id=<%=session.getAttribute("userid")%>">PASSBOOK</a></li>
					<li><a href="/profile?id=<%=session.getAttribute("userid")%>" >PROFILE</a></li>
					<li><a onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout'}">LOGOUT</a></li>
					<%} %>

				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>PASSBOOK</h1>
		<p>Get all your transactions listed here</p>
	</div>

	<div id="about" class="container-fluid">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">FROM</th>
					<th scope="col">TO</th>
					<th scope="col">MODE</th>
					<th scope="col">TYPE</th>
					<th scope="col">DATE</th>
					<th scope="col">AMOUNT</th>
				</tr>
			</thead>
			<tbody>
			<% List<Transaction> list= (List<Transaction>) request.getAttribute("list");
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss");
			int i=0;
			for(Transaction t: list) {%>
				<tr>
					<td><%=t.getId() %></td>
					<td><%=t.getFrom() %></td>
					<td><%=t.getTo() %></td>
					<td><%=t.getMode() %></td>
					<td><%=t.getType() %></td>
					<td><%=t.getDate().format(formatter) %></td>
					<td><%=t.getAmount() %></td>
					
				</tr>
				<%} %>
				
			</tbody>
		</table>
	</div>

	<footer class="container-fluid text-center">
		<a href="/" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>


</body>


</html>