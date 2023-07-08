package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySQLDataTransferTest {
	private static final String MYSQL_INSERT_STUDENT = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String ORACLE_SELECT_STUDENT = "SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	public static void main(String[] args) {
		Connection oraCon = null;
		Connection mySqlCon = null;
		Statement st = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Register the drivers
			Class.forName("oracle.jdbc.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish the connections
			oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			mySqlCon = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root");
			// Create statement objects
			if (oraCon != null)
				st = oraCon.createStatement();

			if (mySqlCon != null)
				pst = mySqlCon.prepareStatement(MYSQL_INSERT_STUDENT);

			// Send and execute select query in oracle Db and get the resultSet object
			if (st != null)
				rs = st.executeQuery(ORACLE_SELECT_STUDENT);
			if (rs != null && pst != null) {
				while (rs.next()) {
					// gather each record from ResultSet
					int no = rs.getInt(1);
					String name = rs.getString(2);
					String addrs = rs.getString(3);
					float avg = rs.getFloat(4);
					// Set each record in query parameter of insert query of mysql table
					pst.setInt(1, no);
					pst.setString(2, name);
					pst.setString(3, addrs);
					pst.setFloat(4, avg);
					// Execute the query
					pst.executeUpdate();
				}
				System.out.println("Records are copied from oracle to mysql table successfully!");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Records are not copied from oracle to mysql!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem in app execution!");
		} finally {
			try {
				if (rs != null)
					rs.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (oraCon != null)
					oraCon.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (mySqlCon != null)
					mySqlCon.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

}
