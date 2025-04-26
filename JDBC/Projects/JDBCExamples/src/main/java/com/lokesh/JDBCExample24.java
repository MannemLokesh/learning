package com.lokesh;

import java.sql.*;

public class JDBCExample24 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";
	static final String QUERY = "SELECT id, first, last, age FROM Registration";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			System.out.println("Fetching records without condition...");
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Age: " + rs.getInt("age"));
				System.out.print(", First: " + rs.getString("first"));
				System.out.println(", Last: " + rs.getString("last"));
			}

			// Select all records having ID equal or greater than 101
			System.out.println("Fetching records with condition...");
			String sql = "SELECT id, first, last, age FROM Registration" +
		                 " WHERE first LIKE '%za%'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Display values
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Age: " + rs.getInt("age"));
				System.out.print(", First: " + rs.getString("first"));
				System.out.println(", Last: " + rs.getString("last"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}