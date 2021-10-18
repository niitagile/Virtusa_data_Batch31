<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%@page import="java.util.*,java.io.*" %>
    <%@page import="com.cityclassifiedandsearch.bean.*,com.cityclassifiedandsearch.dao.*,com.cityclassifiedandsearch.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">

.display{
background-color: black;
opacity: 0.9;
color:white;
width: 1000px;
height:1000 px;
padding: 50px;
margin: 20px;
}
.classified{
background:white;
color:black;
padding: 20px 30px 50px;
}
</style>
</head>
<body>
<%
response.setHeader("Cache-control","no-cache,no-store,must-revalidate");
response.setHeader("Pragma","no-cache");

if(session.getAttribute("user")==null)
{
	response.sendRedirect("index.jsp");
}
%>
 <jsp:include page="template.jsp"></jsp:include>
 <div class=display>
 <p>Hi ${user.userName}</p>
 <%
 int userId=MyUtil.getLoginedUser(session).getUserId();
 List<Classified> list=ClassifiedDAO.getUserClassified(userId);
		for(Classified cs:list){%>
		<div class=classified>
			<%User user=cs.getUser(); %>
			Category:<%=cs.getClassifiedCategory() %><br>
			Title:<%=cs.getClassifiedTitle() %><br>
			Name:<%=user.getUserName() %><br>
			Description:<%=cs.getDescription() %><br>
			Address:<%=user.getUserAddress() %><br>
			City:<%=user.getUserCity() %><br>
			Contact:<%=user.getMobile() %><br>
			
	    </div>    
	    <a href="DeleteClassified?classifiedId=<%=cs.getClassifiedId()%>">Delete</a>
	    <button onclick="document.getElementById('id03').style.display='block'" style="width:auto;">Edit CityDetails</button>

<div id="id03" class="modal">
  <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="UpdateClassified?classifiedId=<%=cs.getClassifiedId()%>" method="post" >
    <div class="container">
      <h1>Post Classified</h1>
      
		<select id="classifiedCategory" name="classifiedCategory">
		<option value="buy">Buy</option>
		<option value="sell">Sell</option>
		<option value="rent">Rent</option>
		<option value="partime">Part Time Jobs</option>
		</select><br>
		<input type="text" id="classifiedTitle" value="<%=cs.getClassifiedTitle() %>" name="classifiedTitle" required>
		
		<input type="text" id="description" value="<%=cs.getDescription() %>" name="description" required >
				 
      <div class="clearfix">
              <button type="submit" class="signupbtn">edit</button>
      </div>
    </div>
  </form>
 </div>
	<%}%>
 </div>
</body>
</html>