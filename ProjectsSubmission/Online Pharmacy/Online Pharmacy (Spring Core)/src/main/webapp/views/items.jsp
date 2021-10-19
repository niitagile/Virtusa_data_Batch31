<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
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
	String role = (String) session.getAttribute("role");

	if (session.getAttribute("username") == null
			|| session.getAttribute("role") == null && (role.equals("DISTRIBUTOR") || role.equals("ADMIN"))) {
		response.sendRedirect("login.jsp");
	} else {
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<%
			String result = (String) session.getAttribute("message");
		session.removeAttribute("message");
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
		<!--div
			class="alert alert-success text-center position-relative top-50 start-50 translate-middle"
			style="width: 30%;" role="alert">Success!</div-->
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
					<form action="<%=request.getContextPath()%>/AddDistributorItem"
						method="POST">
						<div class="form-floating">
							<input type="text" class="form-control" name="itemName"
								placeholder="itemName" required> <label for="itemName">Item
								Name</label>
						</div>
						<div class="form-floating">
							<input type="text" class="form-control" name="description"
								placeholder="Description" required> <label
								for="description">Description</label>
						</div>
						<div class="form-floating">
							<input type="number" step="any" class="form-control" name="price"
								placeholder="Product Price" required> <label for="price">Product
								Price</label>
						</div>
						<div class="form-floating">
							<input type="number" class="form-control" name="quantity"
								placeholder="Quantity" required> <label for="quantity">Quantity</label>
						</div>
						<select class="form-select form-select-lg m-3"
							aria-label=".form-select-lg example" name="category" style="width:92%;color: #6174f0;" required>
							<option selected>Choose Supply Category</option>
							<option value="Basic Supplies">Basic Supplies</option>
							<option value="Medications">Medications</option>
							<option value="Toiletries">Toiletries</option>
						</select>
						<p class="text-secondary ms-3">Choose same supply</p>
						<div class="m-3">
							<button type="submit" style="width: 10rem;"
								class="btn btn-outline-success">Add Item</button>
							<button type="button" class="btn btn-outline-danger"
								style="float: right; width: 10rem;" onclick="revert()">Cancel</button>
						</div>

					</form>
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
					<a href="<%=request.getContextPath()%>/GetDistributorItems"
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