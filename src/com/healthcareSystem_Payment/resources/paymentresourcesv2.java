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
import com.healthcareSystem_Payment.service.payment.paymentServiceversion2;
import com.mysql.cj.xdevapi.JsonParser;
import org.json.*;
import org.json.simple.parser.JSONParser;
//For XML
import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;

@Path("/Paymentver2")
public class paymentresourcesv2 {

	Payment payment = new Payment();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String StoreOnlinePayment(@FormParam("Payment_method") String Payment_method,
			@FormParam("appointment_id") int appointment_id, @FormParam("user_id") int user_id,
			@FormParam("purpose") String purpose, @FormParam("status") String status, @FormParam("amount") float amount

	) {
		paymentServiceversion2 paymentobj1 = new paymentServiceversion2();
		String output;
		
			payment.setPaymentmethod(Payment_method);
			payment.setAppointmentid(appointment_id);
			payment.setUserid(user_id);
			payment.setPurpose(purpose);
			payment.setStatues(status);
			payment.setAmount(amount);
			
			
			output=paymentobj1.StoreOnlinePaymentAll(payment);
				return output;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		paymentServiceversion2 paymentobj = new paymentServiceversion2();

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
		paymentServiceversion2 paymentobj = new paymentServiceversion2();
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
		paymentServiceversion2 paymentobj = new paymentServiceversion2();
		// Read the value from the element <userID>
		String payment_id = doc.select("payment_id").text();
		String output = paymentobj.deletecardDetails(payment_id);
		return output;
	}

}
