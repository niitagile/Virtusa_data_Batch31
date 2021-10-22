<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8" />
	<title>Expert List</title>
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
</head>

  <style><%@include file="/resources/css/studentlist.css"%></style>
<body>
<ul>
  <li><a class="active" href="#home">Online Quiz</a></li>
  <li style="float:right"><a href="http://localhost:8092/">Logout</a></li>
</ul>
<div class="table-title">
<h3>Expert List</h3>
</div>
<table class="table-fill">
<caption>List of Experts</caption>
<thead>
<tr>
<th scope="col" class="text-left">ID</th>
<th scope="col" class="text-left">Name</th>
<th scope="col" class="text-left">Delete</th>
</tr>
</thead>
<tbody class="table-hover">
	
<c:forEach var="expert" items="${experts}">
<tr>
<td class="text-left">${expert.expertId}</td>
<td class="text-left">${expert.expertName}</td>
<td class="text-left">
				<form action="/delete/expert">
  				<input type="hidden" name="id" value="${expert.expertId}"/>
  				<input type="submit" value="Delete"/>				 
  				</form>		
 </td>
</tr>
</c:forEach>
</tbody>
</table>
  
  </body>
</html>