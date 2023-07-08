package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableRSTest {
	private static final String SELECT_QUERY = "SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery(SELECT_QUERY);) {
			if (rs != null) {
				System.out.println("RS record from top to buttom");
				int count = 0;
				while (rs.next()) {
					System.out.println(rs.getRow() + "--------->" + rs.getInt(1) + " " + rs.getString(2) + " "
							+ rs.getString(3) + " " + rs.getFloat(4));
				}
				// Insert operation
				/*
				 * rs.moveToInsertRow(); rs.updateInt(1, 3475); rs.updateString(2, "Mahesh");
				 * rs.updateString(3, "Hyd"); rs.updateFloat(4, 44.67f); rs.insertRow();
				 * System.out.println("record inserted!");
				 */

				// Update operation
				/*
				 * rs.absolute(4); rs.updateFloat(4, 98.9f); rs.updateRow();
				 * System.out.println("Record updated!");
				 */
				// Delete row
				rs.absolute(3);
				rs.deleteRow();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
