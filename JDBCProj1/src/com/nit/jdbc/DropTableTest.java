package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTableTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			// Load the driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			String query = " DROP TABLE TEMP_STUDENT";
			System.out.println("Query is:" + query);
			int count = 0;
			if (st != null) {
				count = st.executeUpdate(query);
				System.out.println("Count is:" + count);
			}
			if (count == 0)
				System.out.println("Table is altered");
			else
				System.out.println("table is not altered!");
		} catch (SQLException e) {
			e.printStackTrace();
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
		}

	}

}
