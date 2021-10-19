<%@page import="com.pharmacy.model.DistributorItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
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
<style>
body {
	background-color: #e8eaf6 !important;
}

.card {
	margin-top: 10%;
}

@media screen and (max-width:670px) {
	.card {
		margin-top: 20%;
	}
}
</style>
</head>

<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String token = (String) session.getAttribute("token");
	if (session.getAttribute("username") == null || token == null) {
		response.sendRedirect("login");
	} else {
		float total = 0.0f;
		List<DistributorItemBean> items = (ArrayList<DistributorItemBean>) session.getAttribute("cartList");
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="d-flex justify-content-center">
			<div class="card shadow rounded w-75" id="cart-card">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Confirm Your
					Order</h3>
				<div class="card-body">
					<table class="table table-striped">
						<thead class="table-light text-primary">
							<tr>
								<th scope="col">Item id</th>
								<th scope="col">Item Name</th>
								<th scope="col">Quantity</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (items != null && !items.isEmpty()) {
								for (DistributorItemBean item : items) {
									total += item.getPrice();
							%>

							<tr>
								<th scope="row"><%=item.getId()%></th>
								<td><%=item.getItemName()%></td>
								<td><%=item.getQuantity()%></td>
								<td><%=item.getPrice()%></td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
					<form action="${pageContext.request.contextPath}/orderplaced" method="POST">
						<ul class="list-group float-end">
							<li class="list-group-item h5">Grand Total: Rs.<input
								class="form-control form-check-inline ms-1" id="total"
								name="total" type="number" value="<%=total%>" disabled
								style="width: 30%;"></li>
							<li class="list-group-item h5">Payment Mode: <span
								class="text-muted">COD</span></li>
						</ul>
						<div class="form-floating w-50 float-start">
							<input type="text" class="form-control" name="address"
								placeholder="Your Address" required> <label
								for="address" class="fw-bold" style="color: #6174f0;">Shipping
								Address</label>
						</div>
						<div class="form-floating w-50 float-start mt-1">
							<input type="text" class="form-control" name="phone"
								placeholder="Your Phone Number" minlength=10 maxlength=10
								required> <label for="phone" class="fw-bold"
								style="color: #6174f0;">Phone Number</label>
						</div>
						<div class="text-center">
							<button type="submit"
								class="btn btn-outline-primary float-end mt-4">Place
								Your Order</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>