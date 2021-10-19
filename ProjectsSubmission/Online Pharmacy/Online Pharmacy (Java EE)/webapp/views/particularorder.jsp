<%@page import="java.io.OutputStream"%>
<%@page import="com.pharmacy.bean.OrdersBean"%>
<%@page import="com.pharmacy.dao.OrdersDAO"%>
<%@page import="com.pharmacy.bean.ParticularOrderProductBean"%>
<%@page import="java.util.List"%>
<%@page import="com.pharmacy.dao.ParticularProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="../assets/particularorder.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script 
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>	
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
	integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
	crossorigin="anonymous"></script>
<script>
window.onload = function () {
    document.getElementById("download")
        .addEventListener("click", () => {
            const invoice = this.document.getElementById("cart-card");
            console.log(invoice);
            console.log("window"+window);
             var opt = {
                margin: [30,40,3,4],
                filename: 'myBill.pdf',
                image: { type: 'jpeg', quality: 0.98 },
                html2canvas: { scale: 5 },
                
                jsPDF: { unit: 'mm',format: 'letter',orientation:'l' }
            };
            html2pdf().from(invoice).set(opt).save();
        });
}
</script>
<style>
label {
	font-weight: bold;
	color: #6174f0;
}

#prescription {
	width: 400px;
	height: 400px;
	margin-left: 25%;
}

@media screen and (max-width:670px) {
	#prescription {
		width: 300px;
		height: 300px;
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
	String role = (String) session.getAttribute("role");
	if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
		response.sendRedirect("login.jsp");
	} else {
		ParticularProductDAO dao = new ParticularProductDAO();
		OrdersDAO orderDao = new OrdersDAO();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		List<ParticularOrderProductBean> products = dao.getAllItemsOfOrder(orderId);
		OrdersBean order = orderDao.getOrderByOrderID(orderId);
	%>
	<%@include file="navbar.jsp"%>
	<div class="page-content container">
		<div class="container px-0">
			<div class="card shadow rounded w-75" id="cart-card">
				<div class="card-body">
					<div class="row mt-4">
						<div class="col-12 col-lg-10 offset-lg-1">

							<hr class="row brc-default-l1 mx-n1 mb-4" />

							<div class="row">
								<div class="col-sm-6">
									<div>
										<span class="text-sm text-grey-m2 align-middle">To:</span> <span
											class="text-600 text-110 text-blue align-middle"><%=order.getUsername()%></span>
									</div>
									<div class="text-grey-m2">
										<div class="my-1"><%=order.getAddress()%></div>
										<div class="my-1">
											<i class="fa fa-phone fa-flip-horizontal text-secondary"></i>
											<b class="text-600"><%=order.getPhoneNumber()%></b>
										</div>
									</div>
								</div>

								<div
									class="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">
									<hr class="d-sm-none" />
									<div class="text-grey-m2">
										<div class="mt-1 mb-2 text-secondary-m1 text-600 text-125">
											Invoice</div>

										<div class="my-2">
											<i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span
												class="text-600 text-90">ID:</span>
											<%=order.getOrderId()%>
										</div>

										<div class="my-2">
											<i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span
												class="text-600 text-90">Order Date:</span>
											<%=order.getOrderDate()%>
										</div>
									</div>
								</div>
							</div>

							<div class="mt-4">
								<div class="row text-600 text-white bgc-default-tp1 py-25">
									<div class="d-none d-sm-block col-1">#</div>
									<div class="col-9 col-sm-5">Description</div>
									<div class="d-none d-sm-block col-4 col-sm-2">Quantity</div>
									<div class="d-none d-sm-block col-sm-2">Discount</div>
									<div class="col-2">Amount</div>
								</div>
								<%
									if (order.isMedicine()) {
								%>
								<img class="text-center border border-secondary mt-2"
									src="<%=request.getContextPath()%>/ShowPrescription?orderId=<%=order.getOrderId()%>"
									id="prescription" />

								<%
									}
								if (products != null) {
								for (ParticularOrderProductBean product : products) {
								%>

								<div class="text-95 text-secondary-d3">
									<div class="row mb-2 mb-sm-0 py-25">
										<div class="d-none d-sm-block col-1"><%=product.getId()%></div>
										<div class="col-9 col-sm-5"><%=product.getItemName()%></div>
										<div class="d-none d-sm-block col-2"><%=product.getQuantity()%></div>
										<div class="d-none d-sm-block col-2 text-95">-</div>
										<div class="col-2 text-secondary-d2"><%=product.getPrice()%></div>
									</div>

								</div>
								<hr>

								<%
									}
								}
								%>
								<div class="row border-b-2 brc-default-l2"></div>

								<div class="row mt-3">
									<div class="col-12 col-sm-7 text-grey-d2 text-95 mt-2 mt-lg-0">
										<b>Payment mode:</b> COD
									</div>

									<div
										class="col-12 col-sm-5 text-grey text-90 order-first order-sm-last">
										<div class="row my-2">
											<div class="col-7 text-right fw-bold">Total Quantity</div>
											<div class="col-5">
												<span class="text-120 text-secondary-d1"><%=order.getTotalQuantity()%></span>
											</div>
										</div>
										<div class="row my-2">
											<div class="col-7 text-right fw-bold">SubTotal</div>
											<div class="col-5">
												<span class="text-120 text-secondary-d1"><%=order.getPrice()%></span>
											</div>
										</div>

										<div class="row my-2 align-items-center">
											<div class="col-7 text-right fw-bold">Total Amount</div>
											<div class="col-5">
												<span class="text-150 text-success-d3 opacity-2"><%=order.getPrice()%></span>
											</div>
										</div>
									</div>
								</div>
								<hr />
								<div>
									<%
										if (role.equals("USER")) {
									%>
									<span class="text-secondary-d1 text-105 float-start">Thank
										you for shopping with us</span>
									<%
										} else {
									%>
									<form class="form-inline"
										action="<%=request.getContextPath()%>/UpdateOrder?orderId=<%=order.getOrderId()%>&role=<%=role%>"
										method="POST">
										<select class="form-select form-select-lg w-50"
											aria-label=".form-select-lg example" name="status"
											style="color: #6174f0" required>
											<option selected>Update Order</option>
											<option value="ACCEPTED">Accept</option>
											<option value="REJECTED">Reject</option>
											<option value="DELIVERED">Delivered</option>
										</select>
										<div class="form-floating w-50">
											<input type="text" class="form-control" name="message"
												placeholder="Message" value="<%=order.getMessage()%>"
												required> <label for="message">Status</label>
										</div>
										<%
											if (order.isMedicine()) {
										%>
										<br>
										<div class="form-floating w-50 float-end">
											<input type="number" class="form-control" name="quantity"
												placeholder="Quantity" value="<%=order.getTotalQuantity()%>"
												required> <label for="quantity">Quantity</label>
										</div>
										<div class="form-floating w-50">
											<input type="number" class="form-control" name="price"
												placeholder="Price" value="<%=order.getPrice()%>" required>
											<label for="price">Price</label>
										</div>
										<br>
										<%
											}
										if (role.equals("ADMIN")) {
										%>
										<a role="button"
											href="<%=request.getContextPath()%>/DeleteOrder?id=<%=order.getOrderId()%>&medicine=<%=order.isMedicine()%>"
											class="btn btn-outline-danger btn-lg ms-1 float-end">Delete</a>
										<%
											}
										%>
										<button type="submit"
											class="btn btn-outline-success btn-lg float-end ">Update</button>

									</form>
									<%
										}
									%>
									<button class="btn btn-outline-primary btn-lg me-1 float-end"
										id="download">Download as PDF</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>