<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript">
        function preventBack() {
            window.history.forward(); 
        }
          
        setTimeout("preventBack()", 0);
          
        window.onunload = function () { null };
    </script>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
 <style><%@include file="/resources/css/questions.css"%></style>
<body>
<script type="text/javascript">
var total_seconds=30*10;
var c_minutes= parseInt(total_seconds/60);
var c_seconds = parseInt(total_seconds%60);
function CheckTime(){
document.getElementById("quiz-time-left").innerHTML='Time Left: ' + c_minutes + ' minutes ' + c_seconds + ' seconds' ;
if(total_seconds <=0){
setTimeout('document.quiz.submit()',1);
}else
{
total_seconds = total_seconds -1;
c_minutes = parseInt(total_seconds/60);
c_seconds = parseInt(total_seconds%60);
setTimeout("CheckTime()",1000);
}}
setTimeout("CheckTime()",1000);
</script>
<div style="text-align:center;font-weight: bold" id="quiz-time-left"></div>
<form name="quiz" action="/getResult">
<input type="hidden" name="subjectId" value="${sub_id}"/>
		<input type="hidden" name="studentId" value="${std_id}"/>
		<div class="quize">
		<ul >
			<c:forEach var="question" items="${questions}">
			 <h2 class="qestion">${question.quesDescription}</h2>
				<li>
					<input type="hidden" name="questionId" value="${question.quesId}"/><br/>
					<input type="radio" name="question_${question.quesId}" value="${question.option1}"/>${question.option1}<br/>
					<input type="radio" name="question_${question.quesId}" value="${question.option2}"/>${question.option2}<br/>
					<input type="radio" name="question_${question.quesId}" value="${question.option3}"/>${question.option3}<br/>
					<input type="radio" name="question_${question.quesId}" value="${question.option4}"/>${question.option4}<br/>
				</li>
			</c:forEach>
			
		</ul>
		<div class="footer-button">
      <input id="submit" type="submit" class="submitBtn">
    </div>
    </div>
		</form>
</body>
</html>