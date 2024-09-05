package com.jdbc.studentmanagement;
import java.util.*;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Student Management App");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("CHOOSE ANY OPTION");
			System.out.println("Press 1 to ADD Student");
			System.out.println("Press 2 to Delete Student");
			System.out.println("Press 3 to Display Student");
			System.out.println("Press 4 to Update Student");
			System.out.println("Press 5 to Exit App");
			int ch = sc.nextInt();
			sc.nextLine();
			if(ch==1)
			{
				// Add student
				System.out.println("Enter User Name");
				String name = sc.nextLine();
				System.out.println("Enter User Phone");
				String phone = sc.nextLine();
				System.out.println("Enter User City");
				String city = sc.nextLine();
				
				Student st1 = new Student(name,phone,city);
				
				boolean ifupdated = StudentDao.InsertStudentToDB(st1);
				
				if(ifupdated)
					System.out.println("DB updated successfully!!");
				else
					System.out.println("Something went wrong!");
				System.out.println(st1);
				
			}
			else if(ch==2) {
				// Delete Student
				System.out.println("Enter the id of the student to delete:");
				int id = sc.nextInt();
				boolean ifDeleted = StudentDao.DeleteStudentFromDB(id);
				if(ifDeleted)
					System.out.println(id+" ID is deleted from the database");
				else
					System.out.println("Deletion Unsuccessful");
			}
			else if(ch==3) {
				//Display Student
				StudentDao.DisplayAllStudents();
			}
			else if(ch==4) {
				//update student
				System.out.println("Enter the id of the student to update:");
				int id = sc.nextInt();
				HashMap<String,Object> updates = new HashMap<>();
				//Edit name
				System.out.println("Do you want to edit Student Name? (y/n):");
				if(sc.next().charAt(0)=='y') 
				{
					System.out.println("Enter the updated Student Name:");
					updates.put("sname", sc.next());
				}
				//Edit Phone
				System.out.println("Do you want to edit Student Phone? (y/n):");
				if(sc.next().charAt(0)=='y') 
				{
					System.out.println("Enter the updated Student Phone:");
					updates.put("sphone", sc.next());
				}
				//Edit city
				System.out.println("Do you want to edit Student City? (y/n):");
				if(sc.next().charAt(0)=='y') 
				{
					System.out.println("Enter the updated Student Name:");
					updates.put("scity", sc.next());
				}
				
				boolean ifUpdated = StudentDao.UpdateStudentInDB(id,updates);
				if(ifUpdated)
					System.out.println(id + " ID Successfully Updated");
				else
					System.out.println("Updation Failed");
			}
			else if(ch==5) {
				break;
			}
			else {
				System.out.println("Wrong Choice, Try Again!!");
				continue;
			}
		}
		System.out.println("Thank You for using the application");
	}

}
