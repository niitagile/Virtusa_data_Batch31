<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  	    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/items.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>

</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String token = (String) session.getAttribute("token");

	if (session.getAttribute("username") == null
			|| !token.contains("ADMIN")) {
		response.sendRedirect("login");
	} else {
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="d-flex justify-content-center">
			<div class="card bg-light border-primary shadow rounded"
				id="itemForm" style="width: 25rem">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Add Users</h3>
				<div class="card-body">
					<form:form action="${pageContext.request.contextPath}/register" method="POST" name ="register" modelAttribute="user">
						<div class="form-floating">
							 <form:input type="text" path="name" class="form-control" placeholder="Name" required="required"/> 
							 <label for="name">Name</label>
						</div>
						<div class="form-floating">
							<form:input type="email" path="email" class="form-control" placeholder="Email" required="required"/> 
							 <label for="email">Email
								address</label>
						</div>
						<div class="form-floating">
							<form:input type="text" class="form-control" path="phoneNumber" name="phoneNumber"
								placeholder="Mobile number" minlength="10" maxlength="10" required="required"/>
							<label for="phoneNumber">Mobile Number</label>
						</div>
						<div class="form-floating">
							<form:input type="text" class="form-control" name="username" path="username"
								placeholder="Username" minlength="6" maxlength="20" required="required"/>
							<label for="username">Username</label>
						</div>
						<p class="text-danger ms-3" id="error"></p>
						<div class="form-floating">
							<form:input type="password" class="form-control" path="password" name="password"
								placeholder="Password" required="required" minlength="8"/> <label
								for="password">Password</label>
						</div>
					<div class="form-floating">
						<form:input type="text" class="form-control" name="role" path="role"
							placeholder="Role"/>
						<label for="role">Role</label>
					</div>
					<button type="submit" class="btn btn-primary col-11 ms-3">Register</button>
					</form:form>
				</div>
			</div>
			</div>
			</div>
			
	<%
		}
	%>
</body>
</html>