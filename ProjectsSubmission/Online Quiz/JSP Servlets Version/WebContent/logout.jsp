<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>




<head>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
</head>
----//body tags
<%

session.invalidate();
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0);




%>
<jsp:forward page="index.jsp"></jsp:forward>