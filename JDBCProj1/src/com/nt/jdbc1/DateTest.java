package com.nt.jdbc1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;

public class DateTest {

	public static void main(String[] args) throws Exception {
		// Converting string date value into java.util.Date class object
		String s1 = "21-11-1990";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udt1 = sdf.parse(s1);
		System.out.println("String date is:" + s1);
		System.out.println("Util date is:" + udt1);

		// Converting java.util.Date class object into java.sql.Date

		long ms = udt1.getTime();
		java.sql.Date sdt1 = new java.sql.Date(ms);
		System.out.println("Util date is:" + udt1);
		System.out.println("Sql date is:" + sdt1);

		// If string date value is in dd-MM-yyyy pattern then it can be directly
		// converted into java.sql.Date object without converting java.util.Date
		String s2 = "1991-12-25";
		java.sql.Date sd2 = java.sql.Date.valueOf(s2);
		System.out.println("String date value is:" + s2);
		System.out.println("Sql date is:" + sd2);

	}

}
