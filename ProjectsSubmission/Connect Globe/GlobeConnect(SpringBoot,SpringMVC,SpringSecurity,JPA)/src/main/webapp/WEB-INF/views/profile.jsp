<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	try {
		String name = "";
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		name=(String)session.getAttribute("userId");
		if (name == "") {
			response.sendRedirect("/");
		} else {
		}
	} catch (Exception ex) {
		out.println(ex);
	}
%>
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

<title>Profile</title>
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
	<c:if test = "${Roles.getRoles() == 'ROLE_USER'}">
		<%@ include file="TopHeader.jsp" %>
	</c:if>
	<c:if test = "${Roles.getRoles() == 'ROLE_ADMIN'}">
		<%@ include file="AdminHeader.jsp" %>
	</c:if>
	<c:forEach var="st" items="${userDetails}">
		<div class="container drop">
			<div class="form-group">
				<i class="material-icons" style="font-size: 150px; color: grey">account_circle</i>
				</p>
			</div>
			<div class="form-group">
				<label>Name : </label>${st.getFullname()}
			</div>
			<div class="form-group">
				<label>Email : </label>${st.getEmail()}
			</div>
			<div class="form-group">
				<label>Mobile Number : </label>${st.getMobileNumber()}
			</div>
			<div class="form-group">
				<label>Gender : </label>${st.getGender()}
			</div>
			<div class="form-group">
				<button class="btn btn-primary commit" data-toggle="modal"
					data-target="#myModal1" data-id="${st.getFullname()}"
					 data-id3="${st.getMobileNumber()}"
					 data-id5="${st.getUserId()}">Edit Profile</button>
			</div>
		</div>
	</c:forEach>
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit User</h4>
					<button type="button" class="close" style="color:black;" data-dismiss="modal">&times;</button>
					
				</div>
				<div class="modal-body">
					<form class="btm" action="/Edit" method="post">
						<p style="display:none;">
							<input type="text" name="id" id="uId" value="">
						</p>
						<label>Name : </label><input type="text" name="name" id="nam" value=""><br>
						<label>Phone : </label><input type="text" name="phone" id="phon" value=""><br>
						<label>New Password : </label><input type="password" name="pass" id="gen" value=""><br>
						<button class="btn btn-success">Edit</button><br><br>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(".commit").click(function() {
		var nam = $(this).data("id");
		var mobil=$(this).data("id3");
		var id=$(this).data("id5");
		$("#nam").val(nam);
		$("#phon").val(mobil);
		$("#uId").val(id);
	});
	</script>
</body>
</html>