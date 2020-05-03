<%@page
	import="com.healthcareSystem_Payment.service.payment.paymentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.healthcareSystem_Payment.service.*"%>
<%@ page import="com.healthcareSystem_Payment.model.*"%>
<%@ page import="com.healthcareSystem_Payment.resources.*"%>
<%
	if (request.getParameter("userid") != null) {
	paymentService paymnentServiceObj = new paymentService();
	String stsMsg = "";

	//Insert new hospital
	if (request.getParameter("hiddenPaymentIDSave") == "") {
		PaymentResources paymentresources = new PaymentResources();
		stsMsg = paymentresources.StoreOnlinePayment(request.getParameter("radiopaymentmethod"),Integer.parseInt(request.getParameter("appointmentid")),Integer.parseInt( request.getParameter("userid")), request.getParameter("purpose"),request.getParameter("status"),Float.parseFloat(request.getParameter("amount")),request.getParameter("card_type"),request.getParameter("card_number"),request.getParameter("cvv"),request.getParameter("expiryDate"));

	}
	//Update existing hospital
	/* else {
		stsMsg = hospObj.updateHospitals(request.getParameter("hidIHospIDSave"), request.getParameter("hospName"),
		request.getParameter("hospAddr"), request.getParameter("hospEmail"), request.getParameter("hospPhone"),
		request.getParameter("hospRegDate"), request.getParameter("hospCharge"));
	} */
}
//Deelete existing hosptal
/* if (request.getParameter("hidHospIDDelete") != null) {
	Hospital hospObj = new Hospital();
	String stsMsg = hospObj.deleteHospital(request.getParameter("hidHospIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
} */
%>

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

				<form id="formPayment" name="formPayment" method="post" action="Payments.jsp">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">
							<span class="input-group-text" id="lblname"> User ID: </span>
						</div>
						<input id="userid" name="userid" type="text">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Appointment
								ID: </span>
						</div>
						<input id="appointmentid" name="appointmentId" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepared">
							<span class="input-group-text" id="lblname">Payment Method</span>
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
						<input id="purpose" name="purpose" type="text">
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Status: </span>
						</div>
						<input id="status" name="status" type="text">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-pretend">

							<span class="input-group-text" id="lblname"> Amount: </span>
						</div>
						<input id="amount" name="amount" type="text">
					</div>



					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hiddenPaymentIDSave" name="hiddenPaymentIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					paymentService paymentservice = new paymentService();
				out.print(paymentservice.readAllPymentRecords());
				%>
			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-8" id="colPayments"></div>

		</div>

		<br>

	</div>

</body>
</html>