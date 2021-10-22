<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
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
<title>Posts</title>
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
	.mag{
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
	.uPost{
		border-radius:20px;
		padding:20px;
		float:right;
		position:relative;
		right:10px;
		top:10px;
	}
	#delete{
		position:relative;
		top:10px;
		left:700px;
	}
	
</style>
</head>
<body>
	<c:if test = "${userModel.getRoles() == 'ROLE_USER'}">
		<%@ include file="TopHeader.jsp" %>
	</c:if>
	<c:if test = "${userModel.getRoles() == 'ROLE_ADMIN'}">
		<%@ include file="AdminHeader.jsp" %>
	</c:if>
	<div class="uPost" data-toggle="modal" data-target="#myModal" data-id="${userModel.getUserId()}">
			<button class="btn-primary"><i class="material-icons" style="font-size:30px">playlist_add</i> <h5><b>Upload New Post</b></h5></button>
	</div>
	<br><br><br><br><br>
	<c:if test="${MyPosts.size()==0}">
	<center>
				<h1 style="color:white;">There are No Posts To Display</h1>
				</center>
	 </c:if> 
	<c:forEach var="st" items="${MyPosts}">
		<div class="box">
			<div class="form-group" id="delete">
				<a href="/deletePost/${st.getpId()}">
          			<span class="glyphicon glyphicon-trash"></span>
        		</a>
			</div>
			<div class="form-group name">
				<i class="material-icons" style="font-size:36px;color:grey">account_circle</i></p>
				<h4> ${st.getName()}</h4>
			</div>
			<div class="form-group">
				<h5 class="tag">${st.getTag()}</h5>
			</div>
			<div class="form-group">
				<img class="mag" src="data:;base64,${st.getImage()}"/>
			</div>
			<hr style="color:black;">
			<hr style="color:black;">
			<div class="form-group comment">
				<%-- <a class="commit" data-toggle="modal" data-target="#myModal1" data-id="${st.getpId()}" data-id2="${st.getUserId()}">Comment</a> --%>
				<a href="AllComments/${st.getpId()}">View All Comments</a>
			</div>
		</div> 
	</c:forEach>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Upload a Post</h4>
					<button type="button" class="close" style="color:black;" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form class="btm" enctype="multipart/form-data" action="/upload" method="post">
						<br>
						<label for="Upload">Upload Data : </label>
						<input class="file" type="file" name="image" accept="image/*"><br>
						<textarea class="form-control" rows="6" cols="30" name="tag" placeholder="Enter the tag Line for your Data" required></textarea>
						<p >
							<input type="text" name="userId" id="uId" value="">
						</p> <br>
						<button class="btn btn-success">Upload</button><br><br>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Comment Modal -->
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Comment To Post</h4>
					<button type="button" class="close" style="color:black;" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form action="commentServlet" method="post">
						<p>
							<input type="text" name="postId" id="pId" value="">
							<input type="text" name="userId" id="useId" value="">
						</p><br>
						<textarea class="form-control" rows="6" cols="30" name="comment" placeholder="Enter comment to post" required></textarea>
						<br>
						<button class="btn btn-primary">Comment</button><br><br>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
$(".uPost").click(function() {
	var id = $(this).data("id");
	$("#uId").val(id);
});
$(".commit").click(function() {
	var id = $(this).data("id");
	var uId=$(this).data("id2");
	$("#pId").val(id);
	$("#useId").val(uId);
});
</script>
</body>
</html>