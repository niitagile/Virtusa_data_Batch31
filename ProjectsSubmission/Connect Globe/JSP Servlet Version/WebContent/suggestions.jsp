<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.jsp.beans.*"%>
<%@ page import="com.jsp.controller.*"%>
<%@ page import="com.jsp.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous">
	
</script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Suggestions</title>
<style>
	body{
		background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgHLPl07I1rQ2sMiWOrvsiUHtu5DMh8jcKvA&usqp=CAU");
	}
	
	.cont{
		display:flex;
		flex-direction:row;
		position:relative;
		float:right;
		right:30px;
		text-align:center;
		width: 200px;
		height:auto;
		background-color:white;
		border:1px solid black;
		border-radius:10px;
		
	}
	.modal-body{
		background-color:orange;
		position:relative;
		text-align:center;
	}
	.file{
		position:relative;
		left:200px;
	}
	.modal-tilte{
		color:green;
	}
	.modal-header{
		background-color:lightgreen;
		color:black;
	}
	.box{
		width:800px;
		height:auto;
		background-color:white;
		border:1px solid black;
		border-radius:20px;
		margin: 0 auto;
		margin-top:40px;
		display:flex;
		flex-direction:column;
		justify-content:space-evenly;
	}
	.box:hover{
    	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	}
	img{
		width:650px;
		height:350px;
		position:relative;
		left:70px;
		top:10px;
		
	}
	.tag{
		text-align:center;
	}
	.comment{
		position:relative;
		top:-10px;
		display:flex;
		flex-direction:row;
		justify-content:space-around;
	}
	.name{
		position:relative;
		left:50px;
		top:10px;
		display:flex;
		flex-direction:row;
	}
	.comments{
		width:50%;
		height:auto;
		border-radius:10px;
		background-color:lightgrey;
		float:left;
		position:relative;
		left:20px;
		margin-bottom:20px;
		padding-left:20px;
		color:white;
	}
	.comments:hover{
    	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	}
	.atag {
		color:white;
	}
	.atag:hover{
		color:white;
	}
</style>
</head>
<body>
<div class="container" style="color:white; cursor:pointer;">
	<a href="AllReports" class="atag"><span class="glyphicon" style="color:white;">&#xe091;</span> Back</a>
</div>
<div class="box">
	<div class="form-group">
		<h5 class="tag">${issue}</h5>
	</div>
	<hr style="color:black;">
	<label for="comment">Suggestions </label>
	<c:forEach var="it" items="${suggestionList}">
		<div class="comments">
			<h4 style="color:red;">${it.getName()}</h4>
			<p style="color:green;">${it.getSuggestion()}</p>
		</div>
	</c:forEach>
</div>
</body>
</html>