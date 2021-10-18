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
height:1000 px;
padding: 50px;
margin: 20px;
}
.classified{
background:white;
color:black;
padding: 20px 30px 50px;
}
.searchdisp{
background:#72A0C1;
color:black;
padding: 20px 30px ;
}
</style>
<script>
function sendRequest(city){
	
	var xmlHttp=new XMLHttpRequest();

	xmlHttp.open("POST","SearchCity", true);
	xmlHttp.setRequestHeader("content-type", "application/x-www-form-urlencoded")
	xmlHttp.send("search="+city);
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200)
			
			document.getElementById("city").innerHTML=xmlHttp.responseText;
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
		<input type="text" name="state" placeholder="Search City" onkeyup="sendRequest(this.value)">
	</div>
 <div class=display>
 <div id="city"class="searchdisp"></div>
 <p>Hi ${user.userName}</p>
 <%
 List<Citydetails> list=CityDAO.getAllCity();
		for(Citydetails cs:list){%>
		<div  class=classified>
			City:<%=cs.getCity() %><br>
			Category:<%=cs.getCategory() %><br>
			Name:<%=cs.getName() %><br>
			Address:<%=cs.getAddress()%><br>
			Link:<a href="<%=cs.getLink()%>"><%=cs.getLink()%></a>		
	    </div>
	    <% if(MyUtil.getLoginedUser(session).getRole().equalsIgnoreCase("admin")) { %>
	    <a href="DeleteCity?cityId=<%=cs.getCityId()%>">Delete</a>
	    <button onclick="document.getElementById('id03').style.display='block'" style="width:auto;">Edit CityDetails</button>
	<% } %> 
	   

 <div id="id03" class="modal">
  <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="UpdateCity?cityId=<%=cs.getCityId()%>" method="post"  >
    <div class="container">
      <h1>Edit CityDetails</h1>
      
		<select id="category" name="category"  >
		<option value="mall">Mall</option>
		<option value="hospital">Hospital</option>
		<option value="school">School</option>
		<option value="hotel">Hotel</option>
		</select>
		
		<input type="text" id="name" value="<%=cs.getName()%>" name="name" required>
		
		<input type="text" id="address" value="<%=cs.getAddress() %>" name="address" required >
		
		<input type="text" id="cityName" value="<%=cs.getCity()%>"name="city" required >
		
		<input type="text" id="link" value="<%=cs.getLink() %>" name="link" required  >
		
		 
      <div class="clearfix">
              <button type="submit" class="signupbtn">Post</button>
      </div>
    </div>
  </form>
 </div>
 <br>
	<% } %>
	 </div>
</body>
</html>