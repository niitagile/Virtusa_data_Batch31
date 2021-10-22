

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8" />
	<title>Result List</title>
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
</head>

  <style><%@include file="/view/css/studentlist.css"%></style>

<body>

<ul>
  <li><a class="active" href="#home">Online Quiz</a></li>
  <li style="float:right"><a href="http://localhost:8092/">Logout</a></li>
</ul>

<div class="table-title">
<h3>Result List</h3>
</div>
<table class="table-fill">
<caption>ScoreCard</caption>
<thead>
<tr>
<th scope="col" class="text-left">Quiz Id</th>
<th scope="col" class="text-left">Student Id</th>
<th scope="col" class="text-left">Subject Id</th>
<th scope="col" class="text-left">Score</th>
</tr>
</thead>
<tbody class="table-hover">
	
<c:forEach var="result" items="${results}">
<tr>
<td class="text-left">${result.quizId}</td>
<td class="text-left">${result.stdId}</td>
<td class="text-left">${result.subId}</td>
<td class="text-left">${result.score}</td>
</tr>
</c:forEach>
</tbody>
</table>
  

  </body>
</html>