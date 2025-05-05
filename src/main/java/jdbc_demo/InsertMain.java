package jdbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertMain {

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			// Step 1: Load Driver Class (Optional)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Establish Connection
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/sprk_jdbc_demo";

			conn = DriverManager.getConnection(url, username, password);

			// create sql query -> Statement Create
			String sql = "select * from student";
			ps = conn.prepareStatement(sql);

			// Execute Query -> Select Query And Non Select Query
			// Always for select query we need result set
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("---- Student Information ----");
				System.out.println("Roll No = " + rs.getInt(1));
				System.out.println("First Name = " + rs.getString("first_name"));
				System.out.println("Last Name = " + rs.getString("last_name"));
				System.out.println("Email = " + rs.getString("email"));
				System.out.println("Phone = " + rs.getString("phone"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Step 5 Close All
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
