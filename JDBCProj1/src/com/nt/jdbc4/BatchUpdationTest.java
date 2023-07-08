package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
				Statement st = con.createStatement();) {
			// add queries to batch of non-select query
			st.addBatch("INSERT INTO STUDENT VALUES(6189,'ANIMESH','DELHI',67.98)");
			st.addBatch("UPDATE STUDENT SET AVG=AVG-40 WHERE SNO>=10000");
			st.addBatch("DELETE FROM STUDENT WHERE SNO<=10");
			// execute the batch
			int result[] = st.executeBatch();
			// process the result
			int sum = 0;
			for (int i = 0; i < result.length; ++i) {
				sum = sum + result[i];
			}
			System.out.println("Total no of row affetced is:" + sum);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
