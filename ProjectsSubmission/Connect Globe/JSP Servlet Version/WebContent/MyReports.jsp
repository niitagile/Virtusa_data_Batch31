<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<title>My Reports</title>
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
	.image{
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
	#le{
		position:relative;
		float:left;
	}
	.m{
	padding-left:5%;
	}
	.a{
	text-align:right;
	
	 
	 padding-right:2%;
	}
	.fa {
    color: red !important;
   
}
</style>
</head>
<body>
<%@ include file="TopHeader.jsp" %>
<div class="uPost" data-toggle="modal" data-target="#myModal" data-id="${userId}">
			<button class="btn-primary"><i class="material-icons" style="font-size:30px">playlist_add</i> <h5><b>Report Your Issue</b></h5></button>
</div>

<c:forEach var="st" items="${ReportList}">
<div class="box">
		<c:if test = "${roles == 'ADMIN'}">
		<div class="a">
       <a href="deleterServlet?id=<c:out value='${st.getrId()}' />">   
       <i class="fa fa-trash fa-3x a" aria-hidden="true" align="right"></i></a> 
           
		</div></c:if>
			<div class="form-group name">
				<p><i class="material-icons" style="font-size:36px;color:grey">account_circle</i></p>
				<h4> ${name}</h4>
				<hr style="color:black;">
			</div>
			
			<div class="form-group m">
				<h4>${st.getIssue()}</h4>
			
			</div>
		
		
			<div class="form-group comment">
				<a href="AllSuggestions?id=${st.getrId()}">View All Suggestions</a>
			</div>
		</div>
</c:forEach>
<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" style="color:black;" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Report Your Issue</h4>
				</div>
				<div class="modal-body">
					<form class="btm" action="uploadReport" method="post">
						<br>
						<label for="Upload">Report : </label><br>
						<textarea class="form-control" rows="6" cols="30" name="issue" placeholder="Enter your issue.." required></textarea>
						<p style="display:none;">
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
	<script>
	$(".uPost").click(function() {
		var id = $(this).data("id");
		$("#uId").val(id);
	});
	</script>
</body>
</html>