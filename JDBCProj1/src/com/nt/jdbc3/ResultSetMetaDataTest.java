package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");) {
			ResultSetMetaData rsmd = null;
			if (rs != null) {
				rsmd = rs.getMetaData();
			}
			//get column count
			if (rsmd != null) {
				int colCount = rsmd.getColumnCount();
				//get column name
				for (int i = 1; i <= colCount; ++i) {
					System.out.print(rsmd.getColumnName(i) + "  ");
				}
				System.out.println();
				//get column data type
				for (int i = 1; i <= colCount; ++i) {
					System.out.print(rsmd.getColumnTypeName(i) + "  ");
				}
				System.out.println();
				while (rs.next()) {
					for (int i = 1; i <= colCount; ++i) {
						System.out.print(rs.getString(i) + "  ");
					}
					System.out.println();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
