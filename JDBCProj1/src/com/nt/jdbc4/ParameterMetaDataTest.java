package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
				PreparedStatement ps = con.prepareStatement("INSERT INTO STUDENT VLAUES(?,?,?,?)");) {
			// Create parameter meta data object
			ParameterMetaData psmd = ps.getParameterMetaData();
			if (psmd != null) {
				int paramCount = psmd.getParameterCount();
				for (int i = 1; i <= paramCount; ++i) {
					System.out.println("Parameter number:" + i);
					System.out.println("Parameter name mode:" + psmd.getParameterMode(i));
					System.out.println("paramentrer type mode:" + psmd.getParameterTypeName(i));
					System.out.println("Parameter is signed or not:" + psmd.isSigned(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
