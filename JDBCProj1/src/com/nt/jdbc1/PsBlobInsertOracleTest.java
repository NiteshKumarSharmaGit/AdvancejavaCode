package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.driver.OracleDriver;

/*CREATE TABLE "SYSTEM"."ARTIST_INFO" 
(	"AID" NUMBER(10,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE), 
	"ADDRS" VARCHAR2(20 BYTE), 
	"PHOTO" BLOB,  
	 CONSTRAINT "ARTIST_INFO_PK" PRIMARY KEY ("AID"))
	 
     CREATE SEQUENCE  "SYSTEM"."AID_SQL"  MINVALUE 1000 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1000 CACHE 20 NOORDER  NOCYCLE ;

	  */
//C:\\Users\\Nitesh\\Desktop\\Ronaldo.jpg

public class PsBlobInsertOracleTest {
   private static final String  INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO VALUES(AID_SQL.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver class not loaded!!");
		}
		try(Scanner sc=new Scanner(System.in)){
			//read inputs
			String name=null,addrs=null, photoLocation=null;
			if(sc!=null) {
				System.out.println("Enter artist name::");
				name=sc.next();
				System.out.println("Enter Artist addrss::");
				addrs=sc.next();
				System.out.println("Enter Artist Location::");
				photoLocation=sc.next();
			}//if
			//create InputStream pointing photo file
			try(FileInputStream is=new FileInputStream(photoLocation)){
				//establish the connection and prepared Statement object
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "NITESH");
				    PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY);	)	{
				//set values to query param
				if(ps!=null) {
					ps.setString(1,name);
					ps.setString(2, addrs);
					ps.setBinaryStream(3, is);
				}
				//execute the query
				int count=0;
				if(ps!=null) 
					count=ps.executeUpdate();
				
				//process the results
				if(count==0)
					  System.out.println("record not inserted");
				else
					System.out.println("Record inserted");
			}//try3
			}//try2
		}//try1
	  catch(SQLException se) {
		  se.printStackTrace();
		  System.out.println("Problem in record insertion");
	  }
       catch(Exception e) {
    	   e.printStackTrace();
       }
		
	}//main
}//class
