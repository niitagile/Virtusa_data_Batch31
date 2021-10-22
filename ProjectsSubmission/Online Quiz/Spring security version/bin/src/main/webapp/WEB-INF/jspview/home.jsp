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

  <style><%@include file="/resources/css/home.css"%></style>
  <style>
.container {
  height: 200px;
  width:auto;
  position: relative;
}
.center {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.card {
  /* Add shadows to create the "card" effect */
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width:300px;
  height:250px;
  margin-top:100px;
  margin-left:250px;
  border-radius: 8px;
  background-color:white;
}
h1{
padding-bottom:20px;
}
.body{
margin: auto;
  width: 50%;
  padding: 10px;
}
.tt{
  margin-top:100px;
font-size: 250%;
color:white;
}
</style>
  
<body>
<ul>
  <li><a class="active" href="/">Online Quiz</a></li>
  <li style="float:right"><a href="/adminlogin">Admin</a></li>
  <li style="float:right"><a href="/expertlogin">Expert</a></li>
</ul>
<div class="body">
<div class="tt">
<h1>Welcome To Quiz,</h1>
<h5>Time to test your knowledge</h5>
</div>
<div class="card">
<div class="container">
  <div class="center">
    <h1>Student ?</h1>
    <form action="/student/login"   >
        <input class="button" type="submit"  value="Register" >
        
    </form>
    <form action="/student/login"  >
        <input class="button" type="submit"  value="  Login  " >
        
    </form>
  </div>
</div>
</div>
</div>
</body>
</html>