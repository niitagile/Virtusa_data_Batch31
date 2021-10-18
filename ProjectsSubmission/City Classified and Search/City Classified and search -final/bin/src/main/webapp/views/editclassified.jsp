<%@page import="java.util.List, com.cityclassifiedandsearch.bean.Classified"%>
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
        <a class="navbar-brand" href="/user/index">City Classified And Search</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          	<li class="nav-item">
              <a class="nav-link" href="/user/index">Classifieds</a>
            </li>
            <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="/user/postclassified">Post Classified</a></li>
	            <li><a class="dropdown-item" href="/user/myclassifieds">My Classifieds</a></li>
	          </ul>
        	</li>
            <li class="nav-item">
              <a class="nav-link" href="/user/index2">City Details</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/user/viewupdates">New Updates</a>
            </li>
          </ul>
		  <a class="btn btn-outline-light" href="/logout">Logout</a>
        </div>
      </div>
    </nav>
    
    <%
    	Classified classified = (Classified) request.getAttribute("classified");
    %>
    
    <div class="container mt-3">
    	<div class="card col-4 px-3 mx-auto">
	    	<h5 class="mt-3">Edit Classified</h5>
	      	<form action="/user/editclassified/<%= classified.getClassifiedId() %>" method="post" enctype="multipart/form-data">
			  <div class="mb-3">
			      <label for="classifiedCategory" class="form-label">Category</label>
			      <select id="classifiedCategory" class="form-select" name="classifiedCategory">
			        <option value="buy" <% if(classified.getClassifiedCategory().equals("buy")) {%> selected <% } %>>Buy</option>
					<option value="sell" <% if(classified.getClassifiedCategory().equals("sell")) {%> selected <% } %>>Sell</option>
					<option value="rent" <% if(classified.getClassifiedCategory().equals("rent")) {%> selected <% } %>>Rent</option>
					<option value="parttime" <% if(classified.getClassifiedCategory().equals("parttime")) {%> selected <% } %>>Part Time Job</option>
			      </select>
			  </div>
			  <div class="mb-3">
			    <label for="classifiedTitle" class="form-label">Title</label>
			    <input type="text" class="form-control" value="<%= classified.getClassifiedTitle() %>" id="classifiedTitle" name="classifiedTitle">
			  </div>
			  <div class="mb-3">
			    <label for="description" class="form-label">Description</label>
			    <input type="text" class="form-control" value="<%= classified.getDescription() %>" id="description" name="description">
			  </div>
			  <%
		      	String img = "";
		      	String classifiedImage = classified.getClassifiedimage();
		      	if(classifiedImage == null) {
		      		img = "/images/noimage.jpg";
		      	}
		      	else {
		      		img = "data:image/jpeg;base64," + classifiedImage;
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
	  	</div>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</body>
</html>