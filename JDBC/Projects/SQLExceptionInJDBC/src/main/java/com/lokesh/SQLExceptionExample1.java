package com.lokesh;

import java.sql.*;

public class SQLExceptionExample1 {
	static final String MYSQL_URL = "jdbc:mysql://localhost/organization";
	static final String USER_NAME = "root";
	static final String PASSWORD = "root";

	public static void main(String args[]) {
		try {
			Connection conn = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD);

			Statement stmt = conn.createStatement();
			// Giving incorrect table name to get Exceptions
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES10");

			while (rs.next()) {
				System.out.println("ID:" + rs.getInt("id"));
				System.out.println(", Age: " + rs.getInt("age"));
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			String sqlState = e.getSQLState();
			String errorMsg = e.getMessage();

			System.out.println("Error code: " + errorCode);
			System.out.println("SqlState: " + sqlState);
			System.out.println("Error Message: " + errorMsg);

			e.printStackTrace();
		}
	}
}