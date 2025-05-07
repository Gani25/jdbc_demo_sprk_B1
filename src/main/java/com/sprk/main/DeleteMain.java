package com.sprk.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.sprk.connection.MyConnection;
import com.sprk.dao.StudentDAO;

public class DeleteMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int rollNo;
		System.out.println("Enter roll no of student to be removed: ");
		rollNo = sc.nextInt();
		
		try {
			StudentDAO dao = new StudentDAO(MyConnection.getConnection());
			
			int result = dao.deleteStudentByRollNo(rollNo);
			
			if(result > 0) {
				System.out.println("Student with roll no = "+rollNo + " deleted successfully.");
			}else {
				System.out.println("Student with roll no = "+rollNo + " not exists in our database.");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
