<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<%
if (session.getAttribute("userid") == null) {
	response.sendRedirect("/");
}
%>


<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Emi Calculator</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {
	font: 400 15px Lato, sans-serif;
	line-height: 1.8;
	color: #818181;
}

.jumbotron {
	background-color: #f4511e;
	color: #fff;
	padding: 100px 25px;
	font-family: Montserrat, sans-serif;
}

.container-fluid {
	padding: 60px 50px;
}

.bg-grey {
	background-color: #f6f6f6;
}
</style>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Online Banking</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">HOME</a></li>
					<%
					if (session.getAttribute("userid") != null) {
					%>
					<li><a href="/dashboard">CHECK BALANCE</a></li>
					<li><a href="/fundtransfer">FUND TRANSFER</a></li>
					<li><a href="/upitransfer">UPI</a></li>
					<li class="active"><a href="/loan">LOAN</a></li>
					<li><a href="/passbook">PASSBOOK</a></li>
					<li><a href="/profile">PROFILE</a></li>
					<li><a
						onclick="if(confirm('Are you sure you want to log out')){window.location.href='/logout'}">LOGOUT</a></li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>APPLY FOR A LOAN</h1>
		<p>Use our calculator to predict the emi and interest amount</p>
		<button class="btn btn-primary" data-toggle="modal"
			data-target="#loan-modal" data-ticket-type="premium-access">APPLY
			FOR A LOAN NOW</button>
	</div>

	<%
	int loan1 = Integer.parseInt(String.valueOf(request.getAttribute("maxloan")));
	%>



	<div class="container">
		<h1>
			Your Eligible Loan Amount:
			Rs.<%=loan1%>/-</h1>
		<div class="row">
			<div class="col-lg-6">
				<div>
					<h3>Loan Amount</h3>
					<input type=number name="amt" class="form-control" id="amt"
						style="border-radius: 5px" required> <br>
				</div>
				<div>
					<h3>Select Type</h3>
					<select name="" onchange="myFunction(event)" class="form-control">
						<option disabled selected>Choose Lone Type</option>
						<option value=3>car</option>
						<option value=2>bike</option>
						<option value=5.8>house</option>
						<option value=6.9>personal</option>
					</select>
				</div>
				<div>
					<h3>Interest Rate</h3>
					<input type="number" name="apr" class="form-control" id="apr"
						style="border-radius: 5px" disabled>
				</div>
				<div>
					<h3>Time Periods(yrs)</h3>
					<input type=number name="tenure" class="form-control" id="tenure"
						style="border-radius: 5px" required> <br>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				<div>
					<button style="border-radius: 2px" id="find" onclick="validate()"
						class="btn btn-primary">Find Emi</button>
				</div>
			</div>
			<div class="col-lg-3 text-center">
				<h1>Results</h1>
				<br />
				<h3>
					Total EMI: Rs.<span id="emi"></span>/-
				</h3>
				<h3>
					Total Amount: Rs.<span id="totalAmt"></span>/-
				</h3>
				<h3>
					Total Interest: Rs.<span id="totalInt"></span>/-
				</h3>
			</div>
		</div>
	</div>



	<!--apply for loan modal -->

	<div id="loan-modal" class="modal fade">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title ">APPLY FOR A LOAN</h4>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body ">
					<form method="POST" action="/loanrequest">
						<input type="hidden" name="id"
							value="<%=session.getAttribute("userid")%>" />
						<div class="form-group">
							<label for="loanamt">Enter a loan amount :</label> <input
								class="form-control" type="number" name="loanamt" id="loanamt"
								placeholder="Amount is in Rupees" required />
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						<input type="submit" id="submit"
							onclick="alert('Your request is submitted. A agent will will contact you soon')"
							name="submit" value="APPLY FOR  A LOAN" />
					</form>
					<p class="text-center">A agent will will contact you soon..</p>
				</div>

			</div>
		</div>
	</div>

	<br>
	<br>
	<script>
		function myFunction(e) {
			var interestRate = document.getElementById('apr').value;
			document.getElementById("apr").value = e.target.value;
		}

		function validate() {
			var loan = parseInt(document.getElementById('amt').value);
			var interestRate = document.getElementById('apr').value;
			var tenure = document.getElementById('tenure').value;
			if (loan <
	<%=loan1%>
		&& loan != "") {
				if (tenure != "") {
					if (interestRate != "") {
						var noOfMonths = Math.floor(tenure) * 12;
						var monthlyRate = interestRate / (12 * 100);
						var onePlusR = Math.pow(1 + monthlyRate, noOfMonths)
						var denominator = onePlusR - 1;
						var emi = (loan * monthlyRate * (onePlusR / denominator))
								.toPrecision(5);
						var totalAmt = noOfMonths * parseFloat(emi);
						var totalInt = Math.floor(totalAmt - loan);

						var payableEmi = document.getElementById('emi');
						var payableamount = document.getElementById('totalAmt');
						var payableInt = document.getElementById('totalInt');
						payableEmi.innerHTML = parseFloat(emi);
						payableamount.innerHTML = parseFloat(totalAmt);
						payableInt.innerHTML = parseFloat(totalInt);
					} else {
						alert('interest cannot be empty');
					}
				} else {
					alert('tenure cannot be empty');
				}
			} else {
				alert("Invalid Loan Amount");
			}

		}
	</script>
</body>

</html>