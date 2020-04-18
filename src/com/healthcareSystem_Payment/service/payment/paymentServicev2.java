package com.healthcareSystem_Payment.service.payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.healthcareSystem_Payment.resources.PaymentResources;
import com.healthcareSystem_Payment.resources.PaymentResourseALlternative;
import com.healthcareSystem_Payment.utils.DBconnection;

public class paymentServicev2 {
	DBconnection db = DBconnection.getDBconnection() ;
	Connection connection;
	PaymentResourseALlternative paymentresources2=new PaymentResourseALlternative();
	
	public String calculatetotalrevenuePerPatient() {
		String output = "";
		output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>Paymment Method</th><th>Total Payment</th><th>User ID</th><th>User Name</th><th>Paymment Method</th><th>Total Payment</th></tr>";
		String query ="select users.user_id,users.username,payment.Payment_method,sum(payment.amount) as totalPayment from payment,appointments,users where payment.user_id=users.user_id and payment.status='paid' group by payment.Payment_method";
		
		try {
			
			Statement stmt = db.connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userid = Integer.toString(rs.getInt("users.user_id"));
				String username = rs.getString("users.username");
				String paymentMethod = rs.getString("payment.Payment_method");	
				String Amount = Float.toString(rs.getFloat("totalPayment"));
				
				// Add into the html table
				output += "<td>" + userid + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + paymentMethod + "</td>";
				output += "<td>" + Amount + "</td>";
				
		} 
//			db.connection.close();
			// Complete the html table
			output += "</table>";	
		}catch (SQLException e) {
			output = "Error while reading the payments.";
		System.err.println(e.getMessage());
		}
		return output;
	}
}
