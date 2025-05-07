package com.sprk.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.sprk.connection.MyConnection;
import com.sprk.dao.StudentDAO;
import com.sprk.entity.Student;

public class UpdateMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int rollNo;
		System.out.println("Enter rollno of student to be updated: ");
		rollNo = sc.nextInt();

		try {
			StudentDAO dao = new StudentDAO(MyConnection.getConnection());
			Student existingStudent = dao.findByRollNo(rollNo);
			
			if(existingStudent == null) {
				System.out.println("Student with roll no = "+rollNo+" not exists in our database.");
				return ;
			}
			
			// Here we will write the logic of update
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
