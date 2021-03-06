package com.healthcareSystem_Payment.resources;

import javax.swing.text.Document;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.healthcareSystem_Payment.model.Payment;
import com.healthcareSystem_Payment.service.payment.paymentService;
import com.mysql.cj.xdevapi.JsonParser;

import org.json.*;
import org.json.simple.parser.JSONParser;
//For XML
import org.jsoup.nodes.*;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;

@Path("/Payment")
public class PaymentResources {
	Payment payment = new Payment();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String StoreOnlinePayment(@FormParam("Payment_method") String Payment_method,
			@FormParam("appointment_id") int appointment_id, @FormParam("user_id") int user_id,
			@FormParam("purpose") String purpose, @FormParam("status") String status, @FormParam("amount") float amount,
			@FormParam("card_type") String card_type, @FormParam("card_number") String card_number,
			@FormParam("cvv") String cvv, @FormParam("expiryDate") String expiryDate

	) {
		paymentService paymentobj = new paymentService();
		String output;
		if (Payment_method.equals("Card") || Payment_method.equals("card") || Payment_method.equals("Cash")
				|| Payment_method.equals("cash")) {
			payment.setPaymentmethod(Payment_method);
			payment.setAppointmentid(appointment_id);
			payment.setUserid(user_id);
			payment.setPurpose(purpose);
			payment.setStatues(status);
			payment.setAmount(amount);
			if (card_type.equals("visa") || card_type.equals("Visa") || card_type.equals("master")
					|| card_type.equals("Master") || card_type.equals("credit card") || card_type.equals("Credit card")
					|| card_type.equals("Credit Card")) {
				payment.setCardtype(card_type);
				payment.setCardnumber(card_number);
				payment.setCvv(cvv);
				payment.setExpiredate(expiryDate);
			} else {
				output = "card type has misspelled or inccorect";
				return output;
			}
			output = paymentobj.StoreOnlinePayment(payment);
		}

		else {
			output = "payment method is incorrect";
		}

		return output;

	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		paymentService paymentobj = new paymentService();

		String output = paymentobj.readAllPymentRecords();
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayments(String paymentData) {
		// convert data to json object
		System.out.println(paymentData);
		// new com.google.gson.JsonParser().parse(paymentData).getAsJsonObject();
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		JsonObject itemObject = parser.parse(paymentData).getAsJsonObject();
		paymentService paymentobj = new paymentService();
		// Read the values from the JSON object
		String payment_id = itemObject.get("payment_id").toString();
		String paymentMethod = itemObject.get("Payment_method").toString();
		String purpose = itemObject.get("purpose").toString();
		String amt = itemObject.get("amount").toString();
		String sts = itemObject.get("status").toString();

		payment.setPaymentid(Integer.parseInt(payment_id));
		payment.setPaymentmethod(paymentMethod);
		payment.setPurpose(purpose);
		payment.setAmount(Float.parseFloat(amt));
		payment.setStatues(sts);
		String output = paymentobj.updatePaymentdetails(payment);
		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecarddetails(String paymentData) {
		System.out.println(paymentData);
		org.jsoup.nodes.Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		// Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		paymentService paymentobj = new paymentService();
		// Read the value from the element <userID>
		String payment_id = doc.select("payment_id").text();
		String output = paymentobj.deletecardDetails(payment_id);
		return output;
	}
}
