package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsCLOBInsertMySQLTest {
	private static final String INSERT_JOBSEEKAR_QUERY = "INSERT INTO JOBSEEKAR_INFO(JSNAME,JSADDRS,RESUME,PHOTO)VALUES(?,?,?,?) ";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try(Scanner sc=new Scanner(System.in)) {//try1
			//read input values 
			String name=null,addrs=null,resumeLocation=null,photoLocation=null;
			if(sc!=null) {
				System.out.print("Enter jobseekar name:");
				name=sc.next();
				System.out.print("Enter jobseekar address:");
				addrs=sc.next();
				System.out.print("Enter resume location:");
				resumeLocation=sc.next();//.replaceAll("?", "");
				System.out.print("Enter photo location:");
				photoLocation=sc.next();//.replaceAll("?", "");			
			}
			//Create reader stream pointing to photo location
			try(Reader reader=new FileReader(resumeLocation);
					InputStream is=new FileInputStream(photoLocation)){//try2
				//Establish the connection and prepared statement object0
				try (Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB", "root", "root")){//try3
					PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKAR_QUERY);
					//Set values to to query param
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
						ps.setBinaryStream(4, is);
					}
					//Execute query
					int count=0;
					if(ps!=null) {
						count=ps.executeUpdate();
					}
					if(count==0) {
						System.out.println("Record not inserted!");
					}else {
						System.out.println("Record inserted!");
					}
				} //try3
				
			}//try2
			
		}//try1
		catch (SQLException e) {
			System.out.println("Error in record insertion!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}