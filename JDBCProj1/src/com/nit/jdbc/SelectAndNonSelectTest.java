package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectAndNonSelectTest {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String query = null;
			sc = new Scanner(System.in);
			System.out.println("Enter(SELECT or NON-SELECT sql) query::");
			query = sc.nextLine();
			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// create statement object
			if (con != null) {
				st = con.createStatement();
			}
			if (st != null) {
				boolean flag = false;
				flag = st.execute(query);
				if (flag == true) {
					System.out.println("SELECT SQL QUERY EXECUTED!!");
					rs = st.getResultSet();
					while (rs.next()) {
						System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
					}
				} else {
					System.out.println("NON-SELECT SQL QUERY IS EXECUTED");
					int count = st.getUpdateCount();
					System.out.println("No of records affected:" + count);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
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
