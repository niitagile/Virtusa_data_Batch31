<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="shortcut icon" href="images/favicon.ico" >
    
<meta charset="ISO-8859-1">
<title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
</head>

  <style><%@include file="/resources/css/loginsignup.css"%></style>
<body>
<h1>${message}</h1>
<div class="container" id="container">
<div class="form-container sign-up-container">
<form action="<%= request.getContextPath() %>/SignUp" method = "post">
	<h1>Create Account</h1>
	
	<span>or use your email for registration</span>
	<input type="text" name="userName" placeholder="Name">
	<input type="email" name="email" placeholder="Email">
	<input type="password" name="password" placeholder="Password">
	<button>SignUp</button>
</form>
</div>
<div class="form-container sign-in-container">
	<form action="/check/expert/login">
		<h1>Sign In</h1>
		
	<span>or use your account</span>
	<input type="text" name="name" placeholder="Email" required minlength="6">
	<input type="password" name="password" placeholder="Password" required minlength="4">
	
	<button type="submit">Sign In</button>
	</form>
</div> 
<div class="overlay-container">
	<div class="overlay">
		<div class="overlay-panel overlay-left">
			<h1>Hello, Friend!</h1>
			<p>Enter your details and start journey with us</p>
			<button class="ghost" id="signIn">Sign In</button>
		</div>
		<div class="overlay-panel overlay-right">
			<h1>Welcome Back!</h1>
			<p>To keep connected with us please login with your personal info</p>
			
		</div>
	</div>
</div>
</div>
.
<script type="text/javascript">
	const signUpButton = document.getElementById('signUp');
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');
	signUpButton.addEventListener('click', () => {
		container.classList.add("right-panel-active");
	});
	signInButton.addEventListener('click', () => {
		container.classList.remove("right-panel-active");
	});
</script>
</body>
</html>