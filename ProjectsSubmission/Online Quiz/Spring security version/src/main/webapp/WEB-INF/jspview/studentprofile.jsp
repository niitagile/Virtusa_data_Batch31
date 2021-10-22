<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/resources/css/studentprofile.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<h1>Your profile</h1>
<!-- Student Profile -->
<div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent text-center">
            <img class="profile_img" src="https://placeimg.com/640/480/arch/any" alt="">
            <h3>${student.stdName}</h3>
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>General Information</h3>
          </div>
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <tr>
                <th width="30%">Student Id</th>
                <td width="2%">:</td>
                <td>${student.stdId}</td>
              </tr>
              <tr>
                <th width="30%">Student Name</th>
                <td width="2%">:</td>
                <td>${student.stdName}</td>
              </tr>
              <tr>
                <th width="30%">Email</th>
                <td width="2%">:</td>
                <td>${student.email}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<h1>Your past quiz results</h1>
 <table class="table-fill">
<caption>ScoreCard</caption>
<thead>
<tr>
<th scope="col" class="text-left">Student Id</th>
<th scope="col" class="text-left">Subject Id</th>
<th scope="col" class="text-left">Score</th>
</tr>
</thead>
<tbody class="table-hover">
	
<c:forEach var="result" items="${results}">
<tr>
<td class="text-left">${result.stdId}</td>
<td class="text-left">${result.subId}</td>
<td class="text-left">${result.score}</td>
</tr>
</c:forEach>
</tbody>
</table> 
</body>
</html>