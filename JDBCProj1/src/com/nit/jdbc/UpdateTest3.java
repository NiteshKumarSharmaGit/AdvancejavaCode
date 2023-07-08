package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest3 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			Float hikePercentage=0.0f;
			String desg1=null;
			String desg2=null;
			String desg3=null;
			sc = new Scanner(System.in);
			System.out.println("Enter hike percentage:");
			hikePercentage=sc.nextFloat();
			sc.nextLine();
			System.out.println("Enter desg1");
			desg1 = sc.nextLine();
			System.out.println("Enter desg2:");
			desg2 = sc.nextLine();
			System.out.println("Enter desg3");
			desg3 = sc.nextLine();

			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query format
			desg1="'"+desg1+"'";
			desg2="'"+desg2+"'";
			desg3="'"+desg3+"'";
			// Prepare the query
			//String query = UPDATE Emp SET SAL = SAL + (SAL * 10/100) where SAL>=1300 AND SAL<=3000
			
			String query="UPDATE EMP SET SAL = SAL + (SAL *"+hikePercentage/100+") WHERE JOB IN("+desg1+","+desg2+","+desg3+")";
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
