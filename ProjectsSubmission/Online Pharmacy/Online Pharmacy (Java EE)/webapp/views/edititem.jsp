<%@page import="com.pharmacy.bean.DistributorItemBean"%>
<%@page import="com.pharmacy.dao.DistributorItemDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/items.component.css">
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
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role = (String) session.getAttribute("role");
	if (session.getAttribute("username") == null || session.getAttribute("role") == null || role.equals("USER")) {
		response.sendRedirect("login.jsp");
	} else {
		DistributorItemDAO dao=new DistributorItemDAO();
		DistributorItemBean item=dao.getItemById(Integer.parseInt(request.getParameter("itemId")));
	%>
	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">
		<div class="card bg-light border-primary shadow rounded" id="itemForm"
			style="width: 25rem;">
			<h3 class="card-header text-primary"
				style="text-align: center; font-weight: bold;">Update Product</h3>
			<div class="card-body">
				<form
					action="<%=request.getContextPath()%>/EditDistributorItem?id=<%=item.getId() %>"
					method="POST">
					<div class="form-floating">
						<input type="text" class="form-control" name="itemName"
							placeholder="itemName" value="<%=item.getItemName() %>" required>
						<label for="itemName">Item Name</label>
					</div>
					<div class="form-floating">
						<input type="text" class="form-control" name="description"
							placeholder="Description" value="<%=item.getDescription() %>" required>
						<label for="description">Description</label>
					</div>
					<div class="form-floating">
						<input type="number" step="any" class="form-control" name="price"
							placeholder="Product Price" value="<%=item.getPrice() %>" required>
						<label for="price">Product Price</label>
					</div>
					<div class="form-floating">
						<input type="number" class="form-control" name="quantity"
							placeholder="Quantity" value="<%=item.getQuantity() %>" required>
						<label for="quantity">Quantity</label>
					</div>
					<div class="m-3 text-center">
						<button type="submit" style="width: 10rem;"
							class="btn btn-outline-success">Update Item</button>
					</div>

				</form>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>