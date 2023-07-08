package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InSensitiveRSTest {
	private static final String SELECT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "NITESH");
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(SELECT_QUERY);
				){
			if(rs!=null) {
				System.out.println("RS record from top to buttom");
				int count=0;
				while(rs.next()) {
					if(count==0) 
							Thread.sleep(30000);
					count++;
					
					System.out.println(rs.getRow()+"--------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		
		

	}

}
