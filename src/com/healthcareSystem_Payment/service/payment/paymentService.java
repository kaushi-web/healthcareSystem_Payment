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
		String query1 = " INSERT INTO `payment`(`Payment_method`, `paid_time`, `purpose`, `amount`, `status`) VALUES (?,?,?,?,?)";
		
		String query2 = " INSERT INTO `card_details`(`payment_id`, `card_type`, `card_number`, `cvv`, `expiryDate`) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStmt1;
		PreparedStatement preparedStmt2;
		DateFormat df = new SimpleDateFormat(" yyyy-mm-dd hh:mm:ss");
	       Date dateobj = new Date();
	       System.out.println(df.format(dateobj));
	       Timestamp ts=Timestamp.valueOf(df.format(dateobj));
	       payment.setPaymenttime(ts);
		String output;
		try {
			preparedStmt1 = db.connection.prepareStatement(query1);
			preparedStmt1.setString(1, payment.getPaymentmethod());
			preparedStmt1.setTimestamp(2,payment.getPaymenttime());
			preparedStmt1.setString(3,payment.getPurpose());
			preparedStmt1.setFloat(3,payment.getAmount());
			preparedStmt1.setString(4,payment.getStatues());
			preparedStmt1.execute();
			 
			 if(payment.getPaymentmethod()=="cash") {
			preparedStmt2 = db.connection.prepareStatement(query2);
			preparedStmt2.setInt(1,1);
			preparedStmt2.setString(2,payment.getCardtype());
			preparedStmt2.setString(3,payment.getCardnumber());
			preparedStmt2.setString(4,payment.getCvv());
			preparedStmt2.setString(5,payment.getExpiredate());
			preparedStmt2.execute();
			//db.connection.close();
			}
			
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
					output = "<table border=\"1\"><tr><th>User ID</th><th>Payment ID</th><th>Paymment Method</th><th>Payment time</th><th>Purpose</th><th>Amount</th><th>Statues</th></tr>";
					String query = "select * from `payment`";
					try {
					
					//	Statement stmt = db.connection.createStatement();
						Statement stmt = db.connection.createStatement();
						ResultSet rs=stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next()) {
							String userId = Integer.toString(rs.getInt("user_id"));
							String paymentid = Integer.toString(rs.getInt("payment_id"));
							String paymentMethod = rs.getString("Payment_method");
							DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
							String paymentTime =dateFormat.format(rs.getDate("paid_time")); 	
							String purpose = rs.getString("purpose");
							String Amount = Float.toString(rs.getFloat("amount"));
							String statues = rs.getString("status");
							
							// Add into the html table
							output += "<tr><td>" + paymentid + "</td>";
							output += "<td>" + userId + "</td>";
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
		String query = "UPDATE `payment` SET `payment_id`=?,`Payment_method`=?,`purpose`=?,`amount`=?,`status`=? WHERE `payment_id`=?";
		PreparedStatement preparedStmt;
		
		try {
			
			 java.util.Date today = new java.util.Date();
			     
			
			 preparedStmt = db.connection.prepareStatement(query);
			 preparedStmt.setInt(1,payment.getPaymentid());
			 preparedStmt.setString(2,payment.getPaymentmethod());
			 preparedStmt.setString(3,payment.getPurpose());
			 preparedStmt.setFloat(4,payment.getAmount());
			 preparedStmt.setString(5,payment.getStatues());
			 preparedStmt.setInt(6,payment.getPaymentid());
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
					String query = "DELETE FROM `card_details` WHERE `payment_id`=?";
					PreparedStatement preparedStmt;
					try {
						System.out.print("==============="+payment_id+"===============");
					preparedStmt= db.connection.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(payment_id));
					// execute the statement
					preparedStmt.execute();
					db.connection.close();
					output = "Deleted successfully";
				} catch (Exception e) {
					output = "Error while deleting the card information.";
					System.err.println(e.getMessage());
				}
				return output;
	}
	
	/*public String deleteDoctorDetails(String doctor_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor where doctor_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(doctor_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor information.";
			System.err.println(e.getMessage());
		}
		return output;
	}*/
}


