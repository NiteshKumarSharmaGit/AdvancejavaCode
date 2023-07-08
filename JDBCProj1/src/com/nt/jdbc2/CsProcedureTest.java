package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 * create or replace procedure first_prox(x in number,y in number,z out number)
 * as 
 * begin 
 *  z:=x+y; 
 *   end;
 */
public class CsProcedureTest {
	private static final String CALL_PROCEDURE = "{CALL FIRST_PROX(?,?,?)}";

	public static void main(String[] args) {
		// Read inputs
		int first = 0, second = 0;
		try (Scanner sc = new Scanner(System.in)) {
			if (sc != null) {
				System.out.println("Enter first number:");
				first = sc.nextInt();
				System.out.println("ENter second number:");
				second = sc.nextInt();
			}

			// Establish the connection
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"NITESH")) {
				// Create callable statement object having pre-compiled query
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				// Register OUT param with jdbc data type
				if (cs != null) {
					cs.registerOutParameter(3, Types.INTEGER);
				}
				// set values to IN params
				if (cs != null) {
					cs.setInt(1, first);
					cs.setInt(2, second);

				}
				// Execute and call the plsql function
				if (cs != null) {
					cs.execute();
				}
				// Gather result from OUT param
				int result = 0;
				if (cs != null) {
					result = cs.getInt(3);
					System.out.println("Result is:" + result);
				}
			} // try2
			catch (SQLException e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
