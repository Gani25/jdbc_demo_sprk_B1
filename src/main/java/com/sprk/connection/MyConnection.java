package com.sprk.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	public static Connection getConnection() throws SQLException
	{
		String username ="root";
		String password ="root";
		String url = "jdbc:mysql://localhost:3306/sprk_jdbc_demo";

		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}

}
