// page refresh moment
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// add event handler to save button
$(document).on("click", "#btnSave", function(event) {
	// Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();

	// If not valid
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// if valid
	$("#formPayment").submit();

});
function validateItemForm() {

	// user ID
	if ($("#userid").val().trim() == "") {
		return "Insert User ID.";
	}

	// Appointment ID
	if ($("#appointmentid").val().trim() == "") {
		return "Insert Appointment ID.";
	}

	// Payment Method
	if ($('input[name="radiopaymentmethod"]:checked').length === 0) {
		return "Select payment method.";
	}
	// Purpose
	if ($("#purpose").val().trim() == "") {
		return "Insert purpose.";
	}

	// Amount
	if ($("#amount").val().trim() == "") {
		return "Insert amount.";
	}
	return true;
}