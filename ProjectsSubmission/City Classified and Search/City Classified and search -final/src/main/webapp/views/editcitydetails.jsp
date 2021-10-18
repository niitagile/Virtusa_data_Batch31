<%@page import="java.util.List, com.cityclassifiedandsearch.bean.CityDetails, com.cityclassifiedandsearch.bean.User, org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
    <title>City Classified And Search</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="/admin/index">City Classified And Search</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="/admin/index">Classifieds</a>
            </li>
            <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="/admin/approve">Approve Classifieds</a></li>
	          </ul>
        	</li>
            <li class="nav-item">
              <a class="nav-link" href="/admin/index2">City Details</a>
            </li>
            <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="/admin/postcitydetails">Post City Details</a></li>
	            <li><a class="dropdown-item" href="/admin/mycitydetails">My City Details</a></li>
	          </ul>
        	</li>
        	<li class="nav-item">
              <a class="nav-link" href="/admin/viewupdates">News Updates</a>
            </li>
        	 <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="/admin/postupdates">Post updates</a></li>
	            <li><a class="dropdown-item" href="/admin/viewupdates">View updates</a></li>
	          </ul>
        	</li>
          </ul>
		  <a class="btn btn-outline-light" href="/logout">Logout</a>
        </div>
      </div>
    </nav>
    
    <%
    	CityDetails cityDetails = (CityDetails) request.getAttribute("cityDetails");
    %>
    
    <div class="container mt-3">
    	<div class="card col-4 px-3 mx-auto">
	    	<h5 class="mt-3">Edit City Details</h5>
	      	<form action="/admin/editcitydetails/<%= cityDetails.getCityId() %>" method="post" enctype="multipart/form-data">
			  <div class="mb-3">
			      <label for="category" class="form-label">Category</label>
			      <select id="category" class="form-select" name="category">
			        <option value="mall" <% if(cityDetails.getCategory().equals("mall")) {%> selected <% } %>>Mall</option>
					<option value="hospital" <% if(cityDetails.getCategory().equals("hospital")) {%> selected <% } %>>Hospital</option>
					<option value="school" <% if(cityDetails.getCategory().equals("school")) {%> selected <% } %>>School</option>
					<option value="hotel" <% if(cityDetails.getCategory().equals("hotel")) {%> selected <% } %>>Hotel</option>
			      </select>
			  </div>
			  <div class="mb-3">
			    <label for="name" class="form-label">Name</label>
			    <input type="text" class="form-control" value="<%= cityDetails.getName() %>" id="name" name="name">
			  </div>
			  <div class="mb-3">
			    <label for="address" class="form-label">Address</label>
			    <input type="text" class="form-control" value="<%= cityDetails.getAddress() %>" id="address" name="address">
			  </div>
			  <div class="mb-3">
			    <label for="cityName" class="form-label">City</label>
			    <input type="text" class="form-control" value="<%= cityDetails.getCity() %>" id="cityName" name="cityName">
			  </div>
			  <div class="mb-3">
			    <label for="link" class="form-label">Link</label>
			    <input type="text" class="form-control" value="<%= cityDetails.getLink() %>" id="link" name="link">
			  </div>
			  <%
		      	String img = "";
		      	String cityImage = cityDetails.getCityimage();
		      	if(cityImage == null) {
		      		img = "/images/noimage.jpg";
		      	}
		      	else {
		      		img = "data:image/jpeg;base64," + cityImage;
		      	}
		      %>
		      <div class="mt-2 mx-auto index-image-parent">
		      	<img src="<%= img %>" class="index-image">
		      </div>
			  <div class="mb-3">
				<label for="file" class="form-label">Upload Image</label>
				<input class="form-control" type="file" id="file" name="file">
			  </div>
			  <button type="submit" class="btn btn-outline-dark mb-3">Update</button>
	 		</form>
	 		<%
	    		String status = (String) request.getParameter("status");
	    		if(status != null) {
	    			if(status.equals("success")) {
	    	%>
				    	<div class="alert alert-success" role="alert">
						  Successfully Edited
						</div>
			<%
	    			}
	    			else if(status.equals("failed")) {
			%>
						<div class="alert alert-danger" role="alert">
						  Failed
						</div>
			<%
	    			}
	    		}
			%>
	  	</div>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</body>
</html>