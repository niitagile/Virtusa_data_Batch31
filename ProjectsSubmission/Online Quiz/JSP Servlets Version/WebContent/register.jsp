<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script>
var request;
function sendInfo()
{
var v=document.myform.username.value;
var url="findname.jsp?val="+v;
if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}
try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e){alert("Unable to connect to server");}
}

function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('location').innerHTML=val;
}
}
</script>
</head><body>
  
<div id="main_container">
	<div class="main_content">
    	<div id="header">
      
        </div>
        <div class="top_center_box"></div>
        <div class="center_box">

    
            <div id="menu_tab">                                     
                    <ul class="menu">                                                                              
                         <li><a href="home.jsp" class="nav">Home</a></li>
                         <li class="divider"></li>
                         <li><a href="takequiz.jsp" class="nav">Take Quiz</a></li>
                         <li class="divider"></li>
                         <li><a href="ask.jsp" class="nav">Ask Ques.</a></li>
                         <li class="divider"></li>
                         
						 <li><a href="makequiz.jsp" class="nav">Make quiz</a></li> 
						 <li class="divider"></li>
<li><a href="contact.html" class="nav">Contact</a></li>
<li class="divider"></li>
 <li><a href="register.jsp" class="nav">Registration</a></li>
                         	   
                         	 
                    </ul>
            </div> 
          
            
            <div class="middle_box">
            	<div class="middle_box_text_content">
                	<div class="middle_box_title"></div>
                    <p class="middle_text">
</p>
                </div>
            
            
            </div>
            
            
            <div class="left_content">
            
            	<div class="calendar_box">
            	
                	<div class="calendar_box_content1">
                	
                		<h1>Welcome to my Site</h1>
                		   <p><div id="location"></div>
                		<form method="post" action="registerprocess.jsp" name="myform" >
					<table>
					
					<tr><td style="color:navy;"><B>Registration</B></td></tr>
					<tr><td>User Name:</td><td><input type="text" name="username" onkeyup="sendInfo()"/></td></tr>
							
					<tr><td>Password:</td><td><input type="password" name="userpass"/></td></tr>
					<tr><td>Category:</td><td><select name="category">
					<option>Select a Category</option>
					<option>Teacher</option>
					<option>Student</option>
					</select>
					</td></tr>
					<tr><td>Email:</td><td><input type="text" name="email"/></td></tr>	
							
					<tr><td>      </td>
					<td><input type="submit" value="Register"></td></tr>
					</table>
                     </form>


                        </p>
                	</div>
                </div>
                
             <div class="news_left">
                <h1>Latest News</h1>
                	<div class="news_box">
                    	<div class="calendar">23</div>
                        <div class="news_content">
						<p><span></span>
						now learn  how to develop android application free of cost</p><br>
                        <a href="www.javatpoint.com" class="read_more">read more</a>                        
                        </div>
                    </div>
                    <br>
                	<div class="news_box">
                    	<div class="calendar">23</div>
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