package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseMetadataMySqlTest {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root")) {
			DatabaseMetaData dbms = con.getMetaData();
			if (dbms != null) {
				System.out.println("Db s/w name:" + dbms.getDatabaseProductName());
				System.out.println("Db s/w version:" + dbms.getDatabaseProductVersion());
				System.out.println("Jdbc driver name:" + dbms.getDriverName());
				System.out.println("jdbc driver version:" + dbms.getDriverVersion());
				System.out.println("All sql keywords:" + dbms.getSQLKeywords());
				System.out.println("All numeric functions:" + dbms.getNumericFunctions());
				System.out.println("All system function:" + dbms.getSystemFunctions());
				System.out.println("All string function:" + dbms.getStringFunctions());
				System.out.println("MAx chars in table name:" + dbms.getMaxTableNameLength());
				System.out.println("AMx tables in select query:" + dbms.getMaxTablesInSelect());
				System.out.println("Max row size:" + dbms.getMaxRowSize());
				System.out.println("Support pl/sql procedures:" + dbms.supportsStoredProcedures());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
