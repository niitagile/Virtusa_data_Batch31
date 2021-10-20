<!-- changed -->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Manager Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
background-image: url("https://www.wallpaperup.com/uploads/wallpapers/2015/05/10/683550/3a852250979b74b325058a15132f5008.jpg");
background-repeat: no-repeat;
background-size: 1600px 1000px;


}

.topnav {
  overflow: hidden;
  background-color: #333;
}

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

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: grey;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

#table1, #table2, #table3, #table4 {
    display: none;
}

table{
  width:100%;
  table-layout: fixed;
  
   background: black;
}
.tbl-header{
  background-color: rgba(255,255,255,0.3);
 }
.tbl-content{
  height:300px;
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 12px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 12px;
  color: #fff;
  border-bottom: solid 1px rgba(255,255,255,0.1);
}


/* demo styles */


section{
  margin: 50px;
}


/* follow me template */
.made-with-love {
  margin-top: 40px;
  padding: 10px;
  clear: left;
  text-align: center;
  font-size: 10px;
  font-family: arial;
  color: #fff;
}
.made-with-love i {
  font-style: normal;
  color: #F50057;
  font-size: 14px;
  position: relative;
  top: 2px;
}
.made-with-love a {
  color: #fff;
  text-decoration: none;
}
.made-with-love a:hover {
  text-decoration: underline;
}


/* for custom scrollbar for webkit browser*/

::-webkit-scrollbar {
    width: 6px;
} 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
} 
::-webkit-scrollbar-thumb {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
}





<!-- form -->


.leave-request-form
{

  width: 50%;
  margin-left: 25%;
  margin-top: 30px;
  margin-bottom:20px;
  border-radius: 30px;
  background-color: white;
  box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
}

.button1 {
border: 1px solid rgb(41,42,62);
border-radius: 3px;
width: 30%;
margin-left: 35%;
margin-top:15px;
height: 30px;
display: block;
color:rgb(41,42,62);

background: linear-gradient(to right, rgb(41,42,62) 50%,white  50%);
background-size: 200% 100%;
background-position: right bottom;
transition: all .5s ease-out;
}

.button1:hover {
background-position: left bottom;

color:white;
}

.col-form-label
{
  font-weight: bold;
  font-size: large
}










</style>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="icon" href="data,">
</head>
<body>
<input id="tokenField" type="hidden" value="${sessionScope.token }" />
<div class="topnav">

  <a href="#" onclick='show(1);'>View Truck</a> 
  <a href="#" onclick='show(2);'>Add Truck</a> 
  <a href="#" onclick='show(3);'>Update/Delete Trucks</a>
  
    <select id="report" style="background-color:  #333; margin-top: 15px; color:white; border-width:0px; font-size: 18px; padding-left:10px; border:none;"  >
    <option value="" selected disabled hidden>Download Report</option>
    <option value="Truck Report as PDF">Truck Report as PDF</option>
    <option value="Truck Report as Excel">Truck Report as Excel</option>
     
  </select>	
  
  <button style="background-color:#333; color: white;  font-size: 18px; " onclick="Download()">Download</button>
  	
  
  
  
  
  <form action="/logout" method="POST">
	<input style="margin-left: 800px; font-size:18px; margin-top: -80px;  border: none; background: none; color: white;" type="submit" value="Logout">      
		</form>	
	</div>

<!-- view  -->
<section id="table1">

  <!--for demo wrap-->
  <h1>Truck Details</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0"; style="background-color: gray">
    <caption style="display:none"></caption>
     <thead>
        <tr>
            <th scope="col">Description</th>
  			<th scope="col">Vehicle type</th>
            <th scope="col">Location</th>
            <th scope="col">Status</th>
  			<th scope="col">Vehicle NO</th>
  <th scope="col">Owner Name</th>
  <th scope="col">Owner Contact</th>
  <th scope="col">Insurance Status</th>
<th scope="col"> Truck Id</th> 
        </tr>
        </thead>
    <tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
    </table>
  </div>
  
  <div class="tbl-content" id="requiredTable"></div>
  </section>
<!--     <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        
        
       
      </tbody>
    </table>
  </div>
</section>
 -->













<!-- add form -->

 <div id="table2" >
 <div style="padding-left: 350px; padding-right: 350px; padding-top: 30px" >
<div style="background-color: #000000;
background-image: linear-gradient(315deg, #000000 0%, #414141 74%) ; padding-top: -1000px; padding-top: 50px; border-radius: 30px;" 
  class="leave-request-form">
 <form  onSubmit="return createTruck()" method="post" id="addtable">


        <div class="row" id="reg" style="margin-top: 10px;" >
  <div class="col-md-2">
  
  </div>
  
  <div class="col-md-2">
      
  </div>
  </div>
  
  <!-- 	<input type="text" name="UserName" value="" id="txt" placeholder="UserName" onchange="myFunction(this.value)">
<p1 id="p1"></p1> -->

  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white; "  class="col-4 col-form-label">Vehicle ID<strong style="color:red">*</strong></label>
    <div class="col-8">
       <input style="width: 100%; height: 30px" class="form-control" required type="number" id="vid" onchange="truckFunctionID(this.value)" #emp_num>
    <p1  style="color: white;" id="p1" ></p1>
    </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white; " for="vtype" class="col-4 col-form-label">Vehicle Type<strong style="color:red">*</strong></label>
    <div class="col-8">
   <!--    <input style="width: 100%; height: 30px" class="form-control" type="text" required value="" id="vtype" #emp_num> -->
      
      <select  name="vtype"  class="form-control" required type="text" value="" id="vtype" style="width: 100%; height: 30px"#emp_num>
   <option value=""></option>
    <option value="TATA ACE">TATA Ace</option>
    <option value="EICHER PRO TIPPER ">EICHER Pro Tipper </option>
    <option value="ASHOK LEYLAND TONNE TRUCK">Ashok Leyland Tonne Truck</option>
    <option value="FREEZER TRUCK">Freezer Truck</option>
    <option value="DIECAST">Diecast</option>
    <option value="HYUNDAI HD35-78 ">Hyundai HD35-78</option>
    <option value="CONTAINER 32 FT SXL">Container 32 FT SXL</option>
    <option value="TATA TAURUS">TATA Taurus</option>
    
  </select>
      
    </div>
  </div>
  
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white; " for="vstatus" class="col-4 col-form-label">Status<strong style="color:red">*</strong></label>
    <div class="col-8">
     <!--   <input style="width: 100%;  height: 30px" class="form-control" type="text"  required value="" id="vstatus" #emp_num>-->
      <select  name="vstatus"  class="form-control" required type="text" value="" id="vstatus" style="width: 100%;  height: 30px" #emp_num>
   <option value=""></option>
    <option value="true">Booked</option>
    <option value="false">Available</option>
  </select>
    </div>
  </div>
  <!-- <input   pattern="[A-Z]{2}-[0-9]{4}" required class="form-control" type="tel" readonly="readonly" id="vvnum" #emp_num>
    <small style="color: white;">Format: TN-9999</small> -->
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white"  class="col-4 col-form-label">Vehicle Number<strong style="color:red">*</strong></label>
    <div class="col-8">
      <input style="width: 100%; height: 30px" pattern="[A-Z]{2}-[0-9]{4}"  class="form-control" type="text"  required  value="" id="vnum" onchange="truckFunctionNumber(this.value)" #emp_num>
     <p1  style="color: white;" id="p2" ></p1>
    <small style="color: white;">==>(Fomat: TN-9999)</small>
    </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white"  class="col-4 col-form-label">Owner<strong style="color:red">*</strong></label>
    <div class="col-8">
      <input style="width: 100% ;  height: 30px" class="form-control" type="text"  required value="" id="vowner" #emp_num>
    </div>
  </div>
  
  <!-- <input class="form-control" type="tel" pattern="[7-9]{1}[0-9]{9}" title="Enter a valid mobile number" required id="vonum" #emp_num>
    <small style="color: white;">Fomat: 9999999999</small>  -->
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white"  class="col-4 col-form-label">Owner Phone Number<strong style="color:red">*</strong></label>
    <div class="col-8">
      <input style="width: 100% ;  height: 30px" class="form-control" type="tel" pattern="[7-9]{1}[0-9]{9}" title="Enter a valid mobile number" required value="" id="onum" #emp_num>
   <small style="color: white;">==>(Fomat: 9999999999)</small>
    </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white" for="insurance" class="col-4 col-form-label">Insurance<strong style="color:red">*</strong></label>
    <div class="col-8">
    <!--    <input style="width: 100% ;  height: 30px" class="form-control" type="text"  required value="" id="insurance" #emp_num>-->
      
      <select id="insurance" name="insurance"  class="form-control"  required type="text" value=""  id="insurance" style="width: 100% ;  height: 30px"#emp_num>
   <option value=""></option>
    <option value="1">Yes</option>
    <option value="0">No</option>  
  </select>
    </div>
  </div>
  
   <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white; "  class="col-4 col-form-label">Description<strong style="color:red">*</strong></label>
    <div class="col-8">
    <textarea rows="2" cols="10" class="form-control" type="number" value=""  required  id="des" #emp_num>  </textarea>
     <!--   <input style="width: 100%; height: 30px" class="form-control" type="number" value="" id="vid" #emp_num>-->
    </div>
  </div>
  
  
 
  
   <button class="button1" >Submit</button>
    
 <button class="button1" onclick="myFunction2();">Cancel</button>
				
    <br>
  


        </form>
       
</div>
        </div>
</div>
   
   
   
   
   
   
   
  <!-- update/delete --> 
   
  <section id="table3">
<div style="padding-left: 1000px">	 <input  style="background-color: gray; width: 25px;" readonly="readonly"><strong style="color: white;"> Delete </strong>
		 <input  style="background-color: white; width: 25px; " readonly="readonly"><strong style="color: white;" > Edit </strong></div>
  <!--for demo wrap-->
  <h1>Truck Details</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0"; style="background-color: gray">
     <caption style="display:none"></caption>
        <tr>
          <th scope="col">Description</th>
  <th scope="col">Vehicle type</th>
  <th scope="col">Location</th>
  <th scope="col">Status</th>
  <th scope="col">Vehicle NO</th>
  <th scope="col">Owner Name</th>
  <th scope="col">Owner Contact</th>
  <th scope="col">Insurance Status</th>
<th scope="col"> Truck Id</th> 
<th scope="col" style="padding-left: 10px;">Manipulate</th>
 
        </tr>
    <tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
    </table>
  </div>

    <div class="tbl-content" id="editTruckTable"></div>
</section>
 
 
 
 
 
 
 <!-- edit truck -->
 <div id="table4">
  
 <div style="padding-left: 350px; padding-right: 350px; padding-top: 30px">
<!--  <div style="padding-top: 65px ;"></div>-->
<div style="background-color: #000000;
background-image: linear-gradient(315deg, #000000 0%, #414141 74%); padding-top: -1000px; padding-bottom:20px; "  class="leave-request-form">
       <form  onSubmit="return editTruckDetails()" method="post"> 
        <div class="row" id="reg" style="margin-top: 10px;">
    
  <div class="col-md-2">
  
  </div>
  
  <div class="col-md-2">
      
  </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white" for="vvtype" class="col-4 col-form-label">Vehicle Type<strong style="color:red">*</strong></label>
    <div class="col-8">
   <!--   <input  class="form-control"  required type="text"  id="vvtype" #emp_num>-->
     <select  name="vvtype"  class="form-control" required type="text" value="" id="vvtype" style="width: 100%; height: 30px"#emp_num>
   <option value=""></option>
    <option value="TATA ACE">TATA Ace</option>
    <option value="EICHER PRO TIPPER ">EICHER Pro Tipper </option>
    <option value="ASHOK LEYLAND TONNE TRUCK">Ashok Leyland Tonne Truck</option>
    <option value="FREEZER TRUCK">Freezer Truck</option>
    <option value="DIECAST">Diecast</option>
    <option value="HYUNDAI HD35-78">Hyundai HD35-78</option>
    <option value="CONTAINER 32 FT SXL">Container 32 FT SXL</option>
    <option value="TATA TAURUS">TATA Taurus</option>
    
  </select>
    </div>
  </div>
  
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white" for="vvstatus" class="col-4 col-form-label">Status<strong style="color:red">*</strong></label>
    <div class="col-8">
   <!--  <input  class="form-control"  required type="text"   id="vvstatus" #emp_num>--> 
 <select  name="vvstatus"  class="form-control" required type="text" value="" id="vvstatus" #emp_num>
   <option value=""></option>
    <option value="true">Booked</option>
    <option value="false">available</option>
        
  </select>    </div>
  </div>
  
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white" for="example-text-input" class="col-4 col-form-label">Vehicle Number<strong style="color:red">*</strong></label>
    <div class="col-8">
<!-- pattern="[A-Z]{2}-[0-9]{2}-[A-Z]{2}{3}{0}-[1-9999]{1}{2}{3}{4}" -->
     
     <input   pattern="[A-Z]{2}-[0-9]{4}" required class="form-control" type="tel" readonly="readonly" id="vvnum" #emp_num>
    <small style="color: white;">Fomat: TN-9999</small>      </div>
  </div>
  
  
   <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white" for="vvlocation" class="col-4 col-form-label">Location<strong style="color:red">*</strong></label>
    <div class="col-8">
  <!--      <input class="form-control" type="text" required name="vvlocation" id="vvlocation" #emp_num>-->
       <select name="vvlocation"  class="form-control" required type="text"  id="vvlocation"  style="width: 100%; height: 30px"#emp_num>
   <option value=""></option>
    <option value="Chennai">Chennai</option>
    <option value="Madurai">Madurai</option>
    <option value="Coimbatore">Coimbatore </option>
    <option value="Trichy">Trichy</option>
  </select>
    </div>
  </div>  
  
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label  style="color: white" for="example-text-input" class="col-4 col-form-label">Owner<strong style="color:red">*</strong></label>
    <div class="col-8">
      <input class="form-control" type="text" required  id="vvowner" #emp_num>
    </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white"  class="col-4 col-form-label">Owner Phone Number<strong style="color:red">*</strong></label>
    <div class="col-8">
<input class="form-control" type="tel" pattern="[7-9]{1}[0-9]{9}" title="Enter a valid mobile number" required id="vonum" #emp_num>
    <small style="color: white;">Fomat: 9999999999</small>    </div>
  </div>
  
  <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white" for="vinsurance" class="col-4 col-form-label">Insurance<strong style="color:red">*</strong></label>
    <div class="col-8">
 <select  name="vinsurance"  class="form-control"  required type="text"  id="vinsurance" #emp_num>
   <option value=""></option>
    <option value="1">Yes</option>
    <option value="0">No</option>
    
  </select>    </div>
  </div>
  
   <div class="form-group row" style="width: 80%;margin-left: 10%;margin-top: 20px;">
    <label style="color: white; "  class="col-4 col-form-label">Description<strong style="color:red">*</strong></label>
    <div class="col-8">
    <textarea rows="2" cols="10" class="form-control" type="number"  required  id="vvdes" #emp_num>  </textarea>
     <!--   <input style="width: 100%; height: 30px" class="form-control" type="number" value="" id="vid" #emp_num>-->
    </div>
  </div>
  
 
   <button class="button1" id="submit">Submit</button>

  
	
	</form>
	<button class="button1" onclick="myFunction1()">Cancel</button>
	</div>
	 </div>
	
</div>

 



 
 
 
 
            
</body>

<script>
var det;
var c;
function show(nr) {
    document.getElementById("table1").style.display="none";
    document.getElementById("table2").style.display="none";
    document.getElementById("table3").style.display="none";
    document.getElementById("table4").style.display="none";
    
    document.getElementById("table"+nr).style.display="block";
    if(nr==1){
		fillTruckTable();
	}
    if(nr==3){
    	fillEditTruckTable();
    	
    }
    if(nr==2)
    	{
    	clearform();
    	}
}
function clearform()
{
	document.getElementById('vid').value='';
	document.getElementById('vtype').value='';

	document.getElementById('vstatus').value='';

	document.getElementById('vnum').value='';

	document.getElementById('onum').value='';

	document.getElementById('vowner').value='';
	document.getElementById('insurance').value='';
	document.getElementById('des').value='';
	

	
	
	}
function tableCreate2() {
	document.getElementById("editTruckTable").innerHTML="";
	var body = document.getElementById("editTruckTable"), tbl = document.createElement('table');
	console.log("Inside table functin*********");
	console.log(det["0"]);
	var keyArray=["description","model","location","status","truckNum","ownerName","ownerContact","insuranceDetails","tid"];
	var totalLength=Object.keys(det).length;
	var singleLength=9;
	console.log("*****");
	for (var i = 0; i < totalLength; i++) {
		var tr = tbl.insertRow();
		for (var j = 0; j < singleLength; j++) {
			
				var td = tr.insertCell();
				td.appendChild(document.createTextNode(det[i.toString()][keyArray[j]]));
			
		}
		

		var d = document.createElement('Button');
		d.style.text = "test";
		d.style.background = 'gray';
		d.style.outline= "none";
		d.style.border= "none";
		d.style.textweight =" bold ";
		d.style.height= "25px";	
		d.style.color= "red";		
		d.style.width="30px";
		d.style.margin="2px";
		d.setAttribute("id","sddd"+i);
		d.setAttribute("onClick","deletetruck(this.id)");
		tr.appendChild(d);
		//openEditManagerPage
		
		c = document.createElement('Button');
		c.innerHtml = 'Edit';
	
		c.style.background = 'white';
		c.style.outline= "none";
		c.style.border= "none";
		c.style.textweight =" bold ";
		c.style.height= "25px";	
		c.style.color= "green";		
		c.style.width="30px";
		c.style.margin="4px";
		
		
		tr.appendChild(c);
		
		
		//d.setAttribute("onClick",'deletetruck()');
		c.setAttribute("onClick",'show(4);run();');
		
			
		
		

	}
body.appendChild(tbl);
}


function deletetruck(a){
	var number=a[4];
	//alert(number);
	$.ajax({
		url : "/deleteTruck",
		type : 'POST',
		data : {
			token : $('#tokenField').val(),
			truckNum : det[number]['truckNum']
			
		},
		//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
		dataType : 'text',
		headers : {
			"Authorization" : "Bearer " + $('#tokenField').val()
		},
		success : function(data) {
			alert(data);
			fillEditTruckTable()

		},
		error : function() {
			//alert('Error occured');

		},
		async : false
	})


}

function run(){
    document.getElementById('table3').onclick = function(event){
        event = event || window.event; //for IE8 backward compatibility
        var target = event.target || event.srcElement; //for IE8 backward compatibility
        while (target && target.nodeName != 'TR') {
            target = target.parentElement;
        }
        var cells = target.cells; //cells collection
        //var cells = target.getElementsByTagName('td'); //alternative
        if (!cells.length || target.parentNode.nodeName == 'THEAD') { // if clicked row is within thead
            return;
        }
        var f1 = document.getElementById('vvdes');
        var f2 = document.getElementById('vvtype');
        var f3 = document.getElementById('vvlocation');
        var f4 = document.getElementById('vvstatus');
        var f5 = document.getElementById('vvnum');
        var f6 = document.getElementById('vvowner');
        var f7 = document.getElementById('vonum');
        var f8 = document.getElementById('vinsurance');
      
        f1.value = cells[0].innerHTML;
        f2.value = cells[1].innerHTML;
        f3.value = cells[2].innerHTML;
        f4.value = cells[3].innerHTML;
        f5.value = cells[4].innerHTML;
        f6.value = cells[5].innerHTML;
        f7.value = cells[6].innerHTML;
        f8.value = cells[7].innerHTML;
       
    }
}










function tableCreate() {
	document.getElementById("requiredTable").innerHTML="";

	var body = document.getElementById("requiredTable"), tbl = document.createElement('table');
	var keyArray=["description","model","location","status","truckNum","ownerName","ownerContact","insuranceDetails","tid"];
	var totalLength=Object.keys(det).length;
	var singleLength=9;
	console.log("*****");
	for (var i = 0; i < totalLength; i++) {
		var tr = tbl.insertRow();
		for (var j = 0; j < singleLength; j++) {
			
				var td = tr.insertCell();
				td.appendChild(document.createTextNode(det[i.toString()][keyArray[j]]));
			
		}
	
	}
	body.appendChild(tbl);
}

	
function fillTruckTable() {
	$.ajax({
				url : "/allTrucks",
				type : 'POST',
				data : "token=" + $('#tokenField').val(),
				dataType : 'json',
				headers : {
					"Authorization" : "Bearer "
							+ $('#tokenField').val()
				},
				success : function(data) {
					det=data;
					tableCreate();
				},
				error : function() {
					//alert('Error occured');
				},
				async : false
			})
	console.log(det);
}

function createTruck(){
	$.ajax({
		url : "/addTruck",
		type : 'POST',
		data : {token: $('#tokenField').val(),tid:$('#vid').val(),model:$('#vtype').val(),status:$('#vstatus').val(),truckNum:$('#vnum').val(),ownerName:$('#vowner').val(),ownerContact:$('#onum').val(),insuranceDetails:$('#insurance').val(),description:$('#des').val()},
		dataType : 'text',
		headers : {
			"Authorization" : "Bearer "
					+ $('#tokenField').val()
		},
		success : function(data) {
			alert(data);
			myFunction2();},
		error : function() {
			//alert('Error occured');
			myFunction2();
		},
		async : false
	})
	return false;
}
function fillEditTruckTable() {
	$.ajax({
				url : "/allTrucks",
				type : 'POST',
				data : "token=" + $('#tokenField').val(),
				dataType : 'json',
				headers : {
					"Authorization" : "Bearer "
							+ $('#tokenField').val()
				},
				success : function(data) {
					det=data;
					tableCreate2();
				},
				error : function() {
					//alert('Error occured');
				},
				async : false
			})
	console.log(det);
}
function editTruckDetails() {
	
	$.ajax({
				url : "/updateTruck",
				type : 'POST',
				data: {token: $('#tokenField').val(), model:$('#vvtype').val(),insuranceDetails:$('#vinsurance').val(), truckNum:$('#vvnum').val(), ownerName:$('#vvowner').val(),ownerContact:$('#vonum').val(),status:$('#vvstatus').val(),description:$('#vvdes').val(),location:$('#vvlocation').val(),},
				//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
				dataType : 'text',
				headers : {
					"Authorization" : "Bearer "
							+ $('#tokenField').val()
				},
				success : function(data) {
					alert(data);
					myFunction1();
				},
				error : function() {
					//alert('Error occured');
					myFunction1();
				},
				async : false
			})
			
			/// !!!!!!!!!!!!
			return false;
}


function myFunction()
{
	//alert("Do you want to delete it!!!")
}



function myFunction1() {
	  var x = document.getElementById("table4");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
}

function myFunction2() {
	  var x = document.getElementById("table2");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
}

function deleteManager()
{
	//alert("delete function is called");
	}

function reset() {
	 
	   var frm = document.getElementsByName('addtable')[0];
	
	   frm.reset();  
	   return false; 
	
	 
		
	 }	
	 
function Download()
{
	var d = document.getElementById("report").value;
	//alert(d);
	
	if(d==="Truck Report as Excel"){
		//alert("in csv frontend");
		$.ajax({
			url : "/exportCSVTruck",
			type : 'POST',
			data : {
				token : $('#tokenField').val(),
			},
			//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
			dataType : 'text',
			
			success : function(data) {
				window.location="/exportCSVTruck"
			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})


	}
	if(d==="Truck Report as PDF"){
	$.ajax({
		url : "/exportpdf",
		type : 'POST',
		data : {
			token : $('#tokenField').val(),
		},
		//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
		dataType : 'text',
		
		success : function(data) {
			window.location="/exportpdf"
		},
		error : function() {
			//alert('Error occured');

		},
		async : false
	})
	}

	}
	
	
function truckFunctionID(val){
	$.ajax({
		url : "/checkTruckIdAvailability",
		type : 'POST',
		data : {tid: val},
		dataType : 'text',
		success : function(data) {
			document.getElementById("p1").innerHTML=data;
			if(data==="Truck ID Already Exists"){alert("Truck ID Already Exists");}},
		error : function() {
			//alert('Error occured');
			
		},
		async : false
	})
	
}

function truckFunctionNumber(val){
	$.ajax({
		url : "/checkTruckNumberAvailability",
		type : 'POST',
		data : {truckNum: val},
		dataType : 'text',
		success : function(data) {
			document.getElementById("p2").innerHTML=data;
			if(data==="Truck Number Already Exists"){alert("Truck Number Already Exists");}},
		error : function() {
			//alert('Error occured');
			
		},
		async : false
	})
	
}









</script>


</html>