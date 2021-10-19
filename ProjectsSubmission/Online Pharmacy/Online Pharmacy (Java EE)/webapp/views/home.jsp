<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/homepagestyle.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>

</head>
<body>
	<%@include file="navbar.jsp" %>
	<header> 
	</header>
	<section id="about-us">
		<div class="container">
			<div class="row align-items-center">
				<div class="col">
					<img
						src="https://st2.depositphotos.com/2234518/10915/i/950/depositphotos_109159650-stock-photo-female-and-male-pharmacists.jpg"
						class="img-fluid about-image" alt="About Us">
				</div>
				<div class="col text-col">
					<h1 class="text-primary">About Us</h1>
					<p class="lead">Improving lives together</p>
					<p>For over 32 years, our Pharmacy has been providing you with
						genuine medicines round-the-clock, through 24-hour pharmacies. And
						now through E-Pharmacy, we intend to make your lives easier – by
						getting your medicines delivered to you. Yes, no more stepping out
						to get your medicines, no more standing in long queues, no more
						worrying about the genuineness of medicines, no more sweat!</p>
				</div>
			</div>
		</div>
	</section>
	<section class="content">
		<div class="container">
			<h3 class="display-3 text-center text-secondary"
				style="margin-bottom: 30px;">Our Services</h3>
			<div class="row cards">
				<div class="col-md-4 d-flex justify-content-center">
					<div class="card" style="width: 20rem;">
						<img src="${pageContext.request.contextPath}/assets/medicine.jpg" class="card-img-top icons"
							alt="Book Appointment" />
						<div class="card-body">
							<h5 class="card-title text-primary">Genuine Medicine</h5>
							<p class="card-text">The products sold through our Pharmacy
								are inspected thoroughly to ensure only genuine products make
								the cut.</p>
						</div>
					</div>
				</div>

				<div class="col-md-4 d-flex justify-content-center">
					<div class="card" style="width: 20rem;">
						<img src="${pageContext.request.contextPath}/assets/medicine-delivery.jpg"
							class="card-img-top icons" alt="Service Two" />
						<div class="card-body">
							<h5 class="card-title text-primary">Fastest Delivery</h5>
							<p class="card-text">We understand that you may sometimes
								require medicines in urgency and that is why we assure you the
								fastest home delivery of your medicines..</p>
						</div>
					</div>
				</div>

				<div class="col-md-4 d-flex justify-content-center">
					<div class="card" style="width: 20rem;">
						<img src="${pageContext.request.contextPath}/assets/trust.jpg" class="card-img-top icons"
							alt="Service Three" />
						<div class="card-body">
							<h5 class="card-title text-primary">Most Trusted</h5>
							<p class="card-text">We ensure that every product sold are
								checked for their authenticity, quality, and compliance with the
								Central Drugs Standard Control Organization.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="contact">
		<div class="container">
			<div class="row align-items-center">
				<div class="col">
					<h3>Contact US</h3>
					<button type="button" class="btn btn-outline-light">
						<span class="material-icons-outlined me-2" id="phone"
							style="float: left;">call</span> <label for="phone">7031896452</label>
					</button>
					<button type="button" class="btn btn-outline-light">
						<span class="material-icons-outlined me-2" id="email"
							style="float: left;">email</span> <label for="email">e-pharmacy@pharm.com</label>
					</button>
					<button type="button" class="btn btn-outline-light">
						<span class="material-icons-outlined me-2" id="home"
							style="float: left;">home</span> <label for="home">No:8
							Anna nagar, Chennai</label>
					</button>
				</div>
				<p class="mt-2">&copy; E-Pharmacy</p>
			</div>
		</div>
	</section>
</body>
</html>