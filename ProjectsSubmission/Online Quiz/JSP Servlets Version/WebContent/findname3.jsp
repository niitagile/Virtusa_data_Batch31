<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>


<%
String n=request.getParameter("val");
session.setAttribute("quizname",n);
if(n.length()>0){
out.print("<font style='color:navy'><B>Quiz"+n+" containns 10 Question<br>Each question is of 1 point</B><br></font>");
out.print("<input type='submit' value='Start Quiz' />");

}
//end of if
%>