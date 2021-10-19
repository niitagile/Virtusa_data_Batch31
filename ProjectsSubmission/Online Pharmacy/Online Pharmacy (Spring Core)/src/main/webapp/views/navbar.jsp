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
<style>
.navbar-custom {
	background-color: #3949ab;
}

.material-icons-outlined {
	float: left;
	margin:2px;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</head>
<body>
	<%
		String uname = (String) session.getAttribute("username");
	String authority = (String) session.getAttribute("role");
	%>
	<nav
		class="navbar fixed-top navbar-expand-lg navbar-custom navbar-dark  py-3 p-3">
		<a class="navbar-brand" href="<%=request.getContextPath() %>/views/home.jsp">Welcome <%
			if (uname != null) {
		%> <%=uname%> <%
 	}
 %></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav ms-auto">
				<%
					if (uname == null) {
				%>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/views/login.jsp"
					class="nav-link active">Login</a></li>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/views/signup.jsp"
					class="nav-link active">Register</a></li>
				<%
					} else {
						System.out.println(uname);
						
				%>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/views/dashboard.jsp"
					class="nav-link active"><span class="material-icons-outlined">
							home </span>Home</a></li>
				<%
					if (authority.equals("USER")) {
				%>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/views/cart.jsp"
					class="nav-link active"><span class="material-icons-outlined">
							shopping_cart </span>Cart</a></li>
				<%
					if (session.getAttribute("category") != null) {
				%>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/GetItemsServlet"
					class="nav-link active"><span class="material-icons-outlined">
							inventory_2 </span>Continue Shopping</a></li>
				<%
					}
				}
				%>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/LogoutServlet"
					class="nav-link active"><span class="material-icons-outlined">
							logout </span>Logout</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>
</body>
</html>