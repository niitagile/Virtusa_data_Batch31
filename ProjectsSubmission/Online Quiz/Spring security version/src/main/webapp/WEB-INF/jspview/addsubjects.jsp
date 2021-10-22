<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Subject</title>
</head>

  <style><%@include file="/resources/css/addsubject.css"%></style>
<body>
    <div style="text-align:center;color:white">
      Add Subject<br/>
      List of subjects available:
    </div>
    
<c:forEach var="subject" items="${subcs}">
<div class="menu-container">
	<nav class="menu">
		<a href="#" class="menu-item">
				<span class="menu-item-title">${subject.subName}</span>
		</a>
	</nav>
</div>
</c:forEach>
<div class="center">
<h1>Add New Subject</h1>
  <form action="/addsubject">
    <div class="inputbox">
      <input type="text" required="required" name="subjectName">
       <span>Subject Name</span>
	</div>
    <div class="inputbox" style="background-color:blue">
      <input type="submit" value="submit">
    </div>
  </form>
</div>
</body>
</html>