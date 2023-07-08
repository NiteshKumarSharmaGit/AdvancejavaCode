package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest2 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		try {
			int eno = 0;
			String ename = null;
			String job = null;
			float sal = 0.0f;
			sc = new Scanner(System.in);
			System.out.println("Enter eno:");
			eno = sc.nextInt();
			System.out.println("Enter ename:");
			ename = sc.next();
			System.out.println("Enter job:");
			job = sc.next();
			System.out.println("Enter sal:");
			sal = sc.nextFloat();
			// Load jdbc driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Estabslish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			// create the statement object
			if (con != null) {
				st = con.createStatement();
			}
			// Prepare the query
			ename = "'" + ename + "'";
			job = "'" + job + "'";
			
			// Actual sql query
			String query = "INSERT INTO EMP  (EMPNO,ENAME,JOB,SAL) VALUES(" + eno + "," + ename + "," + job + "," + sal+ ")";
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
