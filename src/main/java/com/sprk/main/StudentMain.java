package com.sprk.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sprk.connection.MyConnection;
import com.sprk.dao.StudentDAO;
import com.sprk.entity.Student;

public class StudentMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			int choice = 0;
			do {

				System.out.println("Please select choices: ");
				System.out.println("1. Get All Student");
				System.out.println("2. Insert Student");
				System.out.println("3. Update Student");
				System.out.println("4. Delete Student");
				System.out.println("5. Exit");
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1:
					getAllStudent();
					break;
				case 2:
					insertStudent();
					break;
				case 3:
					updateStudent();
					break;
				case 4:
					deleteStudent();
					break;
				case 5:
					System.out.println("Thanks for using our student management system! VISIT AGAIN");
					break;
				default:
					System.out.println("Please select correct choice");
				}
			} while (choice != 5);

		} catch (

		Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	private static void deleteStudent() throws SQLException {
		Scanner sc = new Scanner(System.in);
		int rollNo;
		System.out.println("Enter roll no of student to be removed: ");
		rollNo = sc.nextInt();

		StudentDAO dao = new StudentDAO(MyConnection.getConnection());

		int result = dao.deleteStudentByRollNo(rollNo);

		if (result > 0) {
			System.out.println("Student with roll no = " + rollNo + " deleted successfully.");
		} else {
			System.out.println("Student with roll no = " + rollNo + " not exists in our database.");

		}

	}

	private static void updateStudent() throws SQLException {
		Scanner sc = new Scanner(System.in);

		int rollNo;
		System.out.println("Enter rollno of student to be updated: ");
		rollNo = sc.nextInt();
		sc.nextLine();

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

	}

	private static void insertStudent() throws SQLException {
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

		StudentDAO dao = new StudentDAO(MyConnection.getConnection());
		// Find By Email
		Student existingStudent = dao.findStudentByEmail(student.getEmail());

		if (existingStudent != null) {
			System.out.println("Student with email " + email + " already registered!");
		} else {
			StudentDAO dao2 = new StudentDAO(MyConnection.getConnection());
			int result = dao2.insertStudent(student);
			if (result > 0) {
				System.out.println("Student saved successfully");

			} else if (result == -1) {
				System.out.println("Student with " + student.getEmail() + " already exists");
			} else {
				System.out.println("Something Bad Happens!");
			}
		}

	}

	private static void getAllStudent() throws SQLException {

		StudentDAO dao = new StudentDAO(MyConnection.getConnection());

		List<Student> students = dao.findAllStudent();
		if (students.isEmpty()) {
			System.out.println("No Student Data Available");
		} else {
			for (Student tempStudent : students) {
				System.out.println(tempStudent);
			}
		}

	}

}
