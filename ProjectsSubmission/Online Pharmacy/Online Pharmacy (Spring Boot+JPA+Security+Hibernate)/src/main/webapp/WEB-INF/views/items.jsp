<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/items.component.css">
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
<script>
	function addForm() {
		document.getElementById("itemForm").style.display = "block"
		document.getElementById("buttonCard").style.display = "none"
	}
	function revert() {
		document.getElementById("itemForm").style.display = "none"
		document.getElementById("buttonCard").style.display = "block"
	}
</script>
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
	<div class="container">
		<%
		String result = (String) request.getAttribute("message");
		if (result != null && result.equalsIgnoreCase("SUCCESS")) {
		%>
		<div
			class="alert alert-success alert-dismissible fade show text-center position-relative top-50 start-50 w-25 translate-middle"
			role="alert">
			<strong>Success!</strong>
			<button type="button" class="btn-close" data-dismiss="alert"
				aria-label="Close" style="float: right;">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
		} else if (result != null && result.equalsIgnoreCase("FAIL")) {
		%>
		<div
			class="alert alert-warning alert-dismissible fade show text-center position-relative top-50 start-50 w-25 translate-middle"
			role="alert">
			<strong>Something went wrong!</strong>
			<button type="button" class="btn-close" data-dismiss="alert"
				aria-label="Close" style="float: right;">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
		}
		%>
		<div class="d-flex justify-content-center">
			<div class="card bg-light border-primary shadow rounded"
				id="itemForm" style="width: 25rem; display: none;">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Add Product</h3>
				<div class="card-body">
					<form:form
						action="${pageContext.request.contextPath}/additem?username=${username}"
						method="POST" modelAttribute="item">
						<%
						if (token.contains("ADMIN")) {
						%>
						<div class="form-floating">
							<form:input type="text" class="form-control" name="distributor" path="distributor"
								placeholder="Distributor" required="required"/> <label
								for="distributor">Distributor Name</label>
						</div>
						<%
						}
						%>
						<div class="form-floating">
							<form:input type="text" class="form-control" name="itemName"
								placeholder="itemName" path="itemName" required="required"/>
							<label for="itemName">Item Name</label>
						</div>
						<div class="form-floating">
							<form:input type="text" class="form-control" name="description"
								placeholder="Description" path="description" required="required"/>
							<label for="description">Description</label>
						</div>
						<div class="form-floating">
							<form:input type="number" step="any" class="form-control"
								name="price" placeholder="Product Price" path="price" required="required"/>
							<label for="price">Product Price</label>
						</div>
						<div class="form-floating">
							<form:input type="number" class="form-control" name="quantity"
								placeholder="Quantity" path="quantity" required="required"/>
							<label for="quantity">Quantity</label>
						</div>
						<form:select class="form-select form-select-lg m-3"
							aria-label=".form-select-lg example" name="category"
							style="width:92%;color: #6174f0;" path="category">
							<form:option value="Choose Supply Category"
								label="Choose Supply Category" />
							<form:option value="Basic Supplies" />Basic Supplies</option>
							<form:option value="Medications" label="Medications" />
							<form:option value="Toiletries" label="Toiletries" />
						</form:select>
						<p class="text-secondary ms-3">Choose same supply</p>
						<div class="m-3">
							<button type="submit" style="width: 10rem;"
								class="btn btn-outline-success">Add Item</button>
							<button type="button" class="btn btn-outline-danger"
								style="float: right; width: 10rem;" onclick="revert()">Cancel</button>
						</div>

					</form:form>
				</div>
			</div>
			<div class="card bg-light border-primary shadow rounded text-center"
				style="height: 15rem;" id="buttonCard">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Choose an
					option</h3>
				<div class="card-body">
					<button type="button" class="btn btn-primary col-11 mt-4"
						onclick="addForm()">Add Item</button>
					<a
						href="${pageContext.request.contextPath}/getitems?username=${username}"
						class="btn btn-primary col-11 mt-3" role="button">Modify Item</a>
				</div>
			</div>

		</div>
	</div>
	<%
		}
	%>
</body>
</html>