
<!--  -->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Page</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background-image:
		url("https://www.wallpaperup.com/uploads/wallpapers/2015/05/10/683550/3a852250979b74b325058a15132f5008.jpg");
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
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
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

#table1, #table2, #table3, #table4, #table6, #table5 {
	display: none;
}

table {
	width: 100%;
	table-layout: fixed;
	background: black;
}

.tbl-header {
	background-color: rgba(255, 255, 255, 0.3);
}

.tbl-content {
	height: 300px;
	overflow-x: auto;
	margin-top: 0px;
	border: 1px solid rgba(255, 255, 255, 0.3);
}

th {
	padding: 20px 15px;
	text-align: left;
	font-weight: 500;
	font-size: 12px;
	color: #fff;
	text-transform: uppercase;
}

td {
	padding: 15px;
	text-align: left;
	vertical-align: middle;
	font-weight: 300;
	font-size: 12px;
	color: #fff;
	border-bottom: solid 1px rgba(255, 255, 255, 0.1);
}

/* demo styles */
section {
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
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}

::-webkit-scrollbar-thumb {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}

<!--
form -->.leave-request-form {
	width: 50%;
	margin-left: 25%;
	margin-top: 30px;
	margin-bottom: 20px;
	border-radius: 30px;
	background-color: white;
	box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23);
}

.button1 {
	border: 1px solid rgb(41, 42, 62);
	border-radius: 3px;
	width: 30%;
	margin-left: 35%;
	margin-top: 15px;
	height: 30px;
	display: block;
	color: rgb(41, 42, 62);
	background: linear-gradient(to right, rgb(41, 42, 62) 50%, white 50%);
	background-size: 200% 100%;
	background-position: right bottom;
	transition: all .5s ease-out;
}

.button1:hover {
	background-position: left bottom;
	color: white;
}

.col-form-label {
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

		<a href="#" onclick='show(1);'>View Managers</a> 
		<a href="#"onclick='show(3);'>Add Manager</a>
		 <a href="#" onclick='show(2);'>Update/DeleteManager</a>
		  <a href="#" onclick='show(6);'>View Trucks</a>
		   <a href="#" onclick='show(5);'>Address Queries</a> 
			
		<select id="report" style="background-color: #333; margin-top: 15px; color: white; border-width: 0px; font-size: 18px; padding-left: 10px; border: none;">
			<option value="" selected disabled hidden>Download Report</option>
			<option value="Truck Report as PDF">Truck Report as PDF</option>
			<option value="Truck Report as Excel">Truck Report as Excel</option>
			<option value="Manager Report as PDF">Manager Report as PDF</option>
			<option value="Manager Report as Excel">Manage Report as Excel</option>
		</select>
		<button style="background-color: #333; color: white; font-size: 18px;"
			onclick="Download()">Download</button>

		<form action="/logout" method="POST">
			<input
				style="margin-left: 500px; font-size: 18px; margin-top: -80px; border: none; background: none; color: white;"
				type="submit" value="Logout">
		</form>





	</div>


	<section id="table1">

		<!--for demo wrap-->
		<h1 style="color: white;">Manager Details</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0"
				; style="background-color: gray">
			<caption style="display:none"></caption>
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Email</th>
						<th scope="col">Branch</th>

					</tr>
				</thead>
				<tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
			</table>
		</div>
		<div class="tbl-content" id="requiredTable"></div>
	</section>





	<section id="table2">
		<div style="padding-left: 1000px">
			<input style="background-color: gray; width: 25px;"
				readonly="readonly"><strong style="color: white;"> Delete </strong> <input
				style="background-color: white; width: 25px;" readonly="readonly"><strong
				style="color: white;"> Edit </strong>
		</div>
		<!--for demo wrap-->
		<h1 style="color: white;">Manager Details</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0"
				; style="background-color: gray">
				<caption style="display:none"></caption>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">User Name</th>
					<th scope="col">Email Id</th>
					<th scope="col">Branch</th>
					<th scope="col">Password</th>
					<th scope="col" style="padding-left: 55px;">Manipulate</th>

				</tr>
				<tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
			</table>
		</div>
		<div class="tbl-content" id="editManagerTable"></div>
	</section>



	<section id="table6">

		<!--for demo wrap-->
		<h1 style="color: white;">Truck Details</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0"
				; style="background-color: gray">
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
					<th scope="col">Truck Id</th>
				</tr>
				<tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
			</table>
		</div>
		<div class="tbl-content" id="allTrucksTable"></div>
	</section>



	<!-- edit  -->
	<div id="table4">
		<div style="padding-top: 30px"></div>
		<!-- <h3> <button onclick="autoFill()">To auto fill click here.</button></h3> -->
		<h1 id="check"></h1>
		<div
			style="background-color: #000000; background-image: linear-gradient(315deg, #000000 0%, #414141 74%); padding-top: -1000px; padding-bottom: 20px; padding-top: 50px; margin-left: 400px; margin-right: 300px; margin-bottom: 30px;"
			class="leave-request-form">
			<form onSubmit="return editManagerDetails()" method="post">

				<div class="row" id="reg" style="margin-top: 10px;">
					<div class="col-md-2"></div>

					<div class="col-md-2"></div>
				</div>
				<strong>
					<h3 style="padding-left: 230px; margin-top: -5px; color: white;">EDIT
						MANAGER</h3>
				</strong>
				<div class="form-group row"
					style="align: center; width: 80%; margin-left: 10%; margin-top: 20px;">
					<label style="color: white" for="example-text-input"
						class="col-4 col-form-label">Name<strong style="color: red">*</strong></label>
					<div class="col-8">
						<input class="form-control" required type="text" id="emname"
							#emp_num>
					</div>
				</div>


				<div class="form-group row"
					style="align: center; width: 80%; margin-left: 10%; margin-top: 20px;">
					<label style="color: white" for="example-text-input"
						class="col-4 col-form-label">UserName<strong style="color: red">*</strong></label>
					<div class="col-8">
						<input class="form-control" required type="text" readonly
							id="emuname" #emp_num>
					</div>
				</div>


				<div class="form-group row"
					style="width: 80%; margin-left: 10%; margin-top: 20px;">
					<label style="color: white" for="example-text-input"
						class="col-4 col-form-label">Email<strong style="color: red">*</strong></label>
					<div class="col-8">
						<input class="form-control" required type="email" id="ememail"
							#emp_num>
					</div>
				</div>


				<div class="form-group row"
					style="width: 80%; margin-left: 10%; margin-top: 20px;">
					<label style="color: white" for="embranch"
						class="col-4 col-form-label">Branch<strong style="color: red">*</strong></label>
					<div class="col-8">
						<select name="embranch" class="form-control" required type="text"
							id="embranch" style="width: 100%; height: 30px" #emp_num>
							<option value=""></option>
							<option value="Chennai">Chennai</option>
							<option value="Madurai">Madurai</option>
							<option value="Coimbatore">Coimbatore</option>
							<option value="Trichy">Trichy</option>
						</select>
					</div>
				</div>

				<div class="form-group row"
					style="width: 80%; margin-left: 10%; margin-top: 20px;">
					<label style="color: white" for="example-text-input"
						class="col-4 col-form-label">Password<strong style="color: red">*</strong></label>
					<div class="col-8">
						<input class="form-control"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							id="empassword" #emp_num>
					</div>
				</div>
				<!-- pattern="(?=.\d)(?=.[a-z])(?=.*[A-Z]).{8,}"  id="submit" -->
				<button class="button1">Submit</button>

				<br>.

			</form>
			<button class="button1" onclick="myFunction1();">Cancel</button>

		</div>

	</div>














	<!-- add form -->


	<div id="table3">
		<form onSubmit="return createNewManager()" method="post" id="t3">
			<div
				style="padding-left: 350px; padding-right: 350px; padding-top: 30px">
				<div
					style="background-color: #000000; background-image: linear-gradient(315deg, #000000 0%, #414141 74%); padding-top: -1000px; padding-top: 50px; border-radius: 30px;"
					class="leave-request-form">
					<div class="row" id="reg" style="margin-top: 10px;">
						<div class="col-md-2"></div>

						<div class="col-md-2"></div>
					</div>
					<strong>
						<h3 style="padding-left: 240px; margin-top: -5px; color: white;">ADD
							MANAGER</h3>
					</strong>
					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white;" for="example-text-input"
							class="col-4 col-form-label">Name<strong style="color: red">*</strong></label>
						<div class="col-8">
							<input style="width: 100%; height: 30px" class="form-control"
								id="cnname" required type="text" #emp_num>
						</div>
					</div>



					<!-- 	<input type="text" name="UserName" value="" id="txt" placeholder="UserName" onchange="myFunction(this.value)">
<p1 id="p1"></p1>
			 -->
					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white;" for="example-text-input"
							class="col-4 col-form-label">UserName<strong
							style="color: red">*</strong></label>
						<div class="col-8">
							<input style="width: 100%; height: 30px" class="form-control"
								id="cnuname" required type="text"
								onchange="myFunction(this.value)" #emp_num>
							<p1 style="color: white;" id="p1"></p1>
						</div>
					</div>

					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white;" for="example-text-input"
							class="col-4 col-form-label">Id<strong style="color: red">*</strong></label>
						<div class="col-8">
							<input style="width: 100%; height: 30px" class="form-control"
								id="cnid" required type="number"
								onchange="myFunctionID(this.value)" #emp_num>
							<p1 style="color: white;" id="p2"></p1>
						</div>
					</div>




					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white;" for="example-text-input"
							class="col-4 col-form-label">Email<strong style="color: red">*</strong></label>
						<div class="col-8">
							<input style="width: 100%; height: 30px" class="form-control"
								id="cnemail" required="required" type="email" #emp_num>
						</div>
					</div>


					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white" for="cnbranch"
							class="col-4 col-form-label">Branch<strong style="color: red">*</strong></label>
						<div class="col-8">
							<select name="cnbranch" class="form-control" id="cnbranch"
								required type="text" style="width: 100%; height: 30px" #emp_num>
								<option value=""></option>
								<option value="Chennai">Chennai</option>
								<option value="Madurai">Madurai</option>
								<option value="Coimbatore">Coimbatore</option>
								<option value="Trichy">Trichy</option>
							</select>

						</div>
					</div>

					<div class="form-group row"
						style="width: 80%; margin-left: 10%; margin-top: 20px;">
						<label style="color: white" for="example-text-input"
							class="col-4 col-form-label">Password<strong
							style="color: red">*</strong></label>
						<div class="col-8">
							<!-- pattern="(?=.\d)(?=.[a-z])(?=.*[A-Z]).{8,}" -->
							<input style="width: 100%; height: 30px" required id="cnpassword"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								class="form-control" type="password" #emp_num>
						</div>
					</div>



					<button class="button1">Submit</button>
					<button class="button1" onclick="myFunction2(); reset();">Cancel</button>
					<!--  	<input class="button1"  value="Cancel" type="submit" onclick="myFunction2(); reset();" >-->



				</div>
			</div>

		</form>
	</div>





	







	<!-- Queries -->
	<section id="table5">
		<h1 style="color: white;">User Queries</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0"
				; style="background-color: gray">
<caption style="display:none"></caption>
				<thead>
					<tr>
						<th scope="col">UserName</th>
						<th scope="col">Query</th>
						<th scope="col">Response</th>
						<th scope="col">Save Response</th>
					</tr>
				</thead>
				<tbody style="display:none"><tr><td></td><td></td><td></td></tr></tbody>
			</table>
		</div>
		<div id="respondQuery"></div>

	</section>














</body>

<script>
	var det;
	var c;
	function show(nr) {
		document.getElementById("table1").style.display = "none";
		document.getElementById("table2").style.display = "none";
		document.getElementById("table3").style.display = "none";
		document.getElementById("table4").style.display = "none";
		document.getElementById("table5").style.display = "none";

		document.getElementById("table6").style.display = "none";

		document.getElementById("table" + nr).style.display = "block";
		if (nr == 1) {
			fillManagerTable();
		}
		if (nr == 2) {
			fillEditManagerTable();

		}
		if (nr == 6) {
			fillAllTrucksTable();
		}

		if (nr == 3) {

			clearform();
		}

		if (nr == 5) {
			retrieveQueries();
		}
	}
	function clearform() {
		document.getElementById('cnname').value = '';
		document.getElementById('cnuname').value = '';

		document.getElementById('cnid').value = '';

		document.getElementById('cnemail').value = '';

		document.getElementById('cnbranch').value = '';

		document.getElementById('cnpassword').value = '';

	}
	function tableCreate3() {
		document.getElementById("allTrucksTable").innerHTML = "";
		var body = document.getElementById("allTrucksTable"), tbl = document
				.createElement('table');
		console.log("Inside table functin*****");
		console.log(det["0"]);
		var keyArray = [ "description", "model", "location", "status",
				"truckNum", "ownerName", "ownerContact", "insuranceDetails",
				"tid" ];
		var totalLength = Object.keys(det).length;
		var singleLength = 9;
		console.log("***");
		for (var i = 0; i < totalLength; i++) {
			var tr = tbl.insertRow();
			for (var j = 0; j < singleLength; j++) {

				var td = tr.insertCell();
				td.appendChild(document
						.createTextNode(det[i.toString()][keyArray[j]]));

			}

		}
		body.appendChild(tbl);
	}

	function tableCreate2() {
		document.getElementById("editManagerTable").innerHTML = "";
		var body = document.getElementById("editManagerTable"), tbl = document
				.createElement('table');
		console.log("Inside table functin*****");
		console.log(det["0"]);
		var keyArray = [ "name", "userName", "email", "branch", "password" ];
		var totalLength = Object.keys(det).length;
		var singleLength = 5;
		console.log("***");
		var tr = tbl.insertRow();
		var td = tr.insertCell();
		//td.appendChild(document.createTextNode("Name"));
		//td.appendChild(document.createTextNode("userName"));
		//td.appendChild(document.createTextNode("email"));
		//td.appendChild(document.createTextNode("branch"));
		for (var i = 0; i < totalLength; i++) {
			tr = tbl.insertRow();
			for (var j = 0; j < singleLength; j++) {

				td = tr.insertCell();
				td.appendChild(document
						.createTextNode(det[i.toString()][keyArray[j]]));

			}

			var d = document.createElement('Button');
			d.innerHtml = 'Edit';
			d.style.background = 'gray';
			d.style.outline = "none";
			d.style.border = "none";
			d.style.textweight = " bold ";
			d.style.height = "25px";
			d.style.color = "red";
			d.style.width = "90px";
			d.style.margin = "5px";
			d.setAttribute("id","sedd"+i);
			d.setAttribute("onClick","deleteManager(this.id)");
			tr.appendChild(d);
			//openEditManagerPage

			c = document.createElement('Button');
			c.innerHtml = 'Edit';

			c.style.background = 'white';
			c.style.outline = "none";
			c.style.border = "none";
			c.style.textweight = " bold ";
			c.style.height = "25px";
			c.style.color = "green";
			c.style.width = "90px";
			c.style.margin = "4px";

			tr.appendChild(c);

			//d.setAttribute("onClick", 'deleteManager()');
			c.setAttribute("onClick", 'show(4);run();');

		}
		body.appendChild(tbl);
	}

	function run() {
		document.getElementById('table2').onclick = function(event) {
			event = event || window.event;
			var target = event.target || event.srcElement;
			while (target && target.nodeName != 'TR') {
				target = target.parentElement;
			}
			var cells = target.cells;

			if (!cells.length || target.parentNode.nodeName == 'THEAD') {
				return;
			}
			var f1 = document.getElementById('emname');
			var f2 = document.getElementById('emuname');
			var f3 = document.getElementById('ememail');
			var f4 = document.getElementById('embranch');
			var f5 = document.getElementById('empassword');

			f1.value = cells[0].innerHTML;
			f2.value = cells[1].innerHTML;
			f3.value = cells[2].innerHTML;
			f4.value = cells[3].innerHTML;
			f5.value = cells[4].innerHTML;

		}
	}

	function deleteManager(a) {
		
		var number=a[4];
		$.ajax({
			url : "/deleteManager",
			type : 'POST',
			data : {
				token : $('#tokenField').val(),
				userName : det[number]['userName']
				
			},
			//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
			dataType : 'text',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				alert(data);
				fillEditManagerTable()

			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})

		
	}

	function tableCreate() {
		document.getElementById("requiredTable").innerHTML = "";
		var body = document.getElementById("requiredTable"), tbl = document
				.createElement('table');

		console.log("Inside table functin*****");
		console.log(det["0"]);
		var keyArray = [ "name", "email", "branch" ];
		var totalLength = Object.keys(det).length;
		var singleLength = 3;
		console.log("***");
		for (var i = 0; i < totalLength; i++) {
			var tr = tbl.insertRow();
			for (var j = 0; j < singleLength; j++) {

				var td = tr.insertCell();
				td.appendChild(document
						.createTextNode(det[i.toString()][keyArray[j]]));

			}

		}
		body.appendChild(tbl);
	}

	function fillManagerTable() {
		$.ajax({
			url : "/searchAllManager",
			type : 'POST',
			data : "token=" + $('#tokenField').val(),
			dataType : 'json',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				det = data;
				tableCreate();
			},
			error : function() {
				//alert('Error occured');
			},
			async : false
		})
		console.log(det);
	}

	function fillAllTrucksTable() {
		$.ajax({
			url : "/allTrucks",
			type : 'POST',
			data : "token=" + $('#tokenField').val(),
			dataType : 'json',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				det = data;
				tableCreate3();
			},
			error : function() {
				//alert('Error occured');
			},
			async : false
		})
		console.log(det);
	}

	function createNewManager() {
		//alert("name"+ $('#cnname').val());
		console.log($('#name').val());
		$.ajax({
			url : "/createManager",
			type : 'POST',
			data : {
				token : $('#tokenField').val(),
				name : $('#cnname').val(),
				userName : $('#cnuname').val(),
				email : $('#cnemail').val(),
				password : $('#cnpassword').val(),
				branch : $('#cnbranch').val(),
				id : $('#cnid').val()
			},
			dataType : 'text',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				//	alert("Hello");
				alert(data);
				myFunction2();

			},
			error : function() {
				//alert('Error occured');
				myFunction2();
			},
			async : false
		})
		return false;
	}

	function fillEditManagerTable() {
		$.ajax({
			url : "/searchAllManager",
			type : 'POST',
			data : "token=" + $('#tokenField').val(),
			dataType : 'json',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				det = data;
				tableCreate2();
			},
			error : function() {
				//alert('Error occured');
			},
			async : false
		})
		console.log(det);
	}

	function editManagerDetails() {
		$.ajax({
			url : "/updateManager",
			type : 'GET',
			data : {
				token : $('#tokenField').val(),
				name : $('#emname').val(),
				userName : $('#emuname').val(),
				email : $('#ememail').val(),
				password : $('#empassword').val(),
				branch : $('#embranch').val()
			},
			//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
			dataType : 'text',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
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
		return false;
	}

	var det2;
	function retrieveQueries() {
		$.ajax({
			url : "/viewQueries",
			type : 'GET',
			data : "token=" + $('#tokenField').val(),
			//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
			dataType : 'json',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				det2 = data;
				populateQueryTable()

			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})
	}

	function populateQueryTable() {
		//var i;
		//var j;

		document.getElementById("respondQuery").innerHTML = "";
		var body = document.getElementById("respondQuery"), tbl = document
				.createElement('table');
		console.log("Inside table functin*****");
		console.log(det2["0"]["userName"]);
		console.log(det2);
		var keyArray = [ "userName", "query"];
		// document.getElementById("response").contentEditable = true;
		var totalLength = Object.keys(det2).length;

		var singleLength = 2;
		console.log("***");
		for (var i = 0; i < totalLength; i++) {
			var tr = tbl.insertRow();
			for (var j = 0; j < singleLength; j++) {

				var td = tr.insertCell();
				td.appendChild(document
						.createTextNode(det2[i.toString()][keyArray[j]]));

			}
			var input = document.createElement("input");
		    input.setAttribute("type", "text");
		    input.setAttribute("id","response"+i);
		    tr.appendChild(input);
		    
			var d = document.createElement('Button');
			d.innerHtml = 'Send';
			d.style.background = 'gray';
			d.style.outline = "none";
			d.style.border = "none";
			d.style.textweight = " bold ";
			d.style.height = "20px";
			d.style.width = "90px";
			d.style.margin = "5px";
			d.setAttribute("id","send"+i);
			d.setAttribute("onClick","updateQueries(this.id)");
			tr.appendChild(d);
			//d.setAttribute("onClick","sendQuery( det2[i.toString()][keyArray[j]] )");
		}
		
		
		body.appendChild(tbl);
		
			//var element=document.getElementById("send"+i);
			//element.setAttribute("onClick","sendQuery('a','b',')");
		
	}



	function updateQueries(a) {
		var number=a[4];
		var responseNumber='#response'+number;
		$.ajax({
			url : "/answerQuery",
			type : 'POST',
			data : {
				token : $('#tokenField').val(),
				serialNumber : det2[number]['serialNumber'],
				response : $(responseNumber).val()
				
			},
			//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
			dataType : 'text',
			headers : {
				"Authorization" : "Bearer " + $('#tokenField').val()
			},
			success : function(data) {
				alert(data);

			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})
	}

	function myFunction1() {
		var x = document.getElementById("table4");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
		//alert("edit");
	}

	function myFunction2() {
		var y = document.getElementById("table3");

		if (y.style.display === "none") {
			y.style.display = "block";
		} else {
			y.style.display = "none";
		}

	}

	function reset() {

		//alert("reset");
		var frm = document.getElementsByName('t3')[0];

		frm.reset();

		// return false;

	}

	function myFunction(val) {

		$.ajax({
			url : "/checkUserNameAvailability",
			type : 'POST',
			data : {
				userName : val
			},
			dataType : 'text',
			success : function(data) {
				document.getElementById("p1").innerHTML = data;
				if(data==="User Name Already Exists"){alert(data);}
			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})

	}

	function myFunctionID(val) {
		$.ajax({
			url : "/checkUserIdAvailability",
			type : 'POST',
			data : {
				id : val
			},
			dataType : 'text',
			success : function(data) {
				document.getElementById("p2").innerHTML = data;
				if(data==="ID Already Exists"){alert(data);}
				
			},
			error : function() {
				//alert('Error occured');

			},
			async : false
		})

	}

	function Download() {
		var d = document.getElementById("report").value;
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
		if(d==="Manager Report as PDF"){
			$.ajax({
				url : "/exportpdfManager",
				type : 'POST',
				data : {
					token : $('#tokenField').val(),
				},
				//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
				dataType : 'text',
				
				success : function(data) {
					window.location="/exportpdfManager"
				},
				error : function() {
					//alert('Error occured');

				},
				async : false
			})
			}
		if(d==="Manager Report as Excel"){
			$.ajax({
				url : "/exportCSV",
				type : 'POST',
				data : {
					token : $('#tokenField').val(),
				},
				//data : "token=" + $('#tokenField').val() + "&name="+$('#name').val() + "&userName="+$('#uname').val() + "&email="+$('#email').val() + "&password="+$('#password').val() + "&branch="+$('#branch').val() ,
				dataType : 'text',
				
				success : function(data) {
					window.location="/exportCSV"
				},
				error : function() {
					//alert('Error occured');

				},
				async : false
			})
			}
	}
	
	function downloadPDF(data){
		var blob = new Blob([data], { type: "application/octetstream" });
		var fileName="C:\\Users\\Ramprakaash\\Downloads\\TransportSystem\\iTextHelloWorld.pdf";
        //Check the Browser type and download the File.
        var isIE = false || !!document.documentMode;
        if (isIE) {
            window.navigator.msSaveBlob(blob, fileName);
        } else {
            var url = window.URL || window.webkitURL;
            link = url.createObjectURL(blob);
            var a = $("<a />");
            a.attr("download", fileName);
            a.attr("href", link);
            $("body").append(a);
            a[0].click();
            $("body").remove(a);
	}
	}
</script>


</html>