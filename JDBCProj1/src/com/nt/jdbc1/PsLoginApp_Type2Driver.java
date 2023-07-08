package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp_Type2Driver {
	private static final String LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD= ?";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String uname = null, pwd = null;
			sc = new Scanner(System.in);
			System.out.println("Enter username:");
			uname = sc.nextLine();
			System.out.println("Enter password:");
			pwd = sc.nextLine();
			// Load the driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:oci8:@xe", "system", "NITESH");
			// Create the statement object
			if (con != null) {
				ps = con.prepareStatement(LOGIN_QUERY);
			}
			if (ps != null) {
				ps.setString(1, uname);
				ps.setString(2, pwd);
				rs = ps.executeQuery();
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
				if (ps != null)
					ps.close();
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
