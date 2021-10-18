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
.searchbar{
  align-self:center;
  background-color: black;
  opacity:0.7;
  margin: auto;
  width: 50%;
  padding: 10px;
  
}
#search{
width:70%;
}
.display{
background-color: black;
opacity: 0.9;
color:white;
width: 1000px;
height:1000px;
padding: 50px;
margin: 20px;
}
.classified{
background:white;
color:black;
padding: 20px 30px 50px;
}
.searchDisp{
background:#72A0C1;
color:black;
padding: 20px 30px ;
}
</style>
<script>
function sendRequest(classified){
	
	var xmlHttp=new XMLHttpRequest();

	xmlHttp.open("POST","SearchClassified", true);
	xmlHttp.setRequestHeader("content-type", "application/x-www-form-urlencoded")
	xmlHttp.send("search="+classified);
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200)
			
			document.getElementById("searchdisp").innerHTML=xmlHttp.responseText;
	}

}
</script>
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
 <div class="searchbar">
		<input type="text" name="state" placeholder="Search Category" onkeyup="sendRequest(this.value)">
	</div>
 <div class=display>
 <p>Hi ${user.userName}</p>
 <div id="searchdisp" class="searchDisp"></div>
 <%List<Classified> list=ClassifiedDAO.getAllClassified();
		for(Classified cs:list){%>
		<div class=classified>
			<%User user=cs.getUser(); %>
			Category:<%=cs.getClassifiedCategory() %><br>
			Title:<%=cs.getClassifiedTitle() %><br>
			Name:<%=user.getUserName() %>
			Description:<%=cs.getDescription() %><br>
			Address:<%=user.getUserAddress() %><br>
			City:<%=user.getUserCity() %><br>
			Contact:<%=user.getMobile() %><br>
	    </div>  
	    <% if(MyUtil.getLoginedUser(session).getRole().equalsIgnoreCase("admin")) { %>
	    <a href="DeleteClassified?classifiedId=<%=cs.getClassifiedId()%>">Delete</a>
	<% }
}%>
 </div>
</body>
</html>