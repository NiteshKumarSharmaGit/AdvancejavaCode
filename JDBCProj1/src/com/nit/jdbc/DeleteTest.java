package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			sc = new Scanner(System.in);
			String city = null;
			if (sc != null) {
				System.out.println("Enter the city name:");
				city = sc.next();
			}
			// Fromateting the query
			city = "'" + city + "'";
			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// prepare the query
			String query = "DELETE FROM STUDENT WHERE SADD=" + city;
			System.out.println("Query is:" + query);
			int count = 0;
			// Send and execute the query
			if (st != null) {
				count = st.executeUpdate(query);
			}
			if (count == 0) {
				System.out.println("No records found to delete");
			} else {
				System.out.println("No. of record deleted:" + count);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getErrorCode() >= 900 && e.getErrorCode() <= 999) {
				System.out.println("Invalid column name or table name!");
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
