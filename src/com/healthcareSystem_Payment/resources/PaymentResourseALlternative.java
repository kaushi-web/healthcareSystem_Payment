package com.healthcareSystem_Payment.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.healthcareSystem_Payment.service.payment.paymentService;
import com.healthcareSystem_Payment.service.payment.paymentServicev2;

@Path("/Paymentv2")
public class PaymentResourseALlternative {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readspesificuserrevenue()  {   
		paymentServicev2 paymentobj=new paymentServicev2();
		
		String output=paymentobj.calculatetotalrevenuePerPatient();
		return output;
		}
}
