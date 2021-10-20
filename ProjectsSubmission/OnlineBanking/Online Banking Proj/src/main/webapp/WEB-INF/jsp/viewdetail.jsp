<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.bank.beans.User"%>
<%@page import="com.bank.beans.Transaction"%>
<%@page import="java.util.*"%>
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
<title>Admin- User details</title>
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
	<%
	User user = (User) request.getAttribute("userdetail");
	List<Transaction> list = (List<Transaction>) request.getAttribute("usertransac");
	%>
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
					<li class="active"><a href="/showall">MANAGE USERS</a></li>
					<li><a href="/getallquery">MANAGE QUERY</a></li>
					<li><a href="/adminloan">VIEW LOAN APPLICATIONS</a></li>
					<li><a
						href="/adminprofile">PROFILE</a></li>
					<li><a
						onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout';}">LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<a href="/showall">Back</a>
		</div>
		<br />
		<hr />
		<br />
		<div class="row">
			<div class="col-md-4 shadow-lg">
				<h3 class="text-center">USER DETAILS</h3>
				<table class="table">
					<tbody>
						<tr>
							<td><h5>
									<b>User ID:</b>
								</h5></td>
							<td><%=user.getId()%>
						</tr>
						<tr>
							<td><h5>
									<b>Name:</b>
								</h5></td>
							<td><%=user.getName()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>Acc No.:</b>
								</h5></td>
							<td><%=user.getAccount().getAccno()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>IFSC:</b>
								</h5></td>
							<td><%=user.getAccount().getIfsc()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>BAL:</b>
								</h5></td>
							<td>Rs.<%=user.getAccount().getBal()%>/-
							</td>
						</tr>
						<tr>
							<td><h5>
									<b>Address:</b>
								</h5></td>
							<td><%=user.getAddress()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>State:</b>
								</h5></td>
							<td><%=user.getState()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>Email:</b>
								</h5></td>
							<td><%=user.getEmail()%></td>
						</tr>
						<tr>
							<td><h5>
									<b>Phone:</b>
								</h5></td>
							<td><%=user.getPhone()%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-8 shadow-lg">
				<h3 class="text-center">TRANSACTIONS</h3>
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
						<%
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss");
						for (Transaction t : list) {
						%>
						<tr>
							<td><%=t.getId()%></td>
							<td><%=t.getFrom()%></td>
							<td><%=t.getTo()%></td>
							<td><%=t.getMode()%></td>
							<td><%=t.getType()%></td>
							<td><%=t.getDate().format(formatter)%></td>
							<td><%=t.getAmount()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<footer class="container-fluid text-center">
		<a href="#myPage" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>



</body>

</html>