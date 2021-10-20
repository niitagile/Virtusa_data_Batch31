<!DOCTYPE html>
<html lang="en">
<head>
<title>User</title>
<!-- icons -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
<title>USER PAGE</title>
    
<style>




a.disabled {
  pointer-events: none;
  cursor: default;
}

body {
  
  font-family: Arial, Helvetica, sans-serif;
 
 padding:0px;
   margin:0px;
 
 
background-image: url("https://wallpaperaccess.com/full/2317008.jpg");
background-repeat:  no-repeat;
margin-bottom: 4000px;
height: 1000px;

}
.wrapper {
  position: relative;
  overflow: hidden;
  height: 40px;
  width: 1500px;
  
}

.wrapper p {
  position: absolute;
  margin: 0;
  line-height: 35px;
  white-space: nowrap;
  animation: marquee 8s linear infinite;
}

@keyframes marquee {
  0% { transform: translateX(100%); }
  100% { transform: translateX(-100%); }
}

.topnav {
  overflow: hidden;
  background-color: #333;
}
textarea { font-size: 18px; }
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: gray;
  color: white;
}
summary {
  font-size: 1.25rem;
  font-weight: 600;
  background-color: #fff;
  color: #333;
  padding: 1rem;
  margin-bottom: 1rem;
  outline: none;
  border-radius: 0.25rem;
  text-align: left;
  cursor: pointer;
  position: relative;
}
details > summary::after {
  position: absolute;
  content: "+";
  right: 20px;
}
details[open] > summary::after {
  position: absolute;
  content: "-";
  right: 20px;
}
details > summary::-webkit-details-marker {
  display: none;
}
details[open] summary ~ * {
  animation: sweep .5s ease-in-out;
}




.container {
  border: 2px solid #dedede;
 
  border-color: black;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
  
}
.dcontainer {
  border: 2px solid #dedede;
  background: linear-gradient(to bottom, #000000 0%, #808080 100%);
  border-color: black;
 display: inline-block;
  border-radius: 5px;
  padding: 10px;
  width: 855px;
  margin: 10px 0;
  
}


.container::after {
  content: "";
  clear: both;
  display: table;
}
.hey
{

background-repeat:no-repeat;
border: 2px solid #dedede;
 
  border-color: black;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
border:1px solid black;"
background-position: center bottom;
}






.w3-row-padding img {margin-bottom: 12px}

.w3-sidebar {width: 120px;background: #222;}

#main {margin-left: 120px}














.TA{
padding-left: 3%; 
}



<!-- form -->


.rf{
 align-items:center;
  display: flex;
  justify-content: center;
  height: 100vh;
}

.form {
 background-color: #000000;
background-image: linear-gradient(315deg, #000000 0%, #414141 74%);
 
  box-sizing: border-box;
  
  height: 670px;
  padding: 20px;
  width: 600px;
  
}

.title {
  color: #eee;
  font-family: sans-serif;
  font-size: 36px;
  font-weight: 600;
  margin-top: 10px;
}




.tbl-header1 {
	background-color: rgba(255, 255, 255, 0.3);
}

.tbl-content1 {
	height: 170px;
	overflow-x: auto;
	margin-top: 0px;
	border: 1px solid rgba(255, 255, 255, 0.3);
}

.th1 {
	padding: 20px 15px;
	text-align: left;
	font-weight: 500;
	font-size: 12px;
	color: #fff;
	text-transform: uppercase;
}



/* demo styles */
.section1 {
	margin: 50px;
}


.input-container {
  height: 50px;
  position: relative;
  width: 100%;
}

.ic1 {
  margin-top: 40px;
}

.ic2 {
  margin-top: 30px;
}

.input {
 
  border-radius: 12px;
  border: 0;
  box-sizing: border-box;
  color: black;
  font-size: 18px;
  height: 100%;
  outline: 0;
  padding: 4px 20px 0;
  width: 100%;
}

.cut {
 background-color: #2d3436;
background-image: linear-gradient(315deg, #2d3436 0%, #000000 74%);
  border-radius: 10px;
  height: 20px;
  left: 20px;
  position: absolute;
  top: -20px;
  transform: translateY(0);
  transition: transform 200ms;
  width: 76px;
}

.cut-short {
  width: 50px;
}

.input:focus ~ .cut,
.input:not(:placeholder-shown) ~ .cut {
  transform: translateY(8px);
}

.placeholder {
  color: #65657b;
  font-family: sans-serif;
  left: 20px;
  line-height: 14px;
  pointer-events: none;
  position: absolute;
  transform-origin: 0 50%;
  transition: transform 200ms, color 200ms;
  top: 20px;
}

.input:focus ~ .placeholder,
.input:not(:placeholder-shown) ~ .placeholder {
  transform: translateY(-30px) translateX(10px) scale(0.75);
}

.input:not(:placeholder-shown) ~ .placeholder {
  color: #808097;
}

.input:focus ~ .placeholder {
  color: gray;
}

.submit {
background-color: #2d3436;
background-image: linear-gradient(315deg, #2d3436 0%, #000000 74%);
  border-radius: 12px;
  border: 0;
  box-sizing: border-box;
  color: #eee;
  cursor: pointer;
  font-size: 18px;
  height: 50px;
  margin-top: 38px;
  // outline: 0;
  text-align: center;
  width: 100%;
}

.submit:active {
  background-color: black;
}
  
  
  #table1,#table2
  {
  display: none;
  }
      
.td2 {
	padding: 15px;
	text-align: left;
	vertical-align: middle;
	font-weight: 300;
	font-size: 12px;
	color: #fff;
	border-bottom: solid 1px rgba(255, 255, 255, 0.1);
}

.table1 {
	width: 90%;
	table-layout: fixed;
	background: black;
	padding-left: 60px;
}






.column {
  float: left;
  width: 25%;
  padding: 0 10px;
  padding-left: 300px;
}


.row {margin: 0 -5px;}


.row:after {
  content: "";
  display: table;
  clear: both;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
 
  text-align: center;
  background-color: black;
  height: 250px;
  width: 450px;
 
}
.card1 {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
 
  text-align: center;
  background-color: black;
  height: 250px;
  width: 450px;
 
  padding-right: 80px;
}



.faq
{
 border: 2px solid black;
  border-radius: 10px;
 //background-color: white;
background-image: linear-gradient(to right top, #fbf0f7, #f7eef6, #f3ebf4, #eee9f3, #eae7f1, #e6e6f0, #e2e5ee, #dfe4ec, #dce4e9, #dbe3e6, #dce2e2, #dde1e0);
padding-left: 200px;
padding-right: 20px;

}






</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script>src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="icon" href="data,">

</head>
<body>
<input id="tokenField" type="hidden" value="${sessionScope.token }" />
<div class="topnav"   >

  <a class="active" href="#home">FAQ</a>
  
  <a href="#v">view our vehicles</a>
  
   <a href="#s">Services</a>
   
  <a href="#q">Contact</a>
 
  <a href="#q">Queries & Feedback</a>
    
  <a href="#table2" onclick="show(2)">Your Bookings</a>
  
  <a href="#r" onclick="show(1)">view Response</a>
  
  <form action="/logout" method="POST">
 <input style="margin-left: 500px; margin-top: 10px; border: none; background: none; color: white; font-size: 17px" type="submit" value="Logout">  
 </form>
 </div>

<div style="padding-left:16px">
<div class="wrapper">
    <p style="font-size: 28px;">We do transportation as reliable as running water </p>
</div>

  <h3 style="line-height: 40px;">Are you planning to move to a new place? Are you thinking to hire a reliable and professional online transportation services provider? Do you want to book a truck online but have no or less idea how to book it? If so, We can be the perfect solution for you. </h3>
 </div>
 <div  style="padding-top: 720px" > 
 
  <form id="table2">
<section >
<br><br><br>
		<h1 style="padding-left: 550px;">Bookings Details    </h1>
		<br>
		<br>
			 
 <div class="row">
  <div class="column">
    <div class="card" >
      <h3 style="color: white;">TRUCK MODEL</h3>
     <div class="td2" id="bookingst"  style="padding-left: 140px;" ></div>
    </div>
  </div>

  <div class="column">
    <div class="card" >
      <h3 style="color: white;">BOOKED DATE</h3>
      <div class="td2" id="bookingsd"  style="padding-left: 160px;  " ></div> 
    </div>
	</div>	
	</div>	
	</section>
  
</form>

  <div id="table1">
  <div id="r"  style="padding-top: 30px;">
  <br><br>
  <h1 style="padding-left: 600px;">Query Details  </h1>
		<br>
		
  
 <div class="row">
  

  <div class="column">
    <div class="card1" >
      <h3 style="color: white;">Query</h3>
      <div class="td2" id="queriesq"  style="padding-left: 140px;  " ></div> 
    </div>
  </div>
  
  <div   class="column">
    <div class="card1" >
      <h3 style="color: white;">Response</h3>
       <div class="td2" id="queriesqr" style="padding-left: 120px; align-content: center;" ></div> 
    </div>
  </div>
  
 
</div>
  
  
  
  
  
  </div>
  </div>
 

<div id= v>




<table>
<caption style="display:none"></caption>
 	<tr style="display:none"><th scope="col"></th><th scope="col"></th></tr>
  	
   <tr>
    <td CLASS="TA"><img src="https://trucknbus.hyundai.com/global/en/images/product/cars/hd38-78/best-choice-hd-series-m.jpg" alt="Avatar" style="width:100%;margin-top: 40px;">
  </td>
   <td> </td>
 
   
    <td CLASS="TA"><img src=" https://cms.eichertrucksandbuses.com/uploads/truck/exterior/e491425148a32e048b5e874dc73e8ec8.jpg" alt="Avatar" style="width:100%; margin-top: 60px;">
  </td>
    <td> </td>
    
  </tr>
  
  <tr>
    
   <td CLASS="TA">
   <h4 ><strong id="truckname1">HYUNDAI HD35-78 </strong>   <button style="font-size:15px" onclick="checkAvail('HYUNDAI HD35-78','date1','a1')"> <em class="fa fa-refresh"></em></button>   </h4> 
     <input  type="date" placeholder="Date" id="date1">
    <p>load : 2 Ton</p>
       <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a1"></p>
  </td>
  
  
    <td> </td>
    
    <td CLASS="TA">
    <h4 > <strong id="truckname2"> EICHER PRO TIPPER <button style="font-size:15px"  onclick="checkAvail('EICHER PRO TIPPER','date2','a2')"> <em class="fa fa-refresh"></em></button></strong>    </h4> 
    <input  type="date" placeholder="Date" id="date2">
    <p>load : 1.5 Ton</p>
       <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a2"></p>
  </td>
    <td> </td>
    
  </tr>
   <tr>
    <td CLASS="TA"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX0YF-KDNkgvSVt8la_O2sMmRngXY2YQGfgw&usqp=CAU" alt="Avatar" style="width:100%">
  </td>
   <td> </td>
    
    <td CLASS="TA"><img src="https://p.kindpng.com/picc/s/161-1610306_freezer-truck-hd-png-download.png" alt="Avatar" style="width:100%">
  </td>
    <td> </td>
    
  </tr>
  
  <tr>
    
   <td CLASS="TA">
   <h4 ><strong id="truckname3"> TATA ACE  <button style="font-size:15px" onclick="checkAvail('TATA ACE','date3','a3')"> <em class="fa fa-refresh"></em></button></strong>   </h4> 
   <input  type="date" placeholder="Date" id="date3">
    <p >load : 1.5 Ton</p>
      <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a3"></p>
  </td>
  
  
    <td> </td>
    
    <td CLASS="TA">
    <h4 ><strong id="truckname4">FREEZER TRUCK   <button style="font-size:15px"  onclick="checkAvail('FREEZER TRUCK','date4','a4')"> <em class="fa fa-refresh"></em></button></strong>    </h4> 
    <input  type="date" placeholder="Date" id="date4">
    <p >load : 1.5 Ton</p>
      <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a4"></p>
  </td>
    <td> </td>
    
  </tr>
   <tr>
    <td CLASS="TA"><img src="https://m.media-amazon.com/images/I/41QsB3g68WL._SR500,500_.jpg" alt="Avatar" style="width:100%">
  </td>
   <td> </td>
    
    <td CLASS="TA"><img src="https://www.tatamotors.co.id/wp-content/uploads/2019/11/ULTRA-1012-small.png" alt="Avatar" style="width:100%">
  </td>
    <td> </td>
    
  </tr>
  
  <tr>
    
   <td CLASS="TA">
   <h4 ><strong id="truckname5">DIECAST    <button style="font-size:15px "  onclick="checkAvail('DIECAST','date5','a5')"> <em class="fa fa-refresh"></em></button></strong>    </h4> 
    <input  type="date" placeholder="Date" id="date5">
    <p>load : 1.7Ton</p>
     <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a5"></p> 
  </td>
  
  
    <td> </td>
    
    <td CLASS="TA">
    <h4 ><strong id="truckname6">TATA TAURUS   <button style="font-size:15px"   onclick="checkAvail('TATA TAURUS','date6','a6')"> <em class="fa fa-refresh"></em></button> </strong>   </h4> 
      <input  type="date" placeholder="Date" id="date6">
    <p>load : 1.5 Ton</p>
       <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a6"></p>
  </td>
    <td> </td>
    
  </tr>
  
   <tr>
    <td CLASS="TA"><img src="https://www.motorindiaonline.in/wp-content/uploads/2012/02/Screen-Shot-2012-02-10-at-4.50.26-PM.png" alt="Avatar" style="width:100%">
  </td>
   <td> </td>
    
    <td CLASS="TA"><img src="  https://res.cloudinary.com/danvu3kff/image/fetch/c_scale,q_40,w_400/https://www.trucksbuses.com/uploads/4251_eicher-pro-3019-truck.jpg" alt="Avatar" style="width:100%">
  </td>
    <td> </td>
    
  </tr>
  
  <tr>
    
   <td CLASS="TA">
   <h4 ><strong id="truckname7"> ASHOK LEYLAND TONNE TRUCK <button style="font-size:15px"   onclick="checkAvail('ASHOK LEYLAND TONNE TRUCK','date7','a7')"> <em class="fa fa-refresh"></em></button> </strong>    </h4> 
    <input  type="date" placeholder="Date" id="date7">
    <p>load : 1.5 Ton</p>
       <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a7"></p>
  </td>
  
  
    <td> </td>
    
    <td CLASS="TA">
    <h4 ><strong id="truckname8">   CONTAINER 32 FT SXL <button style="font-size:15px"  onclick="checkAvail('CONTAINER 32 FT SXL','date8','a8')"> <em class="fa fa-refresh"></em></button> </strong>    </h4> 
     <input  type="date" placeholder="Date" id="date8">
    
    <p>load : 2 Ton</p>
       <p style=" display:inline; ">Availability : </p>
    <p style=" display : inline-block; " id="a8"></p>
  </td>
    <td> </td>
    
  </tr>

  <div>
  </div>
    
  

</table>






















<h1 align="CENTER"></h1>

<!-- register form -->

<!-- <img width=1500px; height style=" padding-left: 400px; padding-bottom: 10px;" src="https://images.unsplash.com/photo-1620714223084-8fcacc6dfd8d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8b25saW5lJTIwYm9va2luZ3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80">
 
<img width=1400px; height= 650px; style=" padding-top: -3000px; padding-left: 600px; padding-right: 40px" src="https://images.unsplash.com/photo-1620714223084-8fcacc6dfd8d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8b25saW5lJTIwYm9va2luZ3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80">
-->

<div class="rf" id="s" >
<div> <!-- padding-left: 600px; -->
<img width=1600px; height= 690px; style="  padding-top: 18px;  padding-right: 40px" src="https://images.unsplash.com/photo-1620714223084-8fcacc6dfd8d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8b25saW5lJTIwYm9va2luZ3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80" alt="img">
    
    <div style="margin-top: -671.5px;  padding-bottom: 1px; "  class="form" >
    
      <div class="title" >BOOK YOUR TRUCK </div>
      
      
      <div class="input-container ic2">
        <input  style="width: 420px"        id="kilo" class="input" type="number" placeholder=" " />
        <div class="cut"></div>
        <label for="lastname" class="placeholder">Number of Kilometers</label>
        <button style="width:133px; height:53px;border-radius: 12px;" onclick="cal()">show amount</button>
      </div>
      
      
      
      <div class="input-container ic2">
        <input  style="width: 420px"     readonly   id="amt" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        <label for="lastname" class="placeholder" id="demo1" >Amount</label>
      
      </div>
      
      
      
      
      
      
      <div class="input-container ic2">
        <input id="bdate" class="input" type="date" placeholder=" " />
        <div class="cut cut-short"></div>
        <label for="date" class="placeholder">Date</label>
      </div>
      
        <div class="input-container ic1">
       
        <label for="bmodel" style="color: gray" class="cut">Model</label>
        
        <select id="bmodel" class="input" name="bmodel">
    <option value="HYUNDAI HD35-78">HYUNDAI HD35-78 </option>
    <option value="CONTAINER 32 FT SXL">CONTAINER 32 FT SXL</option>
  
    <option value="TATA ACE">TATA ACE</option>
    <option value="FREEZER TRUCK">FREEZER TRUCK</option>
    <option value="DIECAST">DIECAST</option>
    <option value="TATA TAURUS">TATA TAURUS</option>
    <option value="ASHOK LEYLAND TONNE TRUCK">ASHOK LEYLAND TONNE TRUCK</option>
    <option value="EICHER PRO TIPPER ">EICHER PRO TIPPER </option>
    
  </select>
      </div>
      
      
     <div > <button  type="text" id="booktruckbutton" onClick="booktruckFunction()" class="submit">submit</button></div>
    </div>
    
</div>

</div>



</div>
</div>

<!-- left:890px; WIDTH:500px; HEIGHT:486px    background: linear-gradient(to bottom, #000000 0%, #808080 100%);-->

<div  style="padding-top: 0px" > 

<div style=" background-color: #2d3436;
background-image: linear-gradient(315deg, #2d3436 0%, #000000 74%);">
<div class = "hey22">





<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<div class="f1">

                <h1 style="color: white;" align="center">FAQ</h1>
                <h1 class="faq"  style="  padding:15px; padding-left: 50px; font-size: 30px;" >1. How do I request a booking?</h1>
                <h5 style="color: white; padding-left: 55px; font-size: 25px;">You can request a vehicle my filling the above given form or contacting our support team.</h5>
               
                <h1 class="faq"  style=" padding:15px; padding-left: 50px;font-size: 30px;">2.Do you also provide loading/unloading & packing services?</h1>
               <h5 style="color: white;padding-left: 55px;font-size: 25px;">Yes, we also provide loading/unloading & packing services and charger them separately.</h5>
               
                <h1  class="faq" style=" padding:15px; padding-left: 50px;font-size: 30px;">3.What are the GST charges on Fare amount ?</h1>
               <h5 style="color: white;padding-left: 55px;font-size: 25px;">The GST charge depends on your total fare amount. To know more about this please contact our support team.</h5>
           
            <h1  class="faq" style=" padding:15px; padding-left: 50px;font-size: 30px;">4.How many days or hours in advance do I need to make my booking?</h1>
               <h5 style="color: white;padding-left: 55px;font-size: 25px;">You should book the truck at least 8 hours before the trip. </h5>
            
               <h1  class="faq" style=" padding:15px; padding-left: 50px;font-size: 30px;">5.Can we do cash on delivery?</h1>
               <h5 style="color: white;padding-left: 55px;font-size: 25px;">Yes, we do accept cash on delivery. </h5>
              
            </div>

</div>  
<div id =q>
   
  <footer style="background-color: #2d3436;
background-image: linear-gradient(315deg, #2d3436 0%, #000000 74%);">
  
  
  <div class="w3-padding-64 w3-content w3-text-grey" id="contact">
    <h2 class="w3-text-light-grey">Contact Us</h2>
    <hr style="width:200px" class="w3-opacity">

    <div class="w3-section">
      <p><em class="fa fa-map-marker fa-fw w3-text-white w3-xxlarge w3-margin-right"></em> INDIA</p>
      <p><em class="fa fa-phone fa-fw w3-text-white w3-xxlarge w3-margin-right"></em> Phone: 99999 99999</p>
      <p><em class="fa fa-envelope fa-fw w3-text-white w3-xxlarge w3-margin-right"> </em> Email: trasport@mail.com</p>
    </div><br>
    
     <form onSubmit="return query()" method="POST">
     <textarea style="margin-right: 100px;" rows="8" cols="100" id="qq"></textarea>   <br>
       
     <button style="border-radius: 8px"   >Send</button>    
     </form>
     
    
     
        
    
           
             
 
  </div>
  
  </footer>
  </div>
  </div>
</div>
 
 
        <SCRIPT>
        
        $("div").children("h5").hide();
        $(document).ready(function () {

            $("h1").click(function () {
                $("div").children("h5").hide();
                $(this).next("h5").show();
            });
        });
        
        var det3;
        function show(nr) {
            document.getElementById("table1").style.display="none";
            document.getElementById("table2").style.display="none";
            
            document.getElementById("table"+nr).style.display="block";
            if(nr==1)
            	showQueries();
            if(nr==2)
            	showBookings();
        
        }
        
        function showBookings(){
        	$.ajax({
        		url : "/viewMyBookings",
        		type : 'POST',
        		data : "token=" + $('#tokenField').val(),
        		dataType : 'json',
        		headers : {
        			"Authorization" : "Bearer "
        					+ $('#tokenField').val()
        		},
        		success : function(data) {
        			det3=data;
        			tableCreate2();
        			

        		},
        		error : function() {
        			//alert('Error occured');
        		},
        		async : false
        	})

        }
        //query table
        function tableCreate() {
        	document.getElementById("queries").innerHTML="";
        	var body = document.getElementById("queries"), tbl = document.createElement('table');
        	body.style.background = "black";
        	body.style.color = "white";
        	//body.style.margin = "30px";
          // body.style.borderSpacing  = "100px";
           // document.getElementById("body").style.borderSpacing = "15px";
        	console.log("Inside table functin*****");
        	console.log(det["1"]);
        	var keyArray=["serialNumber"];
        	var totalLength=Object.keys(det).length;
        	var singleLength=1;
        	console.log("***");
        	
        	for (var i = 0; i < totalLength; i++) {
        		var tr = tbl.insertRow();
        		for (var j = 0; j < singleLength; j++) {
        			
        				var td = tr.insertCell();
        				
        				td.appendChild(document.createTextNode(det[i.toString()][keyArray[j]]));
        				td.style.borderSpacing = "1000px";
        			
        		}
        		
        	}
        	body.appendChild(tbl);
        	tableCreateq();
        }
        function tableCreateq() {
        	document.getElementById("queriesq").innerHTML="";
        	var body = document.getElementById("queriesq"), tbl = document.createElement('table');
        	body.style.background = "black";
        	body.style.color = "white";
        	//body.style.width = "200px";
        	//body.style.margin = "150px";
          // body.style.borderSpacing  = "100px";
           // document.getElementById("body").style.borderSpacing = "15px";
        	console.log("Inside table functin*****");
        	console.log(det["1"]);
        	var keyArray=["query"];
        	var totalLength=Object.keys(det).length;
        	var singleLength=1;
        	console.log("***");
        	
        	for (var i = 0; i < totalLength; i++) {
        		var tr = tbl.insertRow();
        		for (var j = 0; j < singleLength; j++) {
        			
        				var td = tr.insertCell();
        				
        				td.appendChild(document.createTextNode(det[i.toString()][keyArray[j]]));
        				td.style.borderSpacing = "1000px";
        			
        		}
        		
        	}
        	body.appendChild(tbl);
        	tableCreateqr();
        }
        function tableCreateqr() {
        	document.getElementById("queriesqr").innerHTML="";
        	var body = document.getElementById("queriesqr"), tbl = document.createElement('table');
        	body.style.background = "black";
        	body.style.color = "white";
        	//body.style.width = "100px";
        	//body.style.margin = "60px";
          // body.style.borderSpacing  = "100px";
           // document.getElementById("body").style.borderSpacing = "15px";
        	console.log("Inside table functin*****");
        	console.log(det["0"]);
        	var keyArray=["response"];
        	var totalLength=Object.keys(det).length;
        	var singleLength=1;
        	console.log("***");
        	
        	for (var i = 0; i < totalLength; i++) {
        		var tr = tbl.insertRow();
        		for (var j = 0; j < singleLength; j++) {
        			
        				var td = tr.insertCell();
        				
        				td.appendChild(document.createTextNode(det[i.toString()][keyArray[j]]));
        				td.style.borderSpacing = "1000px";
        			
        		}
        		
        	}
        	body.appendChild(tbl);
        	
        }   
       	function showQueries(){
       		
       		
       		
       		$.ajax({
        		url : "/viewResponses",
        		type : 'POST',
        		data : "token=" + $('#tokenField').val(),
        		dataType : 'json',
        		headers : {
        			"Authorization" : "Bearer "
        					+ $('#tokenField').val()
        		},
        		success : function(data) {
        			det=data;
        			tableCreateq();
        			

        		},
        		error : function() {
        			//alert('Error occured');
        		},
        		async : false
        	})

       		
       	}
       	
       	function tableCreate2() {
       	//	alert("booking trucks");
       		document.getElementById("bookingst").innerHTML="";
       		var body = document.getElementById("bookingst"), tbl = document.createElement('table');

       		console.log("Inside table functin*****");
       		console.log(det3["0"]);
       		var keyArray=["truckModel"];
       		var totalLength=Object.keys(det3).length;
       		var singleLength=1;
       		console.log("***");
       		for (var i = 0; i < totalLength; i++) {
       			var tr = tbl.insertRow();
       			for (var j = 0; j < singleLength; j++) {
       				
       					var td = tr.insertCell();
       					td.appendChild(document.createTextNode(det3[i.toString()][keyArray[j]]));
       				
       			}
       		
       		}
       		body.appendChild(tbl);
       		tableCreate21();
       	}
       	
       	function tableCreate21() {
       		//alert("booking trucks");
       		document.getElementById("bookingsd").innerHTML="";
       		var body = document.getElementById("bookingsd"), tbl = document.createElement('table');

       		console.log("Inside table functin*****");
       		console.log(det3["0"]);
       		var keyArray=["bookedDate"];
       		var totalLength=Object.keys(det3).length;
       		var singleLength=1;
       		console.log("***");
       		for (var i = 0; i < totalLength; i++) {
       			var tr = tbl.insertRow();
       			for (var j = 0; j < singleLength; j++) {
       				
       					var td = tr.insertCell();
       					td.appendChild(document.createTextNode(det3[i.toString()][keyArray[j]]));
       				
       			}
       		
       		}
       		body.appendChild(tbl);
       	}
       	
       	
       	
        function booktruckFunction(){
        	//alert($('#bmodel').val());
        	console.log($('#name').val());
        	$.ajax({
        		url : "/bookTruck",
        		type : 'POST',
        		data : {token: $('#tokenField').val(),date:$('#bdate').val(), model:$('#bmodel').val()},
        		dataType : 'text',
        		headers : {
        			"Authorization" : "Bearer "
        					+ $('#tokenField').val()
        		},
        		success : function(data) {
        			alert(data);

        		},
        		error : function() {
        			alert('No truck available');
        		},
        		async : false
        	})
        	return false;
        }
 
        
        
function ShowAndHide() {
    var x = document.getElementById('SectionName');
    if (x.style.display == 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}

function cal()
{
	var n = document.getElementById("kilo").value;
	
	var m = 200;
	var r = m*n;
	
	//alert("amount is "+r);
	
	
	document.getElementById("demo1").innerHTML = r;
	
	
	
	
	
}



function checkAvail(t, d,id)
{
	var date1= document.getElementById(d).value;
$.ajax({
	url : "/checkTruckAvailablity",
	type : 'POST',
	data : {token: $('#tokenField').val(),date:date1, model:t},
	dataType : 'text',
	headers : {
		"Authorization" : "Bearer "
				+ $('#tokenField').val()
	},
	success : function(data) {
		document.getElementById(id).innerHTML=data;
		
	},
	error : function() {
		document.getElementById(id).innerHTML=0;
	},
	async : false
})
return false;

		}


function close() {
	//alert("in close");
	  var x = document.getElementById("table2");
	  if (x.style.display === "none") {
		 // alert("in if");
	    x.style.display = "block";
	  } else {
		 // alert("in else");
	    x.style.display = "none";
	  }
}


function query()
{
	   var  q = document.getElementById("qq").value; 
	  // alert(q);
   	$.ajax({
   		url : "/askQueries",
   		type : 'POST',
   		data : {token: $('#tokenField').val(),query:q},
   		dataType : 'text',
   		headers : {
   			"Authorization" : "Bearer "
   					+ $('#tokenField').val()
   		},
   		success : function(data) {
   			alert(data);

   		},
   		error : function() {
   			//alert('Error occured');
   		},
   		async : false
   	})
   	return false;

}


</SCRIPT>
</body>
</html>
