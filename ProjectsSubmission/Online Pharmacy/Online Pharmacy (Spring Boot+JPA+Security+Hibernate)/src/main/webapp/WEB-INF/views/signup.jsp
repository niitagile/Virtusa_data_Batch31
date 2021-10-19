<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/signup.component.css">
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

<script>
	function validatePassword(){
		event.preventDefault();
		var pass1=document.register.password.value
		var pass2=document.register.password2.value
		if(!(pass1===pass2)){
			document.getElementById("error").innerHTML="Both passwords must match!"
		}
		else{
			document.register.submit();
		}
	}
</script>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<%if(request.getAttribute("msg")!=null){ %>
		<div
			class="alert alert-success text-center position-relative top-50 start-50 translate-middle mt-5"
			style="width: 32%;" role="alert">Registered Successfully!</div>
		<%} %>
		<div class="d-flex justify-content-center">

			<div class="card bg-light border-primary shadow rounded"
				style="width: 25rem;">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">SignUp Page</h3>
				<div class="card-body">

						 <form:form action="register" method="POST" name ="register" modelAttribute="user" onsubmit="validatePassword()">  
						<div class="form-floating">
							 <form:input type="text" path="name" class="form-control" placeholder="Name" required="required"/> 
							 <label for="name">Name</label>
						</div>
						<div class="form-floating">
							<form:input type="email" path="email" class="form-control" placeholder="Email" required="required"/> 
							 <label for="email">Email
								address</label>
								<form:errors path="email" cssClass="error" />
						</div>
						<div class="form-floating">
							<form:input type="text" class="form-control" path="phoneNumber" name="phoneNumber"
								placeholder="Mobile number" minlength="10" maxlength="10" required="required"/>
							<label for="phoneNumber">Mobile Number</label>
							<form:errors path="phoneNumber" cssClass="error" />
						</div>
						<div class="form-floating">
							<form:input type="text" class="form-control" name="username" path="username"
								placeholder="Username" minlength="6" maxlength="20" required="required"/>
							<label for="username">User-name (minimum 6 characters)</label>
							<form:errors path="username" cssClass="error" />
						</div>
						<p class="text-danger ms-3" id="error"></p>
						<div class="form-floating">
							<form:input type="password" class="form-control" path="password" name="password"
								placeholder="Password" required="required" minlength="8"/> <label
								for="password">Password (minimum 8 characters)</label>
								<form:errors path="password" cssClass="error" />
						</div>
						<div class="form-floating">
							<input type="password" class="form-control" name="password2"
								placeholder="Confirm Password" required> <label
								for="password2">Confirm Password</label>
						</div>
						<p class="fw-bold ms-3" style="color: #6174f0;">Account Type:</p>
						<div class="form-check form-check-inline ms-3">
							<form:radiobutton class="form-check-input" name="usertype"
								id="user" path="role" value="USER" checked="checked"/> <label
								class="form-check-label" for="user"> User </label>
						</div>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" name="usertype"
								id="distributor" path="role" value="DISTRIBUTOR"/><label
								class="form-check-label" for="distributor"> Distributor
							</label>
						</div>
						<br>
						<button type="submit" class="btn btn-primary col-11 ms-3">Register</button>

					</form:form>
					<br>
					<p class="text-center">
						Already have an account? &nbsp;<a href="login">Click here!</a>
					</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>