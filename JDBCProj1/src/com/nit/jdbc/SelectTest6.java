package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(System.in);
			int deptNo = 0;
			System.out.print("Enter deptNo:");
			if (sc != null) {
				deptNo = sc.nextInt();
			}
			// Register the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create statement object
			if (con != null)
				st = con.createStatement();

			// Prepare the query
			String query = "SELECT DEPTNO,DNAME,LOC  FROM DEPT WHERE DEPTNO=" + deptNo;
			System.out.println("Query is:" + query);
			// send and execute the query in db s/w and get the result back to display
			if (st != null) {
				rs = st.executeQuery(query);
			}

			// Display the result
			if (rs != null) {
				if (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				} else {
					System.out.println("Records not found.!!!!");
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
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
