<%@page import="com.pharmacy.model.OrdersBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/orders.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
	integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
	crossorigin="anonymous"></script>
<script>
	function orders() {
		document.getElementById("orders").style.display = "block"
		document.getElementById("buttonCard").style.display = "none"
	}
	function revert() {
		document.getElementById("orders").style.display = "none"
		document.getElementById("buttonCard").style.display = "block"
	}
</script>
<style>
#buttonCard {
	margin-top: 20%;
	margin-left: 45%;
}

#order-form {
	margin-left: 15%;
}

#alert {
	margin-left: 20%;
}

@media screen and (max-width:670px) {
	#buttonCard {
		margin-top: 40%;
		margin-left: 0%;
	}
	#alert {
		margin-left: 0%;
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
	String username = (String) session.getAttribute("username");
	String role = (String) session.getAttribute("role");

	if (username == null || token == null) {
		response.sendRedirect("login");
	} else {
		List<OrdersBean> orders = (List<OrdersBean>) request.getAttribute("orders");
	%>

	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">

		<%
		if (!token.contains("USER")) {
		%>
		<div
			class="card bg-light border-primary shadow rounded text-center choose-block"
			style="height: 15rem;" id="buttonCard">
			<h3 class="card-header text-primary"
				style="text-align: center; font-weight: bold;">Choose an option</h3>
			<div class="card-body">
				<a href="<%=request.getContextPath()%>/medicinerequest"
					role="button" class="btn btn-primary col-11 mt-4">View Medicine
					Requests</a>
				<button type="button" class="btn btn-primary col-11 mt-3"
					onclick="orders()">Your Orders</button>
			</div>
		</div>
		<%
		}
		%>
		<div class="display-content" <%if (!token.contains("USER")) {%>
			style="display: none;" <%}%> id="orders">
			<p class="fs-2 text-center text-primary fw-bold mt-2 mb-4 heading">
				<u>Your Orders</u>
			</p>
			<br>
			<%
			if (token.contains("ADMIN") || token.contains("DISTRIBUTOR")) {
			%>
			<form action="/pdfConvertByDate/<%=role%>" id="order-form"
				method="post">
				<div class="row align-items-center g-3">
					<div class="col-auto">
						<label class="visually-hidden" for="date">Download By Date</label>
						<input type="date" class="form-control" name="date" id="date"
							placeholder="Download By Date">
					</div>
					<div class="col-auto">
						<input type="submit" value="Download Orders by Date"
							class="btn btn-secondary">
					</div>
					<div class="col-auto">
						<a href="/pdfConvert/<%=role%>"
							class="btn btn-primary text-center">Export all orders as PDF</a>
					</div>
				</div>
			</form>
			<br>
			<%}
			if (orders != null && !orders.isEmpty()) {
				for (OrdersBean order : orders) {
					String status = order.getStatus();
					if (order.getDistributorName() == null)
				order.setDistributorName("PENDING");
			%>
			<div class="row">
				<div class="col-lg-5 col-md-5 d-none d-lg-block">
					<div
						class="card text-dark mt-3 mb-3 shadow bg-white rounded left-card"
						style="padding: 10px; margin-left: 35%;">
						<div class="card-body">
							<h5 class="card-title text-primary">
								<b>Order ID:</b>&nbsp;<%=order.getOrderId()%></h5>
							<p class="card-text">
								<b class="text-dark">Order Date:</b>&nbsp;
								<%=order.getOrderDate()%></p>
							<p class="card-text">
								<b class="text-dark">Status:</b>&nbsp;
								<%
								if (status.equals("ACCEPTED")) {
								%>
								<span>
									<button class="btn btn-outline-success btn-sm ms-2">
										<span class="material-icons-outlined me-2"
											style="float: left;"> thumb_up_alt </span> ACCEPTED
									</button>
								</span>
								<%
								} else if (status.equals("PENDING")) {
								%>
								<span>
									<button class="btn btn-outline-secondary btn-sm ms-2">
										<span class="material-icons-outlined me-2"
											style="float: left;"> pending_actions </span>Pending
									</button>
								</span>
								<%
								} else if (status.equals("REJECTED")) {
								%>
								<span>
									<button class="btn btn-outline-danger btn-sm ms-2">
										<span class="material-icons-outlined me-2"
											style="float: left;"> thumb_down </span>Rejected
									</button>
								</span>
								<%
								} else if (status.equals("DELIVERED")) {
								%>
								<span>
									<button class="btn btn-outline-primary btn-sm ms-2">
										<span class="material-icons-outlined me-2"
											style="float: left;"> local_shipping </span>Delivered
									</button>
								</span>
								<%
								}
								%>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-12 offset-sm-2 col-lg-7 col-md-7 offset-md-0">
					<div
						class="card text-dark bg-white mb-3 mt-3 shadow rounded right-card"
						style="margin-left: 25%; padding: 15px;">
						<div class="card-body">
							<ul class="nav position-absolute top-0 end-0 mt-5 me-2">
								<li><span><a
										href="${pageContext.request.contextPath}/particularOrder/<%=order.getOrderId()%>"
										role="button" class="btn btn-success btn-sm"> <%
 if (token.contains("USER")) {
 %>View Bill<%
 } else {
 %>Update<%
 }
 %>
									</a></span></li>
								<%
								if (status.equals("REJECTED")) {
								%>
								<li><a
									href="<%=request.getContextPath()%>/DeleteOrder?id=<%=order.getOrderId()%>&medicine=<%=order.isMedicine()%>"
									class="btn btn-danger btn-sm ms-2"> <span
										class="material-icons-outlined me-2" style="float: left;">
											delete </span>Delete
								</a></li>
								<%
								}
								%>
							</ul>
							<h5 class="card-title text-primary">
								<%
								if (token.contains("USER")) {
								%>
								<b>Distributor Name: </b><%=order.getDistributorName()%>
								<%
								} else {
								%>
								<b>Customer Name: </b><%=order.getUsername()%>
								<%
								}
								%>
							</h5>
							<%
							if (token.contains("ADMIN")) {
							%>
							<p class="card-text float-end mt-4">
								<b class="text-dark">Distributor:</b>&nbsp;
								<%=order.getDistributorName()%></p>
							<%
							}
							%>
							<p class="card-text">
								<b class="text-dark">Total Price:</b>&nbsp;
								<%=order.getPrice()%></p>
							<p class="card-text">
								<b class="text-dark">Status:</b>&nbsp;
								<%=order.getMessage()%></p>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<div
				class="alert alert-danger text-center position-absolute top-50 start-50 translate-middle w-50 mt-5"
				role="alert" id="alert">No Orders!</div>

			<%
			}
			%>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>