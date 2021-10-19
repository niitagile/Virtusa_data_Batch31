<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/itemslist.component.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role=(String)session.getAttribute("role");
	if(session.getAttribute("username")==null || session.getAttribute("role")==null){response.sendRedirect("login.jsp");}
	else{
	%>
	<%@include file="navbar.jsp" %>
	 <div class="container">
        <div class="row justify-content-center align-items-center">
          <%
			String result = (String) session.getAttribute("message");
		session.removeAttribute("message");
		if (result != null) {
		%>
		<div class="alert alert-success alert-dismissible fade show text-center w-25 translate-middle"
			role="alert" style="margin-left:25%;margin-top:5%">
			<strong><%=result %></strong>
			<button type="button" class="btn-close" data-dismiss="alert"
				aria-label="Close" style="float: right;">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%} %>
          <div class="col-12 mt-5">
            <div class="row justify-content-around">
             <c:forEach items="${items}" var="item">
              <div class="col-lg-3 col-md-6 col-sm-12">
                <div class="card shadow rounded mb-4 me-2"  style="width: 18rem;height: 20rem;">
                  <div class="card-body">
                    <h6 class="card-title fw-bold text-primary text-center">${item.itemName}</h6>
                    <hr>
                    <div class="row">
                      <div class="col">
                        <div class="right-content mt-4">
                         <div class="data p-4">
                          <span class="fw-bold text-secondary">Description:</span>  <span>${item.description }</span><br>
                           <span class="fw-bold text-secondary">Price:</span> <span>${item.price }</span><br>
                           <span class="fw-bold text-secondary">Quantity Available:</span>   <span>${item.quantity }</span><br>
                          </div> 
                        </div>
                      </div>
                    </div>
                   <div class="text-center">
                   	<a href="<%=request.getContextPath()%>/AddToCart?id=${item.id}" class="btn btn-primary btn-center w-50 mt-3" >Add to cart</a>
                   </div> 
                  </div>
                </div>
              </div>
              </c:forEach>
              
            </div>
          </div>
        </div>
      </div>
      <%} %>
</body>
</html>