package com.nt.jdbc4;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetDemo {

	public static void main(String[] args) {
		try (OracleJDBCRowSet jrs = new OracleJDBCRowSet()) {
			jrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrs.setUsername("system");
			jrs.setPassword("NITESH");
			jrs.setCommand("SELECT * FROM STUDENT");
			jrs.execute();
			while (jrs.next()) {
				System.out.println(
						jrs.getInt(1) + " " + jrs.getString(2) + " " + jrs.getString(3) + " " + jrs.getFloat(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
