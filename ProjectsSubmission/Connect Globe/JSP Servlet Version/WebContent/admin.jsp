<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<%@ include file="TopHeader.jsp" %>
     <a href="User.jsp" class="btn btn-primary">Add User</a>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Mobile</th>
      <th scope="col">Gender</th>
      <th scope="col" colspan="3">Actions</th>
      
    </tr>
  </thead>
  <tbody>
  <c:forEach var="st" items="${userDetails}">
  
  <c:if test = "${st.getRoles() == 'USER'}">
    <tr>
      <td >${st.getFullname()}</td>
      <td>${st.getEmail()}</td>
      <td>${st.getMobileNumber()}</td>
      <td >${st.getGender()}</td>
      <td colspan="3">
                       <a href="viewServlet?id=<c:out value='${st.getUserId()}' />" class="btn btn-info">View</a>      
                        <a href="deleteServlet?id=<c:out value='${st.getUserId()}'/>" class="btn btn-danger">Delete</a>
                        </td>
    </tr></c:if>
    </c:forEach>
  </tbody>
</table>

</body>
</html>