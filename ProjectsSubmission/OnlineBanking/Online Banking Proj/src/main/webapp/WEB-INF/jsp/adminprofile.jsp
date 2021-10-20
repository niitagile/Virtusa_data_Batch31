<%@page import="com.bank.beans.User"%>
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
<title>Profile</title>
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
					<li><a href="/showall">MANAGE USERS</a></li>
					<li><a href="/getallquery">MANAGE QUERY</a></li>
					<li><a href="/adminloan">VIEW LOAN APPLICATIONS</a></li>
					<li class="active"><a href="/adminprofile">PROFILE</a></li>
					<li><a
						onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout';}">LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>DASHBOARD</h1>
		<p>Check your Account balance instantly</p>
	</div>
	<div class="text-center">
		<button class="btn btn-primary" style="width: 30%;"
			data-toggle="modal" data-target="#edit-profile-modal"
			data-ticket-type="premium-access">EDIT PROFILE</button>
	</div>

	<%
	User u = (User) (request.getAttribute("adminmodel"));
	%>

	<div class="container-fluid">
		<div class="row gutters-sm  text-center">
			<div class="card mb-3">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>Name</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5>
								<%=u.getName()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>Email</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5><%=u.getEmail()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>State</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5><%=u.getState()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>Address</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5><%=u.getAddress()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>Mobile</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5><%=u.getPhone()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>ID PROOF</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5>
								<%=u.getAadharproof()%></h5>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h5 class="mb-0">
								<b>Designation</b>
							</h5>
						</div>
						<div class="col-sm-9">
							<h5>
								<%=u.getRole()%>
							</h5>
						</div>
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div>

	<div id="edit-profile-modal" class="modal fade">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title ">Edit Profile</h4>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>


				<div class="modal-body ">
					<form method="POST" action="/editprofile"
						onsubmit="return validate()">
						<input type="hidden" name="id" value="<%=u.getId()%>" />
						<div class="form-group">
							Name : <input class="form-control" type="text" name="name"
								id="name" value="<%=u.getName()%>" required />
						</div>
						<div class="form-group">
							Phone : <input class="form-control" type="tel" name="phone"
								id="phone" value="<%=u.getPhone()%>" required />
						</div>
						<div class="form-group">
							Address : <input class="form-control" type="text" name="address"
								id="address" value="<%=u.getAddress()%>" required />
						</div>
						<div class="form-group">
							Pin (4 digit pin): <input class="form-control" type="number"
								name="pin" id="pin" value="<%=u.getPin()%>" required />
						</div>

						<div class="form-group">
							Password (Min 8 characters, at least 1 lowercase and uppercase
							letter and one digit): <input class="form-control"
								type="password" name="password" id="password"
								 placeholder="Enter the pasword" required
								/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="submit" name="submit"
							value="Update" />
					</form>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		function validate() {
			if (document.getElementById("phone").value.length == 10
					&& document.getElementById("address").value.length >= 5
					&& (document.getElementById("password").value.length >= 8)
					&& document.getElementById("pin").value.length == 4
					&& document.getElementById("name").value.length >= 3) {
				return true;
			} else {
				alert("Invalid Form Data");
				return false;
			}

		}
	</script>

	<footer class="container-fluid text-center">
		<a href="/" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>



</body>



</html>