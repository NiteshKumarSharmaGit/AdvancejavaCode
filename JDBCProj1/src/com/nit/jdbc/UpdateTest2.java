package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest2 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			float percentage = 0.0f;
			String firstCity = null;
			String secondCity = null;
			String thirdCity = null;
			sc = new Scanner(System.in);
			System.out.println("Enter percentage for to add in avg:");
			percentage = sc.nextFloat();
			sc.nextLine();
			System.out.println("Enter first city:");
			firstCity = sc.nextLine();
			System.out.println("Enter second city:");
			secondCity = sc.nextLine();
			System.out.println("Enter third city:");
			thirdCity = sc.nextLine();

			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query format
			firstCity = "'" + firstCity + "'";
			secondCity = "'" + secondCity + "'";
			thirdCity = "'" + thirdCity + "'";
			// Prepare the query
			// UPDATE student SET AVG = AVG + (AVG * 10/100) where sadd
			// in('bettiah','Patna','Mumbai');

			String query = "UPDATE STUDENT SET AVG = AVG + (AVG*" + percentage / 100 + ") WHERE SADD IN(" + firstCity + ","
					+ secondCity + "," + thirdCity + ")";
			System.out.println("Query is:" + query);
			// send and execute the result
			int count = 0;
			if (st != null) {
				count = st.executeUpdate(query);
			}
			// process the result
			if (count == 0) {
				System.out.println("No records found to update!");
			} else {
				System.out.println("No of record updated!::" + count);
			}

		} catch (SQLException e) {
			if (e.getErrorCode() > 900 && e.getErrorCode() < 999) {
				System.out.println("Invalid coulmn or table name!");
			} else if (e.getErrorCode() == 12899) {
				System.out.println("Too large value for column size!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
