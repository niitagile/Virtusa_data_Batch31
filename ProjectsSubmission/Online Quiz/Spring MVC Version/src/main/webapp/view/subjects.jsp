<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Welcome Student</title>
</head>
  <style><%@include file="/view/css/subjects.css"%></style>
<body>
<ul>
  <li><a class="active" href="#home">Online Quiz</a></li>
  <li style="float:right"><a href="/">Logout</a></li>
  <li style="float:right"><a href="/student/profile/${std_id}">View Profile</a></li>
</ul>
	<h3 style="text-align:center;margin-top:10px">These are the list of categories available with us. Choose the topics as per your liking and click on the start quiz button to take the quiz.</h3>
	<c:forEach var="subject" items="${subs}">
	
		<form action="/getQuestions/${subject.subId}">
		<div class="card">
  			<div class="container">
    			<h4><strong>${subject.subName}</strong></h4>    				 
			
   				 <button type="submit" class="button button">Start Quiz</button>
  			</div>
		</div>
			<input type="hidden" name="studentId" value="${std_id}"/>		</form>
	</c:forEach>
</body>
</html>