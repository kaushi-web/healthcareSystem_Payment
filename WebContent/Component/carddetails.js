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

	// card type
	if ($("#cardtype").val().trim() == "") {
		return "Insert card type.";
	}

	// Card Number
	if ($("#cardnumber").val().trim() == "") {
		return "Insert card number.";
	}

	// cvv
	if ($("#cvv").val().trim() == "") {
		return "Insert CVV.";
	}
	// EXIPRE DATE
	if ($("#expiredate").val().trim() == "") {
		return "Insert expiredate.";
	}
	return true;
}