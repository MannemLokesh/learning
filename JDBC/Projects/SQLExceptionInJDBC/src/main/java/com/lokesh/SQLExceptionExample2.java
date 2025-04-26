package com.lokesh;

import java.sql.*;

public class SQLExceptionExample2 {

	static final String MYSQL_URL = "jdbc:mysql://localhost/organization";
	static final String USER_NAME = "root";
	static final String PASSWORD = "root";

	public static void main(String args[]) {
		try {
			Connection conn = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD);
			System.out.println("Connection to db established..");
			String query = "select * from employees";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Successfully executed query.");
			while (rs.next()) {
				System.out.println("Intentionally giving emp_id column name, when the correct "
						+ "one is: id. Giving this, to see exception handling.");
				System.out.println("ID: " + rs.getString("emp_id"));
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
            String sqlState = e.getSQLState();
            String errorMsg = e.getMessage();
            
            System.out.println("Error code: " + errorCode);
            System.out.println("SqlState: " + sqlState);
            System.out.println("Error Message: " + errorMsg);
            
			System.out.println("------------------------------------");
			e.printStackTrace();
		}
	}
}