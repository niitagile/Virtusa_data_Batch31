<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Quiz<h1>
	<form>
		<ol type="1">
			<c:forEach var="question" items="${questions}">
				<% 
					var answers=[];	
					answers.add(${question.option1});
					answers.add(${question.option2});
					answers.add(${question.option3});
					answers.add(${question.option4});
				%>
				<li>
					${question.ques_description}
					<input type="hidden" name="questionId" value="${question.ques_id}"/><br/>
					<c:forEach var="answer" items="${answers}">
					<input type="radio" name="question_${question.ques_id}" value="${answer}"/>${answer}<br/>
					</c:forEach>
				</li>
			</c:forEach>
		</ol>
	</form>
</body>
</html>