package com.healthcareSystem_Payment.service.payment;
import java.sql.*;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;
import org.apache.el.parser.ParseException;
import com.healthcareSystem_Payment.utils.*;
import com.healthcareSystem_Payment.model.Payment;
import com.healthcareSystem_Payment.resources.PaymentResources;


public class paymentService {
	DBconnection db = DBconnection.getDBconnection() ;
	Connection connection;
	PaymentResources paymentresources=new PaymentResources();
	
	
	public String StoreOnlinePayment(Payment payment) {
		String query1 = " INSERT INTO `payment`(`Payment_method`,`appointment_id`, `user_id`,`purpose`, `amount`, `status`) VALUES (?,?,?,?,?,?)";
		
		String query2 = " INSERT INTO `card_details`(`payment_id`,`user_id`, `card_type`, `card_number`, `cvv`, `expiryDate`) VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStmt1;
		PreparedStatement preparedStmt2;
		
		String output;
		
		try {
			preparedStmt1 = db.connection.prepareStatement(query1);
			 preparedStmt2 =db.connection.prepareStatement(query2);
			    preparedStmt1.setString(1, payment.getPaymentmethod());
				preparedStmt1.setInt(2,payment.getAppointmentid());
				preparedStmt1.setInt(3,payment.getUserid());
				preparedStmt1.setString(4,payment.getPurpose());
				preparedStmt1.setFloat(5,payment.getAmount());
				preparedStmt1.setString(6,payment.getStatues());
				
				preparedStmt1.execute();
			if(payment.getPaymentmethod().equals("Card")==true) {
							
				String id ="SELECT max(payment_id) as payment_id FROM `payment`";
				Statement stmt1 = db.connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(id);
				 preparedStmt2 =db.connection.prepareStatement(query2);
				 
				  
				 
				while(rs1.next()) { 	
			  		  preparedStmt2.setInt(1,rs1.getInt("payment_id"));
			  		  preparedStmt2.setInt(2,payment.getUserid());
					  preparedStmt2.setString(3,payment.getCardtype());
					  preparedStmt2.setString(4,payment.getCardnumber());
					  preparedStmt2.setString(5,payment.getCvv());
					  preparedStmt2.setString(6,payment.getExpiredate()); 
					  preparedStmt2.execute();
					
				}
			  			
			
			
			}
			 
			  //db.connection.close(); }
			 
			
			 output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
public String readAllPymentRecords() {
		
		String output="";
		// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Payment ID</th><th>Appointment ID</th><th>User ID</th><th>Paymment Method</th><th>Payment time</th><th>Purpose</th><th>Amount</th><th>Statues</th></tr>";
					String query = "select * from `payment`";
					try {
					
						Statement stmt = db.connection.createStatement();
						ResultSet rs=stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next()) {
							String paymentid = Integer.toString(rs.getInt("payment_id"));
							String appointmentid = Integer.toString(rs.getInt("appointment_id"));
							String userid = Integer.toString(rs.getInt("user_id"));
							String paymentMethod = rs.getString("Payment_method");
							DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
							String paymentTime =dateFormat.format(rs.getDate("paid_time")); 	
							String purpose = rs.getString("purpose");
							String Amount = Float.toString(rs.getFloat("amount"));
							String statues = rs.getString("status");
							
							// Add into the html table
							output += "<tr><td>" + paymentid + "</td>";
							output += "<td>" + appointmentid + "</td>";
							output += "<td>" + userid + "</td>";
							output += "<td>" + paymentMethod + "</td>";
							output += "<td>" + paymentTime + "</td>";
							output += "<td>" + purpose + "</td>";
							output += "<td>" + Amount + "</td>";
							output += "<td>" + statues + "</td>";
					} 
				//		db.connection.close();
						// Complete the html table
						output += "</table>";	
					}catch (SQLException e) {
						output = "Error while reading the payments.";
					System.err.println(e.getMessage());
					}
		return output;
		
	}
public String updatePaymentdetails(Payment payment) { 
	String output = "";
	String query = "UPDATE `payment` SET `Payment_method`=?,`purpose`=?,`amount`=?,`status`=? WHERE `payment_id`=?";
	PreparedStatement preparedStmt;
	
	try {
		
		 java.util.Date today = new java.util.Date();
		     
		
		 preparedStmt = db.connection.prepareStatement(query);
		// preparedStmt.setInt(1,payment.getUserid());
		 preparedStmt.setString(1,payment.getPaymentmethod());
		 preparedStmt.setString(2,payment.getPurpose());
		 preparedStmt.setFloat(3,payment.getAmount());
		 preparedStmt.setString(4,payment.getStatues());
		 preparedStmt.setInt(5,payment.getPaymentid());
		// execute the statement
			preparedStmt.execute();
			output = "Updated successfully";
	} catch (SQLException e) {
		output = "Error while updating the user.";
		System.err.println(e.getMessage());
	}
	return output;
}


public String deletecardDetails(String payment_id) {
	String output;
	
	// create a prepared statement
	            String query1 = "UPDATE `payment` SET `status`='cancel' WHERE `payment_id`=?";
				String query2 = "DELETE FROM `card_details` WHERE `payment_id`=?";
				PreparedStatement preparedStmt1;
				PreparedStatement preparedStmt2;
				try {
					System.out.print("==============="+payment_id+"===============");
				preparedStmt1= db.connection.prepareStatement(query1);
				preparedStmt2= db.connection.prepareStatement(query2);
				// binding values
				preparedStmt1.setInt(1, Integer.parseInt(payment_id));
				preparedStmt2.setInt(1, Integer.parseInt(payment_id));
				// execute the statement
				preparedStmt1.execute();
				preparedStmt2.execute();
				
				output = "Deleted successfully";
				
			} catch (Exception e) {
				output = "Error while deleting the card information.";
				System.err.println(e.getMessage());
			}
				
			return output;
}

	
}


