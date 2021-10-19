<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/itemstable.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String token = (String) session.getAttribute("token");
	String username = (String) session.getAttribute("username");
	if (username == null && (token.contains("DISTRIBUTOR") || token.contains("ADMIN"))) {
		response.sendRedirect("login");
	} else {
	%>
	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">
		<div class="card my-5 shadow bg-white rounded donor">
			<h3 class="card-header text-primary fw-bold">Your Items:</h3>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead class="table-primary">
							<tr>
								<th scope="col">Item Name</th>
								<th scope="col">Description</th>
								<th scope="col">Price</th>
								<th scope="col">Quantity</th>
								<th scope="col">Operation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items}" var="item">
								<tr>
									<td>${item.itemName}</td>
									<td>${item.description}</td>
									<td>${item.price}</td>
									<td>${item.quantity}</td>
									<td><a role="button"
										href="${pageContext.request.contextPath}/edititem?id=
							${item.id}"
										class="btn btn-outline-primary btn-sm ms-3">Edit</a> <a
										role="button" href="${pageContext.request.contextPath}/deleteitem/${item.id}"
										class="btn btn-outline-danger btn-sm ms-2">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>