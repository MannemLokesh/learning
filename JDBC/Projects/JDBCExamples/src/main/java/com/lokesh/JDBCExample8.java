package com.lokesh;

import java.sql.*;

public class JDBCExample8 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String QUERY1 = "CREATE TEMPORARY TABLE EMPLOYEES_COPY SELECT * FROM EMPLOYEES";
			stmt.execute(QUERY1);
			String QUERY2 = "SELECT * FROM EMPLOYEES_COPY";
			ResultSet rs = stmt.executeQuery(QUERY2);

			while (rs.next()) {
				System.out.print("Id: " + rs.getInt("id"));
				System.out.print(" Age: " + rs.getInt("age"));
				System.out.print(" First: " + rs.getString("first"));
				System.out.println(" Last: " + rs.getString("last"));
				System.out.println("------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}