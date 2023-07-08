package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTestMySql {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			String sname = null;
			String saddr = null;
			int sno = 0;
			float avg = 0.0f;
			sc = new Scanner(System.in);
			System.out.println("Enter student name:");
			sname = sc.nextLine();
			System.out.println("Enter student address:");
			saddr = sc.nextLine();
			System.out.println("Enter avg value:");
			avg = sc.nextFloat();
			System.out.println("Enter sno:");
			sno = sc.nextInt();
			// Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root");
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query format
			sname = "'" + sname + "'";
			saddr = "'" + saddr + "'";
			// Prepare the query
			String query = "UPDATE STUDENT SET SNAME=" + sname + ",SADD=" + saddr + ",AVG=" + avg + " WHERE SNO=" + sno;
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
