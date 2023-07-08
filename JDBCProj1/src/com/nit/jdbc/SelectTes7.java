package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTes7 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// Register the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connetion
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query
			String query = "SELECT COUNT(*) FROM STUDENT";
			System.out.println("Query is:" + query);
			if (st != null) {
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				rs.next();
				//int count = rs.getInt(1);
				int count=rs.getInt("COUNT(*)");
				System.out.println("Record count in Db is:" + count);
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
		}

	}

}
