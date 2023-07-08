package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace PROCEDURE P_GET_EMP_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT VARCHAR2 
) AS 
BEGIN
  SELECT NAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
END;*/
public class CsProcedureEMPSTest {
	private static final String CALL_PROCEDURE = "{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";

	public static void main(String[] args) {
		// read inputs
		int eno = 0;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter enp no:");
			eno = sc.nextInt();
			// establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"NITESH")) {
				// Craete the callable statement object
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				// Register the OUT params
				if (cs != null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}
				// set values to IN params
				if (cs != null) {
					cs.setInt(1, eno);
				}
				// execute pl/sql function/procedure
				if (cs != null) {
					cs.execute();
				}
				// gather results from out parameters
				if (cs != null) {
					String name = cs.getString(2);
					String desg = cs.getString(3);
					float sal = cs.getFloat(4);
					System.out.println("Emp details are:" + name + " " + desg + " " + sal);
				}
			} catch (SQLException se) {
				System.out.println("Requested data is not available!");
				se.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
