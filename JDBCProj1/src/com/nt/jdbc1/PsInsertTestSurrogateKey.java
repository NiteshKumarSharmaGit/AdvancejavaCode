package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestSurrogateKey {
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(SNO_SEQ1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			int count = 0;
			if (sc != null) {
				System.out.println("Enter student count:");
				count = sc.nextInt();
			}
			// Register the driver and load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// Create the statement object
			if (con != null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			// Send and execute the query
			if (ps != null && sc != null) {
				for (int i = 1; i <= count; ++i) {
					// read each student value
					System.out.println("Enter " + i + " student details");
					System.out.println("Enter student name:");
					String name = sc.next();
					System.out.println("Enter student address:");
					String address = sc.next();
					System.out.println("Enter student avg:");
					float avg = sc.nextFloat();
					// set each student details in pre-compiled sql query params
					ps.setString(1, name);
					ps.setString(2, address);
					ps.setFloat(3, avg);
					// Execute pre-compiled query each time
					int result = ps.executeUpdate();
					// Process the execution result of pre-compiled sql query
					if (result == 0) {
						System.out.println("Recorsa are not inserted!");
					} else {
						System.out.println(i + " Students  record inserted.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
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
