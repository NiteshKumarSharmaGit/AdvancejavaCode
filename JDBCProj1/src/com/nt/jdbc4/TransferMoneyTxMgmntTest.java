package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferMoneyTxMgmntTest {

	public static void main(String[] args) {
		long srcAccount = 0, destAccount = 0;
		double amount = 0.0;
		try (Scanner sc = new Scanner(System.in)) {
			if (sc != null) {
				System.out.print("Enter source account:");
				srcAccount = sc.nextLong();
				System.out.print("Enter destination account:");
				destAccount = sc.nextLong();
				System.out.print("Enter amount:");
				amount = sc.nextDouble();
			}
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
					Statement st = con.createStatement();) {
				// set auto commit false
				if (con != null) {
					con.setAutoCommit(false);
				}
				if (st != null) {
					// add queries to batch processing
					// for withdrawing operation
					st.addBatch("update JDBC_ACCOUNT set balance=balance-" + amount + "where acno=" + srcAccount);
					// for deposit operation
					st.addBatch("update JDBC_ACCOUNT set balance=balance+" + amount + "where acno=" + destAccount);
					// execute query
					int result[] = st.executeBatch();
					// processing the result
					boolean flag = true;
					for (int i = 0; i < result.length; ++i) {
						if (result[i] == 0) {
							flag = false;
							break;
						}
					}
					if (flag == true) {
						con.commit();// commit the txn
						System.out.println("Money transfered and transaction committed!");
					} else {
						con.rollback();// rollback the txn
						System.out.println("Money transfer failed and Transaction rolled back!");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
