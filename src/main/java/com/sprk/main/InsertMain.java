package com.sprk.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.sprk.connection.MyConnection;
import com.sprk.dao.StudentDAO;
import com.sprk.entity.Student;

public class InsertMain {

	public static void main(String[] args) {
		String fName, lName, phone, email;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter First Name: ");
		fName = sc.nextLine();
		System.out.println("Enter Last Name: ");
		lName = sc.nextLine();
		System.out.println("Enter email: ");
		email = sc.nextLine();
		System.out.println("Enter phone: ");
		phone = sc.nextLine();

		Student student = new Student();
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setPhone(phone);
		student.setEmail(email);

		// DAO
		try {
			StudentDAO dao = new StudentDAO(MyConnection.getConnection());

			int result = dao.insertStudent(student);
			if (result > 0) {
				System.out.println("Student saved successfully");

			} else if (result == -1) {
				System.out.println("Student with " + student.getEmail() + " already exists");
			} else {
				System.out.println("Something Bad Happens!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
