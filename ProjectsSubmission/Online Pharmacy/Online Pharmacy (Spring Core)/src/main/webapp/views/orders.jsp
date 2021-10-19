<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.pharmacy.dao.OrdersDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pharmacy.bean.OrdersBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="../assets/orders.component.css">
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
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</head>
<body> 
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role = (String) session.getAttribute("role");
	String username = (String) session.getAttribute("username");
	if (username == null || session.getAttribute("role") == null) {
		response.sendRedirect("login.jsp");
	} else {
		ApplicationContext context = new AnnotationConfigApplicationContext(com.pharmacy.JdbcConfig.class);
		OrdersDAO dao = context.getBean("ordersDao", OrdersDAO.class);
		List<OrdersBean> orders = dao.getOrdersByNameAndRole(username, role);
	%>

	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">
		
		<div class="display-content">
			<p class="fs-2 text-center text-primary fw-bold mt-2 mb-4 heading">
				<u>Your Orders</u>
			</p>
			<%
				if (orders != null && !orders.isEmpty()) {
				for (OrdersBean order : orders) {
					String status = order.getStatus();
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
										href="<%=request.getContextPath()%>/views/particularorder.jsp?orderId=<%=order.getOrderId()%>"
										role="button" class="btn btn-success btn-sm">
											<%
												if (role.equals("USER")) {
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
									href="<%=request.getContextPath()%>/DeleteOrder?id=<%=order.getOrderId()%>"
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
									if (role.equals("USER")) {
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
								if (role.equals("ADMIN")) {
							%>
							<p class="card-text float-end mt-4">
								<b class="text-dark">Distributor:</b>&nbsp;
								<%=order.getDistributorName()%></p>
							<%
								}
							%>
							<p class="card-text">
								<b class="text-dark">Total Quantity:</b>&nbsp;
								<%=order.getTotalQuantity()%></p>
							<p class="card-text">
								<b class="text-dark">Total Price:</b>&nbsp;
								<%=order.getPrice()%></p>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			} else {
			%>
			<div class="alert alert-danger text-center w-100 mt-5" role="alert">No
				orders!</div>

			<%} %>
		</div>
	</div>
	<%} %>
</body>
</html>