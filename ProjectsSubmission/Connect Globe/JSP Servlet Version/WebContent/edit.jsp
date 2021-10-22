<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

<title>Insert title here</title>
<style>
	.drop{
		position:relative;
		left:350px;
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
</style>
</head>
<body>
<%@ include file="TopHeader.jsp"%>
<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit User</h4>
					<button type="button" class="close" style="color:black;" data-dismiss="modal">&times;</button>
					
				</div>
				<div class="modal-body">
				
					<form class="btm" action="Edit" method="post">
						<p style="display:none;">
							<input type="text" name="id" id="uId" value="">
						</p>
						<c:forEach var="st" items="${userDetails}">
						
						<label>Name : </label><input type="text" name="name" id="nam" value="${st.getFullname()}"><br>
						<label>Email : </label><input type="text" name="email" id="emai" value="${st.getEmail()}"><br>
						<label>Phone : </label><input type="text" name="phone" id="phon" value="${st.getMobile()}"><br>
						<label>gender : </label><input type="text" name="gend" id="gen" value="${st.getGender()}"><br>
						<button class="btn btn-success">Edit</button><br><br>
				</c:forEach>
					</form>
					
				</div>
			</div>
</body>
</html>