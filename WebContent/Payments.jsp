<%@page
	import="com.healthcareSystem_Payment.service.payment.paymentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.healthcareSystem_Payment.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Service</title>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Component/jquery-3.5.0.min.js"></script>
<script src="Component/payment.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Payment Details</h1>

				<form id="formPayment" name="formPayment">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> User ID: </span>
						</div>
						<input id="hospName" name="hospName" type="text">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Appointment
								ID: </span>
						</div>
						<input id="hospName" name="hospName" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepared">
							<span class="input-group-text" id="lblname">Payment
								Method</span>
						</div>
						&nbsp;&nbsp;Cash <input id="radopaymentmethodCash"
							name="radiopaymentmethod" type="radio" value="Cash">
						&nbsp;&nbsp;Card <input id="radopaymentmethodCard"
							name="radiopaymentmethod" type="radio" value="Card">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Purpose: </span>
						</div>
						<input id="hospName" name="hospName" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Amount: </span>
						</div>
						<input id="hospName" name="hospName" type="text">
					</div>
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidIHospIDSave" name="hidIHospIDSave" value="">
				</form>
			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-8" id="colPayments"></div>

		</div>
		<div id="alertSuccess" class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
		</div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>

	</div>

</body>
</html>