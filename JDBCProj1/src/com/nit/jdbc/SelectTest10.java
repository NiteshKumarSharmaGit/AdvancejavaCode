package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest10 {

	public static void main(String[] args) {
		Scanner sc = null;
		int ranking = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(System.in);
			System.out.print("Enter the rank:");
			ranking = sc.nextInt();
			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// create the statement
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query
			String query = "SELECT SAL FROM (SELECT  SAL ,DENSE_RANK() OVER (ORDER BY SAL DESC) RANKING  FROM   EMP  )  WHERE RANKING ="
					+ ranking;
			System.out.println("Query is:" + query);
			// Send and execute the query
			if (st != null) {
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				if (rs.next())
					System.out.println(rs.getFloat(1));
				else {
					System.out.println("No records found");
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
			} catch (Exception e) {
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
