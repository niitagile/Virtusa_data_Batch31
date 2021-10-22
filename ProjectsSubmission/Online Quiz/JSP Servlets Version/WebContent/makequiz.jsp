<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
					out.print("<font size='2' color='red' m>");
					out.print(request.getAttribute("Error"));
					out.print("</font>");
					}
					if(session.getAttribute("category")!=null){
					String category=(String)session.getAttribute("category");
					if(category.equals("Student")){
					request.setAttribute("category","You are not a Teacher,You can't make Quiz");
					%>
					<jsp:forward page="home.jsp"></jsp:forward>
					<% 
					}
					
					}
					
					
					%>
            	<div class="calendar_box">
            	
                	<div class="calendar_box_content">
               
                		<h1>Welcome to my Site</h1>
                		                   		<marquee direction="left" style="color: navy;" onmouseover="stop()" onmouseout="start()"><B>Assess Yourself by taking quizs on various subjects</B></marquee>
                		   
                		<form method="post" action="createquiz.jsp" >
					<table>
					<tr><td>Subject:</td><td><input type="text" name="subject" /></td></tr>
				
                    <tr><td></td><td><input type="submit" value="Create"></td>
                     	</table>
                     </form>


                    
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
						we have latest projects uoloaded on my site</p>
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

<jsp:include page="footer.html"></jsp:include>