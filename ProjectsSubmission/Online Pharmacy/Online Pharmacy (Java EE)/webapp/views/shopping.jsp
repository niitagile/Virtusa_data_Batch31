<%@page import="com.pharmacy.bean.ItemsBean"%>
<%@page import="java.util.List"%>
<%@page import="com.pharmacy.dao.ItemsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
<style>
body {
	background-color: #e8eaf6 !important;
}

.container {
	margin-top: 5%;
	position: absolute;
	left: 15%;
}
</style>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role = (String) session.getAttribute("role");

	if (session.getAttribute("username") == null || session.getAttribute("role") == null && (!role.equals("USER"))) {
		response.sendRedirect("login.jsp");
	} else {
		ItemsDAO dao = new ItemsDAO();
		List<ItemsBean> items = dao.getAllItems();
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="row justify-content-center align-items-center">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center search-bar">
					<h3 class="mt-2 text-center text-primary heading">Shop By
						Category</h3>
				</div>
			</div>
			<div class="col-12 mt-5 card-column">
				<div class="row justify-content-around">
					<%
						if (!items.isEmpty()) {
						for (ItemsBean item : items) {
							if(!item.getCategory().equals("Medications")){
					%>

					<div class="col-lg-3 col-md-6 col-sm-12">
						<div class="card shadow rounded mb-4 me-2"
							style="width: 18rem; height: 26rem;">
							<div class="card-body">
								<h6 class="card-title fw-bold text-primary text-center"><%=item.getCategory()%></h6>
								<hr>
								<div class="row">
									<div class="col">
										<div class="right-content mt-4">
											<%
												if (item.getCategory().equals("Basic Supplies")) {
											%>
											<img
												src="${pageContext.request.contextPath}/assets/supplies.jpg"
												class="ms-2" style="width: 15rem; height: 10rem;">
											<%
												} else if(item.getCategory().equals("Toiletries")) {
											%>
											<img
												src="${pageContext.request.contextPath}/assets/toiletries.jfif"
												class="ms-2" style="width: 15rem; height: 10rem;">
											<%
												}%>
											<div class="data p-4">
												<span class="fw-bold text-secondary">Distributor:</span> <span><%=item.getDistributor()%></span><br>
											</div>
										</div>
									</div>
								</div>
								<div class="text-center">
									<a
										href="<%=request.getContextPath()%>/GetItemsServlet?category=<%=item.getCategory()%>&distributor=<%=item.getDistributor()%>"
										class="btn btn-primary btn-center w-50 mt-3">Go </a>
								</div>
							</div>
						</div>
					</div>
					<%
						}}
					}
					%>

				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>