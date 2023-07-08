package com.nt.comons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ScrollableRSTestWithPropertiesFile {
	private static final String SELECT_QUERY = "SELECT * FROM EMP";

	public static void main(String[] args) {
		// working with properties file
		Properties props = null;
		try (InputStream is = new FileInputStream("src/com/nt/comons/jdbc.properties")) {
			props = new Properties();
			props.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery(SELECT_QUERY);) {
			if (rs != null) {
				System.out.println("RS record from tp to buttom");
				while (rs.next()) {
					System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
							+ rs.getString(3) + " " + rs.getFloat(4));
				}
				System.out.println("-----------------------------------------------------");
				System.out.println("RS record from buttom to top");
				rs.afterLast();
				while (rs.previous()) {
					System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
							+ rs.getString(3) + " " + rs.getFloat(4));
				}
				System.out.println("-----------------------------------------------------");
				rs.first();
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

				rs.last();
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

				rs.absolute(3);
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

				rs.absolute(-6);
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

				rs.relative(3);
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

				rs.relative(-6);
				System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getFloat(4));
				System.out.println("-----------------------------------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
