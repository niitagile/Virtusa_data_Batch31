<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
  <style >
  </style>
</head>
<body>
	<%@ include file="heading.jsp" %>
	<div class = "container">
		<div class = "row">
			<div class = "col-md-6 col-md-offset-3">
				
				<h1> User Login Page </h1>
				
					<c:if test="${not empty error}">
			<p style="color:red;">${error}</p>
		</c:if>
					
				
					<form action="login" method="post">
					
					<div class = "form-group">
						<label for ="username"> Enter Email Id :</label>
						<input type="email" class = "form-control" id ="email" name = "email"
						placeholder="Enter Email ID" autofocus="autofocus">
					</div>
					
					<div class="form-group">
						<label for="password">Password</label>: <input type="password"
							id="psw" name="psw" class="form-control"
							placeholder="Enter Password"  required/>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="submit" name="login-submit" id="login-submit"
									class="form-control btn btn-primary" value="Log In" />
							</div>
						</div>
					</div>
				</form>
				<div class="form-group">
						<span>New user? <a href="/register" >Register
								here</a></span>
				</div>
			</div>
		</div>
	</div>
<!-- 

    

<div class="bod">
<div class="container my-5 con">
<h1>Login</h1>
<form action="login" method="post">
<table class="center">
<tr><td>User Name :</td><td><input type="text" name="email" /></td></tr>
<tr><td>password :</td><td><input type="password" name="psw" required/></td></tr>
<tr><td colspan="2"><input type="submit" value="login"/></td></tr>
<tr><td colspan="2">If you are new User?<a href="register">Register</a></td></tr>
</table>
</form>
</div>
</div>
-->
</body>
</html>