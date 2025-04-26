package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample18 {
	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";
	static final String QUERY = "SELECT id, first, last, age FROM Registration";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// Execute update query
			String sql = "UPDATE Registration SET age = 30 WHERE id in (100, 101)";
			stmt.executeUpdate(sql);

			// Execute select query
			rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Age: " + rs.getInt("age"));
				System.out.print(", First: " + rs.getString("first"));
				System.out.println(", Last: " + rs.getString("last"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources in reverse order
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
