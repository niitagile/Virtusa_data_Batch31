<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<html>


<jsp:include page="header.jsp"></jsp:include>

<%
String islogin=(String)session.getAttribute("islogin");
if(islogin==null){
request.setAttribute("notlogin_msg","Sorry,Please do Login first");

%>
<jsp:forward page="index.jsp"></jsp:forward>
<%
}
%>



<div class="left_content">
             <% 
					if(request.getAttribute("notlogin_msg")!=null){
					out.print("<font size='2' color='red' m>");
					out.print(request.getAttribute("notlogin_msg"));
					out.print("</font>");
					}
					%>
				<% 
					if(request.getAttribute("Error")!=null){
					out.print("<font size='2' color='red' >");
					out.print(request.getAttribute("Error"));
					out.print("</font>");
					}
					%>
					<%
					if(request.getAttribute("finished")!=null){
					   out.print("<font size='2' color='navy'>");
                   out.print("<B>");
					out.print(request.getAttribute("finished"));
					  out.print("<B>");
   						out.print("</font>");
					}
				
					%>
            	<div class="calendar_box2">
            	
                	<div class="calendar_box_content">
               
                		<h1>Welcome to my Site</h1>
                		              		<marquee direction="left" style="color: navy;" onmouseover="stop()" onmouseout="start()"><B>Assess Yourself by taking quizs on various subjects</B></marquee>
				<%
				String name=(String)session.getAttribute("quizname");
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
					PreparedStatement ps= con.prepareStatement("select * from quizques where quizname='"+name+"'");
					ResultSet rs=ps.executeQuery();
					out.print("<table>");
					while(rs.next()){
					out.print("<tr><td>Question:</td><td>"+rs.getString(1)+"</td></tr>");
					out.print("<tr><td>Answer:</td><td>"+rs.getString(6)+"</td></tr>");
					out.print("<tr><td>Description:</td><td>"+rs.getString(9)+"</td></tr>");
					
					}
					out.print("</table>");
					
				}catch(Exception e){e.printStackTrace();}
				 %>					


                     
                	</div>
                </div>
                
                <div class="news_left">
                <h1>Latest News</h1>
                	<div class="news_box">
                    	<div class="calendar">1</div>
                        <div class="news_content">
						<p><span></span>
						now learn  how to develop android application free of cost</p><br>
                        <a href="www.javatpoint.com" class="read_more">read more</a>                        
                        </div>
                    </div>
                    <br>
                	<div class="news_box">
                    	<div class="calendar">2</div>
                        <div class="news_content">
						<p><span></span>
						we have latest projects uploaded on my site</p>
                        <a href="www.cstpoint.com" class="read_more">read more</a>                        
                        </div>
                    </div>                    
                    
                    
                    
                
                </div>
            
            
            </div>
            <!--end of left content-->


            <div class="right_content">
            
         
                <h1>Latest Projects</h1>
                
                <div class="project_box">
                    <a href="#"><img src="images/p1.gif" alt="" title="" class="left_img" border="0"></a>
                    <p>
                    <span> Description:</span><br>
                    Payment billing System
                    Online Quiz<br>
                    Matrimonial
                    
                    </p>
                </div>

                <div class="project_box">
                    <a href="#"><img src="images/p2.gif" alt="" title="" class="left_img" border="0"></a>
                    <p>
                    <span> Description:</span><br>
                    
                  		Gmail Account<br>
                  		Latest Quiz
                    </p>
                </div>
                <br>
                <div>
                
              <h1 ><br>Our main Services</h1>          
 </div>                   
                  <div class="services">
                    <ul>                                        
                       <li><a href="www.javatpoint.com">Java tutorial</a></li>
                       <li><a href="www.cstpoint.com">CSE tutorial</a></li>
                    </ul>
                </div>
                
                 <div class="services">
                    <ul>                                        
                       <li><a href="www.javatpoint.com">Java tutorial</a></li>
                       <li><a href="www.cstpoint.com">CSE tutorial</a></li>
                    </ul>
                </div>  
                   
            
            </div><!--end of right content-->


	<div id="footer">
    
    </div>


		</div> <!--end of center box-->
	</div> <!--end of main content-->
</div> <!--end of main container-->
</body></html>

