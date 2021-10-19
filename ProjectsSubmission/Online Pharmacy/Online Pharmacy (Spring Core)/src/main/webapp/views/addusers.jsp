<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/items.component.css">
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
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>

</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role = (String) session.getAttribute("role");

	if (session.getAttribute("username") == null
			|| session.getAttribute("role") == null || !role.equals("ADMIN")) {
		response.sendRedirect("login.jsp");
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
					<form action="<%=request.getContextPath()%>/AddUsers"
						method="POST">
						<div class="form-floating">
						<input type="text" class="form-control" name="username"
							placeholder="Username" required>
						<label for="username">Username</label>
					</div>
					<div class="form-floating">
						<input type="text" class="form-control" name="name"
							placeholder="Name" required>
						<label for="name">Name</label>
					</div>
					<div class="form-floating">
						<input type="text" class="form-control" name="email"
							placeholder="Email" minlength=6 maxlength=20  required>
						<label for="email">Email</label>
					</div>
					<div class="form-floating">
						<input type="text" class="form-control" name="phoneNumber"
							placeholder="Phonenumber" minlenth="10" maxlength="10" required>
						<label for="PhoneNumber">PhoneNumber</label>
					</div>
					<div class="form-floating">
						<input type="password" class="form-control" name="password"
							placeholder="Password" minlength="8" required>
						<label for="password">Password</label>
					</div>
					<div class="form-floating">
						<input type="text" class="form-control" name="role"
							placeholder="Role" required>
						<label for="role">Role</label>
					</div>
					<button type="submit" class="btn btn-primary col-11 ms-3">Register</button>
					</form>
				</div>
			</div>
			</div>
			</div>
			
	<%
		}
	%>
</body>
</html>