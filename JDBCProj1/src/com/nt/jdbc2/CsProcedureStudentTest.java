package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_STUDENT_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDR OUT VARCHAR2 
, AVG OUT NUMBER 
) AS 
BEGIN
  SELECT SNAME,SADD,AVG INTO NAME,ADDR,AVG FROM STUDENT WHERE SNO=NO;
END ;*/
public class CsProcedureStudentTest {
	private static final String CALL_PROCEUDRE = "{CALL P_GET_STUDENT_DETAILS_BY_ID(?,?,?,?)}";

	public static void main(String[] args) {
		// read input
		int sno = 0;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter student number:");
			sno = sc.nextInt();
			// Establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"NITESH")) {
				// Create the callable statement
				CallableStatement cs = con.prepareCall(CALL_PROCEUDRE);
				// Register the out parameter
				if (cs != null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}
				// set the IN params
				if (cs != null) {
					cs.setInt(1, sno);
				}
				// execute the procedure
				if (cs != null) {
					cs.execute();
				}
				// Gather the result
				if (cs != null) {
					String sname = cs.getString(2);
					String saddr = cs.getString(3);
					float avg = cs.getFloat(4);
					System.out.println("Student result is:" + sname + " " + saddr + " " + avg);

				}
			} catch (SQLException e) {
				System.out.println("Requested data not available!");
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
