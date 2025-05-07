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
		sc.nextLine();

		try {
			StudentDAO dao = new StudentDAO(MyConnection.getConnection());
			Student existingStudent = dao.findByRollNo(rollNo);

			if (existingStudent == null) {
				System.out.println("Student with roll no = " + rollNo + " not exists in our database.");
				return;
			}

			// Here we will write the logic of update
			String fName, lName, phone, email;

			System.out.println("Enter Updated First Name: ");
			fName = sc.nextLine();
			System.out.println("Enter Updated Last Name: ");
			lName = sc.nextLine();
			System.out.println("Enter Updated email: ");
			email = sc.nextLine();
			System.out.println("Enter Updated phone: ");
			phone = sc.nextLine();

			existingStudent.setFirstName(fName);
			existingStudent.setLastName(lName);
			existingStudent.setPhone(phone);
			existingStudent.setEmail(email);
			
			StudentDAO dao2 = new StudentDAO(MyConnection.getConnection());

			int result = dao2.updateStudent(existingStudent);

			if (result > 0) {
				System.out.println("Student with roll no = " + rollNo + " updated successfully.");
			} else {
				System.out.println("Something bad happens");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
