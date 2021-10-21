<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Expert</title>
</head>

  <style><%@include file="/resources/css/addexpert.css"%></style>
<body>
<div class="center">
  <h1>Add Expert</h1>
  <form action="/add/expert/" method="post">
    <div class="inputbox">
      <input type="text" required="required" name="name">
      <span>User Name</span>
    </div>
     <div class="inputbox">
      <input type="email" required="required" name="email">
      <span>Email</span>
    </div>
    <div class="inputbox">
      <input type="text" required="required" name="password">
      <span>Password</span>
    </div>
    <div class="inputbox">
      <input type="text" required="required">
      <span>Confirm Password</span>
    </div>
    <div class="inputbox" style="background-color:blue">
      <input type="submit" value="submit">
    </div>
  </form>
</div>
	
</body>
</html>