package com.sprk.main;

import java.sql.SQLException;
import java.util.List;

import com.sprk.connection.MyConnection;
import com.sprk.dao.StudentDAO;
import com.sprk.entity.Student;

public class AllStudentMain {

	public static void main(String[] args) {

		try {
			StudentDAO dao = new StudentDAO(MyConnection.getConnection());

			List<Student> students = dao.findAllStudent();
			if (students.isEmpty()) {
				System.out.println("No Student Data Available");
			} else {
				for (Student tempStudent : students) {
					System.out.println(tempStudent);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
