package com.jdbc.studentmanagement;

import java.sql.Connection; 
import java.sql.DriverManager;
 
public class CP {
	
	static Connection con;
	
	//made static so that no need to make object to call:- CP.createC()
	public static Connection createC() {
			
		try {
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//create connection
			String url="jdbc:mysql://localhost:3306/student_manage"; //student_manage is Database name
			String user="root";
			String password="atv@@2303";
			con = DriverManager.getConnection(url, user, password);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
