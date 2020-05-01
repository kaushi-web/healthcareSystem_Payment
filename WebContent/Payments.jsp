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

				<form id="formPayment" name="formPayment" >
					User ID: <input id="hospName" name="hospName" type="text"
						class="form-control form-control-sm"> <br>
					Appointment ID: <input id="hospAddr" name="hospAddr" type="text"
						class="form-control form-control-sm"> <br> Payment
					Method: <input id="hospEmail" name="hospEmail" type="text"
						class="form-control form-control-sm"> <br> Payment
					Purpose: <input id="hospPhone" name="hospPhone" type="text"
						class="form-control form-control-sm"> <br> Amount: <input
						id="hospRegDate" name="hospRegDate" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidIHospIDSave" name="hidIHospIDSave" value="">
				</form>
			</div>
		</div>
		<br>
		
		<div class="row">
		<div class ="col-8" id ="colPayments">
		
		
		</div>
		
		</div>
		<div id="alertSuccess" class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
		</div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
	</div>

</body>
</html>