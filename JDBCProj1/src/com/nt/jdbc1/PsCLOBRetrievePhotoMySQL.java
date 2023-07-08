package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsCLOBRetrievePhotoMySQL {
	private static final String JS_RETRIEVE_QUERY = "SELECT JSID,JSNAME,JSADDRS,RESUME,PHOTO  FROM JOBSEEKAR_INFO WHERE JSID=?";

	public static void main(String[] args) {
		// Load mysql driver
		/*
		 * try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (Exception e) {
		 * System.out.println("Exception occurred while loading the jar!" + e); }
		 */
		// Read inputs
		try (Scanner sc = new Scanner(System.in)) {// try1
			int jsid = 0;
			if (sc != null) {
				System.out.print("Enter job id:");
				jsid = sc.nextInt();
			}
			// Prepare connection and statement object
			try (Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root");
					PreparedStatement ps = con.prepareStatement(JS_RETRIEVE_QUERY)) {
				// set query param
				if (ps != null) {
					ps.setInt(1, jsid);
				}
				// Execute query
				try (ResultSet rs = ps.executeQuery()) {
					// process the result
					if (rs != null) {
						if (rs.next()) {
							jsid = rs.getInt(1);
							String name = rs.getString(2);
							String addrs = rs.getString(3);
							System.out.println(jsid + " " + name + " " + addrs);
							// get Reader pointing to blob file
							try (Reader reader = rs.getCharacterStream(4);
									InputStream is = rs.getBinaryStream(5);
								// create output stream pointing to destination
								OutputStream os = new FileOutputStream("retrieve-image.jpg");
								Writer writer = new FileWriter("retrieve-resume.txt");){
								// Copy blob value to destination value
								IOUtils.copy(is, os);
								IOUtils.copy(reader, writer);
								System.out.println("Clob,Blob valye retrieved and stored successfully in file!");
							}
						}
					} else {
						System.out.println("Record not found!");
					}
				}

			}

		} // try1
		catch (SQLException e) {
			System.out.println("Exception occurred while retrieving the data."+e);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
