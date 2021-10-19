<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/login.component.css">
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
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<%if(request.getSession().getAttribute("status")!=null){ %>
		<div class="alert alert-danger text-center position-relative top-50 start-50 translate-middle" style="width: 32%;"
			role="alert">Invalid Credentials!</div>
		<%} %>
		<div class="d-flex justify-content-center">

			<div class="card bg-light border-primary mb-3 shadow rounded"
				style="width: 25rem;">
				<img
					src="https://blog.ipleaders.in/wp-content/uploads/2020/09/Online-pharmacy-1200x600.jpg"
					style="height: 12rem" class="card-img-top banner" alt="pharmacy">
				<div class="card-body">

					<form:form action="authenticate"
						method="POST" modelAttribute="user">
						<div class="form-floating">
							<form:input type="text" class="form-control" name="username" path="username"
								placeholder="username" minlength="6" maxlength="20" required="required"/>
							<label for="username">Username</label>
						</div>
						<div class="form-floating">
							<form:input type="password" class="form-control" path="password" name="password"
								placeholder="Password" minlength="8" required="required"/> <label
								for="password">Password</label>
						</div>
						<button class="btn btn-primary col-11 ms-3" type="submit">Submit</button>
					</form:form>
					<br>
					<div class="text-center">
						<p>
							New user? &nbsp;<a href="register">Sign up!</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>