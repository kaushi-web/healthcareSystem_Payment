<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Service- card payments</title>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Component/jquery-3.5.0.min.js"></script>
<script src="Component/carddetails.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Card Details</h1>

				<form id="formPayment_Card" name="formPayment_Card">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">
							<span class="input-group-text" id="lblname"> Card Type: </span>
						</div>
						<input id="cardtype" name="cardtype" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">
							<span class="input-group-text" id="lblname"> Card Number:
							</span>
						</div>
						<input id="cardnumber" name="cardnumber" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">
							<span class="input-group-text" id="lblname"> CVV: </span>
						</div>
						<input id="cvv" name="cvv" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">
							<span class="input-group-text" id="lblname"> Expire Date:
							</span>
						</div>
						<input id="expiredate" name="expiredate" type="text">
					</div>

					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hiddenPaymentIDSave" name="hiddenPaymentIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success" >
				<%
				out.print(session.getAttribute("statusMsg"));
				%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			
			</div>
		</div>


		<div class="row">
			<div class="col-8" id="colPayments_Cards"></div>

		</div>
</body>
</html>