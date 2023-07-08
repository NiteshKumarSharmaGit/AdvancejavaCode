package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPS_BUNAME_INITIAL 
(
  INITIALCHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  OPEN DETAILS FOR 
    SELECT EMPNO,NAME,JOB,SAL FROM EMP WHERE NAME LIKE INITIALCHARS;
    
END P_GET_EMPS_BUNAME_INITIAL; */
public class CsProcedureCursorTest {
	private static final String CALL_PROCEDURE = "{CALL P_GET_EMPS_BUNAME_INITIAL(?,?)}";

	public static void main(String[] args) {
		// Read input param
		String initChars = null;
		try (Scanner sc = new Scanner(System.in)) {
			if (sc != null) {
				System.out.println("Enter initial chars:");
				initChars = sc.next() + "%";
			}
			// Establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"NITESH")) {
				// create callable statement object
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				// Register out params
				if (cs != null) {
					cs.registerOutParameter(2, OracleTypes.CURSOR);
				}
				// set in IN params
				if (cs != null) {
					cs.setString(1, initChars);
				}
				// Execute the query
				if (cs != null) {
					cs.execute();
				}
				// Gather result from OUT params
				if (cs != null) {
					ResultSet rs = (ResultSet) cs.getObject(2);
					boolean flag = false;
					while (rs.next()) {
						flag=true;
						System.out.println(
								rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
					}
					if (flag == false) {
						System.out.println("Recors not found!");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
