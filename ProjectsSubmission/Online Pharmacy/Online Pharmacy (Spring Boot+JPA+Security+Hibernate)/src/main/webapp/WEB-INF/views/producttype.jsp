<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	String username = (String) session.getAttribute("username");
	String role = (String) session.getAttribute("role");
	if (username == null || session.getAttribute("role") == null) {
		response.sendRedirect("login.jsp");
	} else {
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="d-flex justify-content-center">
			<div class="card bg-light border-primary shadow rounded"
				id="itemForm" style="width: 25rem; display: none;">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Order Medicine</h3>
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/fileUpload" method="POST" enctype="multipart/form-data">
						<p class="text-secondary ms-3">Only image format is allowed!</p>
						<div class="m-3">
							<label for="prescription" class="form-label">Upload
								Prescription</label> <input class="form-control" type="file"
								name="prescription" id="prescription" required/>
						</div>
						<div class="form-floating">
							<input type="text" class="form-control" name="address"
								placeholder="Your Address" required/> <label
								for="address" class="fw-bold" style="color: #6174f0;">Shipping
								Address</label>
						</div>
						<div class="form-floating mt-1">
							<input type="text" class="form-control" name="phone"
								placeholder="Your Phone Number" minlength=10 maxlength=10
								required/> <label for="phone" class="fw-bold"
								style="color: #6174f0;">Phone Number</label>
						</div>
						<button type="submit" class="btn btn-primary col-11 ms-3 mt-3">Go</button>
					</form>
				</div>
			</div>
			<div
				class="card bg-light border-primary shadow rounded text-center choose-block"
				style="height: 15rem;" id="buttonCard">
				<h3 class="card-header text-primary"
					style="text-align: center; font-weight: bold;">Choose an
					option</h3>
				<div class="card-body">
					<button type="button" class="btn btn-primary col-11 mt-4"
						onclick="addForm()">Order Medicine</button>
					<a href="<%=request.getContextPath()%>/shopping"
						role="button" class="btn btn-primary col-11 mt-3">Order
						Supplies</a>
				</div>
			</div>

		</div>
	</div>
	<%
		}
	%>
</body>
</html>