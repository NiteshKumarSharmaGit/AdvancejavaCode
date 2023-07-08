package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_AUTHENTICATE 
(
  USERNAME IN VARCHAR2 
, PASSWORD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
CNT NUMBER(5);
BEGIN
  SELECT COUNT(*)INTO CNT FROM IRCTC_TAB WHERE USER_ID='USERNAME' AND PASSWORD='PASSWORD';
  IF(CNT<>0)THEN
  RESULT:='VALID_CREDENTIALS';
  ELSE
  RESULT:='INVALID_CREDENTIAL';
  END IF; 
  
END ;*/
public class CsProcedureAuthTest {
	private static final String CALL_PROCEDURE = "{CALL P_AUTHENTICATE(?,?,?)}";

	public static void main(String[] args) {
		// Read input
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter username:");
			String uname = sc.next();
			System.out.println("Enter password:");
			String pwd = sc.next();
			// Establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH")) {
				// Create the callable statement
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				// Register the OUT param
				if (cs != null) {
					cs.registerOutParameter(3, Types.VARCHAR);
				}
				// set the IN param
				if (cs != null) {
					cs.setString(1, uname);
					cs.setNString(2, pwd);
				}
				// Execute the procedure
				if (cs != null) {
					cs.execute();
				}
				// Gather the result
				if (cs != null) {
					String result = cs.getString(3);
					System.out.println("Result is:" + result);
				}

			} catch (SQLException e) {
				System.out.println("Requested data are not available!");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
