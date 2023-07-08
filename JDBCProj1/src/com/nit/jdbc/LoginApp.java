package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String uname = null, pwd = null;
			sc = new Scanner(System.in);
			System.out.println("Enter username:");
			uname = sc.nextLine();
			System.out.println("Enter password:");
			pwd = sc.nextLine();
			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// prepare sql formatted query
			uname = "'" + uname + "'";
			pwd = "'" + pwd + "'";
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Query preparation
			String query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=" + uname + "AND PWD=" + pwd;
			System.out.println("Query is" + query);
			// send and execute the query
			if (st != null) {
				rs = st.executeQuery(query);
			}
			int count = 0;
			if (rs != null) {
				rs.next();
				count = rs.getInt(1);
			}
			if (count == 0) {
				System.out.println("Invalid credentials!!");
			} else {
				System.out.println("Valid credentials!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
