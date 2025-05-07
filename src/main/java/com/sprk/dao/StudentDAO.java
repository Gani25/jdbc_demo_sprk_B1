package com.sprk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.sprk.entity.Student;

public class StudentDAO {

	private Connection connection;

	// restriction
	public StudentDAO(Connection connection) {
		this.connection = connection;
	}

	// INSERT
	public int insertStudent(Student student) throws SQLException {

		// Find By Email
		Student existingStudent = findStudentByEmail(student.getEmail());

		if (existingStudent != null) {
			return -1;
		}

		// create sql statement
		String insertSql = "insert into student (first_name, last_name, email, phone) values (?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(insertSql);
		ps.setString(1, student.getFirstName());
		ps.setString(2, student.getLastName());
		ps.setString(3, student.getEmail());
		ps.setString(4, student.getPhone());

		int result = ps.executeUpdate();

		// CLOSE
		closeAll(connection, ps, null);

		return result;
	}

	public Student findStudentByEmail(String email) throws SQLException {
		Student student = null;
		PreparedStatement ps = connection.prepareStatement("select * from student where email = ?");

		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			student = new Student();
			student.setRollNo(rs.getInt("roll_no"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setEmail(rs.getString("email"));
			student.setPhone(rs.getString("phone"));
		}

		closeAll(connection, ps, rs);

		return student;
	}

	public List<Student> findAllStudent() throws SQLException {
		Student student = null;
		List<Student> studentsList = new LinkedList<>();
		PreparedStatement ps = connection.prepareStatement("select * from student");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			student = new Student();
			student.setRollNo(rs.getInt("roll_no"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setEmail(rs.getString("email"));
			student.setPhone(rs.getString("phone"));

			studentsList.add(student);
		}

		closeAll(connection, ps, rs);

		return studentsList;
	}

	public int deleteStudentByRollNo(int rollNo) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from student where roll_no = ?");
		ps.setInt(1, rollNo);

		int result = ps.executeUpdate();
		closeAll(connection, ps, null);
		return result;
	}
	
	
	// UPDATE
//		public int updateStudent(Student updatedStudent, int rollNo) throws SQLException {
//
//			
//
//			if (existingStudent != null) {
//				return -1;
//			}
//
//			// create sql statement
//			String insertSql = "insert into student (first_name, last_name, email, phone) values (?, ?, ?, ?)";
//
//			PreparedStatement ps = connection.prepareStatement(insertSql);
//			ps.setString(1, student.getFirstName());
//			ps.setString(2, student.getLastName());
//			ps.setString(3, student.getEmail());
//			ps.setString(4, student.getPhone());
//
//			int result = ps.executeUpdate();
//
//			// CLOSE
//			closeAll(connection, ps, null);
//
//			return result;
//		}
	

	private void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (conn != null) {
			conn.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}

	}

	public Student findByRollNo(int rollNo) throws SQLException {
		Student student = null;
		PreparedStatement ps = connection.prepareStatement("select * from student where roll_no = ?");

		ps.setInt(1, rollNo);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			student = new Student();
			student.setRollNo(rs.getInt("roll_no"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setEmail(rs.getString("email"));
			student.setPhone(rs.getString("phone"));
		}

		closeAll(connection, ps, rs);

		return student;
	}

}
