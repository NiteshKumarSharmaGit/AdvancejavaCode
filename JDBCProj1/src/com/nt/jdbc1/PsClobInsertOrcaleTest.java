package com.nt.jdbc1;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsertOrcaleTest {
	private static final String INSERT_JOBSEEKAR_QUERY = "INSERT INTO JOBSEEKAR_INFO VALUES(JSID_SEQ1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception e) {
			System.out.println("Exception occurred while loading the driver." + e);
		}
		try (Scanner sc = new Scanner(System.in)) {// try1
			// read inputs
			String name = null, addrs = null, resumeLocation = null;
			if (sc != null) {
				System.out.print("Enter jobseekar name:");
				name = sc.next();
				System.out.print("Enter jobseekar address:");
				addrs = sc.next();
				System.out.print("Enter resume location:");
				resumeLocation = sc.next();//.replaceAll("?", "");
			} // if
				// create reader stream pointing to photo location
			try (Reader reader = new FileReader(resumeLocation)) {// try2
				// Establish the connection and preparedStatement object
				try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"NITESH"); PreparedStatement ps = con.prepareStatement(INSERT_JOBSEEKAR_QUERY);) {// try3
					// set values to query param
					if (ps != null) {
						ps.setNString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
					}
					// Execute the query
					int count = 0;
					if (ps != null) {
						count=ps.executeUpdate();
					}
					// Process the result
					if (count == 0) {
						System.out.println("Record not inserted!");
					} else {
						System.out.println("Record inserted!");
					}

				} // try3

			} // try2

		} // try1
		catch (SQLException e) {
			System.out.println("Exception occurred while  inserting the  data!"+e);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
