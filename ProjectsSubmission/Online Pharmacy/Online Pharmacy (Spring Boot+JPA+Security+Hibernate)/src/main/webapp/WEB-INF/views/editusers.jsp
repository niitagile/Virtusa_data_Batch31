<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
	String role = (String) session.getAttribute("role");
	String token=(String) session.getAttribute("token");
	if (session.getAttribute("username") == null || token==null) {
		response.sendRedirect("login.jsp");
	} else {
	%>
	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">
		<div class="card bg-light border-primary shadow rounded" id="itemForm"
			style="width: 25rem;">
			<h3 class="card-header text-primary"
				style="text-align: center; font-weight: bold;">Edit Users</h3>
			<div class="card-body">
				<form:form
					action="${pageContext.request.contextPath}/updateprofile"
					method="PATCH" modelAttribute="user">
					<div class="form-floating">
						<form:input type="int" class="form-control" name="id"
							placeholder="Id" path="id" />
						<label for="id">Id</label>
					</div>
					<div class="form-floating">
						<form:input type="text" class="form-control" name="name"
							placeholder="Name" path="name" required="required"/>
						<label for="name">Name</label>
					</div>
					<div class="form-floating">
						<form:input type="text" class="form-control" name="username"
							placeholder="Username" path="username" required="required"/>
						<label for="username">Username</label>
					</div>
					<div class="form-floating">
						<form:input type="email" path="email" class="form-control" name="email"
							placeholder="Email" required="required"/>
						<label for="email">Email</label>
					</div>
					<div class="form-floating">
						<form:input type="text" path="phoneNumber" class="form-control" name="phoneNumber"
							placeholder="Phonenumber" required="required"/>
						<label for="PhoneNumber">PhoneNumber</label>
					</div>
					<div class="form-floating">
						<form:input type="password" path="password" class="form-control" name="password"
							placeholder="Password" required="required"/>
						<label for="password">Password</label>
					</div>
					<%if(!token.contains("USER") && !token.contains("DISTRIBUTOR")){ %>
					<div class="form-floating">
						<form:input type="text" path="role" class="form-control" name="role"
							placeholder="Role" required="required"/>
						<label for="role">Role</label>
					</div>
					<%} %>
					<div class="m-3 text-center">
						<button type="submit" style="width: 10rem;"
							class="btn btn-outline-success">Update User</button>
					</div>

				</form:form>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>