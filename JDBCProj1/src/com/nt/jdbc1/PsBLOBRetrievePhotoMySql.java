package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBLOBRetrievePhotoMySql {
	private static final String ARTIST_RETRIEVE_QUERY = "SELECT AID,NAME,ADDRS,PHOTO  FROM ARTIST_INFO WHERE AID=?";

	public static void main(String[] args) {
		// Load mysql driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Exception occurred while loading the jar!" + e);
		}
		// Read inputs
		try (Scanner sc = new Scanner(System.in)) {// try1
			int aid = 0;
			if (sc != null) {
				System.out.print("Enter artist id:");
				aid = sc.nextInt();
			}
			// Prepare connection and statement object
			try (Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root");
					PreparedStatement ps = con.prepareStatement(ARTIST_RETRIEVE_QUERY)) {
				// set query param
				if (ps != null) {
					ps.setInt(1, aid);
				}
				// Execute query
				try (ResultSet rs = ps.executeQuery()) {
					// process the result
					if (rs != null) {
						if (rs.next()) {
							aid = rs.getInt(1);
							String name = rs.getString(2);
							String addrs = rs.getString(3);
							System.out.println(aid + " " + name + " " + addrs);
							// get inputStream pointing to blob file
							try (InputStream is = rs.getBinaryStream(4)) {
								// create output stream pointing to destination
								OutputStream os = new FileOutputStream("retrieve-image.jpg");
								// Copy blob value to destination value
								IOUtils.copy(is, os);
								System.out.println("Blob valye retrieved and stored successfully in file!");
							}
						}
					} else {
						System.out.println("Record not found!");
					}
				}

			}

		} // try1
		catch (SQLException e) {
			System.out.println("Exception occurred while retrieving the data.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
