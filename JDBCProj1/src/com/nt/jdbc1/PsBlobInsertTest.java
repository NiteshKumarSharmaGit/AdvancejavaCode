package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertTest {
	private static final String INSERT_ARTIS_QUERY = "INSERT INTO ARTIST_INFO VALUES(SID_SQL.NEXTVAL,?,?,?);";

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver class not loaded!!");
		}
		try (Scanner sc = new Scanner(System.in)) {
			// read inputs
			String name = null, addrs = null, photoLocation = null;
			if (sc != null) {
				System.out.print("Enter Artist name:");
				name = sc.next();
				System.out.print("Enter Artist address:");
				addrs = sc.next();
				System.out.print("Enter phtolocation:");
				photoLocation = sc.next();
			} // if
				// Create inputStream pointing photo file
			try (FileInputStream is = new FileInputStream(photoLocation)) {
				try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"NITESH")) {
					PreparedStatement ps = con.prepareStatement(INSERT_ARTIS_QUERY);
					// set values to query param
					if (ps != null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
						// ps.setBlob(3, is);
					}//if
					// Execute query
					int count = 0;
					if (ps != null)
						count = ps.executeUpdate();
					// Process the result
					if (count == 0)
						System.out.println("Data is not inserted to table!!!");
					else
						System.out.println("Data inserted successfully!");

				} // try-2
			} // try-3

		} // try-1
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem in record insertion!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main

}// class
