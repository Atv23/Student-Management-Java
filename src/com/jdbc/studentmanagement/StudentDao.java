package com.jdbc.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class StudentDao {
	
	public static boolean InsertStudentToDB(Student st)
	{
		boolean flag=false;
		try {
			Connection con = CP.createC();
			
			String query= "insert into students(sname,sphone,scity) values(?,?,?)";
			
			//prepared statement
			PreparedStatement pstmt = con.prepareStatement(query);
			//set values to parameter
			pstmt.setString(1, st.getStudentName());
			pstmt.setString(2, st.getStudentPhone());
			pstmt.setString(3, st.getStudentCity());
			
			//execute
			pstmt.executeUpdate();
			flag= true;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
	}
	
	public static boolean DeleteStudentFromDB(int id) {
		
		boolean flag = false;
		String query= "delete from Students where sid=?";
		try {
			//create connection
			Connection con = CP.createC();
			//statement
			PreparedStatement pstmt = con.prepareStatement(query);
			//set values
			pstmt.setInt(1, id);
			//execute
			pstmt.executeUpdate();
			flag=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static void DisplayAllStudents() {
		
		String query = "Select * from Students;";
		try {
			//create connection
			Connection con = CP.createC();
			//statement
			Statement stmt = con.createStatement();
			//execute
			ResultSet rs = stmt.executeQuery(query);
			
			//display result
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString("sname");
				String phone = rs.getString(3);
				String city = rs.getString("scity");
				
				System.out.println("ID: " + id);
				System.out.println("Name: " + name);
				System.out.println("Phone No: " + phone);
				System.out.println("City: " + city);
				System.out.println("-------------------------------------------------");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean UpdateStudentInDB(int id, HashMap<String,Object> updates) {
		
		boolean flag= false;
		StringBuilder query = new StringBuilder();
		query.append("Update Students Set ");
		for(String col : updates.keySet()) {
			query.append(col).append("= ?, ");
		}
		query.deleteCharAt(query.length()-2); // last index holds space, 2nd last holds comma
		query.append("Where sid=?");
		
		try {
			//connection
			Connection con = CP.createC();
			//statement
			PreparedStatement pstmt = con.prepareStatement(query.toString());
			//set values of statement
			int idx=1;
			for(Object ob : updates.values()) {
				pstmt.setObject(idx++, ob);
			}
			pstmt.setInt(idx, id);
			//execute
			pstmt.executeUpdate();
			flag=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
