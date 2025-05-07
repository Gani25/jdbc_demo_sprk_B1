package com.sprk.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.sprk.connection.MyConnection;

public class TestMain {

	public static void main(String[] args) {
		try {
			Connection conn = MyConnection.getConnection();

			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
