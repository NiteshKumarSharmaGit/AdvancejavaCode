package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This class tells to retrive data from DB whrn any name consists any letter
 * given dynamically by user
 * 
 * @author Nitesh
 *
 */
public class SelectTest5 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// read inputs
			sc = new Scanner(System.in);
			String initChars = null;
			if (sc != null) {
				System.out.print("Enetr initial characters of Employee:");
				initChars = sc.next();// gives s
			}
			// conver initchars as query form
			initChars = "'" + initChars + "%'";// gives 's%'
			// register jdbc driver by loding Oracle driver class
			Class.forName("oracle.jdbc.OracleDriver");
			// Get connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// create statement object
			st = con.createStatement();
			// Perpare the query
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE " + initChars;
			rs = st.executeQuery(query);
			if (rs != null) {
				boolean flag = false;
				while (rs.next() != false) {
					flag = true;
					System.out.println(
							rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
				}
				if (flag == false) {
					System.out.println("No recors found!..");
				}
			}
		} catch (SQLException e) {
			if (e.getErrorCode() >= 900 && e.getErrorCode() <= 999)
				System.out.println("Invalid SQL statement,query etc.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
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
