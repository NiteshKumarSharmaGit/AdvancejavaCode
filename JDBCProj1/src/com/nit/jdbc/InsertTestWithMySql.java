package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestWithMySql {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			int sno = 0;
			String sname = null;
			String saddr = null;
			float avg = 0.0f;
			sc = new Scanner(System.in);
			System.out.println("Enter sno:");
			sno = sc.nextInt();
			System.out.println("Enter sname:");
			sname = sc.next();
			System.out.println("Enter sadddress:");
			saddr = sc.next();
			System.out.println("Enter avg value:");
			avg = sc.nextFloat();
			// Load jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Estabslish the connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root");
			// create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query
			sname = "'" + sname + "'";
			saddr = "'" + saddr + "'";
			// Actual sql query
			String query = "insert into student values(" + sno + "," + sname + "," + saddr + "," + avg + ")";
			System.out.println("Query is :" + query);
			int count = 0;
			if (st != null) {
				count=st.executeUpdate(query);
			}
			if (count == 0)
				System.out.println("No recors inserted into table!");
			else {
				System.out.println("No of record inserted into DB:" + count);
			}

		} catch (SQLException e) {
			if(e.getErrorCode()==1)
				System.out.println("Duplicate value can be inserted into PK column!");
			if(e.getErrorCode()==1400)
				System.out.println("No null value can be inserted into PK column!");
				if(e.getErrorCode()>=900 && e.getErrorCode()<=999)
						System.out.println("Invalid column or table name!");
				else if(e.getErrorCode()==12899)
					System.out.println("Do not insert more than column size!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
