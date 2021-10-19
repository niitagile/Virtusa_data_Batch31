<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dashboard.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</head>
<body>
	<%
		String result = (String) session.getAttribute("message");
	session.removeAttribute("message");
	if(result.equalsIgnoreCase("SUCCESS")){
	%>
	<div
		class="alert alert-success text-center position-relative top-50 start-50 translate-middle"
		style="width: 30%;" role="alert">Success!</div>
	<%}else if(result.equalsIgnoreCase("FAIL")){ %>
	<div
		class="alert alert-danger text-center"
		style="width: 30%;top:20%;left:35%" role="alert">Something went wrong!</div>
	<%} %>
	<!-- %@include file="items.jsp"%-->
</body>
</html>