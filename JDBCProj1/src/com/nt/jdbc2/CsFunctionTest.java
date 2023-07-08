package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUDENT_DETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDRS OUT VARCHAR2 
) RETURN FLOAT AS 
PERCENTAGE FLOAT;
BEGIN
  SELECT SNAME,SADD,AVG INTO NAME,ADDRS,PERCENTAGE FROM STUDENT WHERE SNO=NO;
  RETURN PERCENTAGE;
END FX_GET_STUDENT_DETAILS_BY_NO;*/
public class CsFunctionTest {
	private static final String CALL_FX_QUERY = "{?= call FX_GET_STUDENT_DETAILS_BY_NO(?,?,?)}";

	public static void main(String[] args) {
		// read inputs
		int sno = 0;
		try (Scanner sc = new Scanner(System.in)) {
			if (sc != null) {
				System.out.println("Enter student number:");
				sno = sc.nextInt();
			}
			// Establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"NITESH")) {
				// create callable statement object
				CallableStatement cs = con.prepareCall(CALL_FX_QUERY);
				// Register OUT param
				if (cs != null) {
					cs.registerOutParameter(1, Types.FLOAT);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.VARCHAR);
				}
				// set values to IN param
				if (cs != null) {
					cs.setInt(2, sno);
				}
				// Execute pl/sql function
				if (cs != null) {
					cs.execute();
				}
				// Gather information from function
				if (cs != null) {
					System.out.println("Student name:" + cs.getString(3));
					System.out.println("Student address:" + cs.getString(4));
					System.out.println("Student avg:" + cs.getFloat(1));
				}

			} catch (SQLException e) {
				System.out.println("SQL excption occurred!");
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
