package com.lokesh;

import java.sql.*;

// This class demonstrates use of ORDER BY clause of the SELECT statement in SQL
public class JDBCExample17 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			// Example of ORDER BY
			String query1 = "select id, age, first, last from employees order by age asc;";
			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
				System.out.print(" ID: " + rs.getInt(1));
				System.out.print(" AGE: " + rs.getInt(2));
				System.out.print(" FirstName: " + rs.getString(3));
				System.out.println(" LastName: " + rs.getString(4));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
