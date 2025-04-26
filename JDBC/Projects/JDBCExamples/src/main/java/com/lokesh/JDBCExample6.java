package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample6 {
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			// Execute SQL to drop the database
			String sql = "DROP DATABASE sample_db1";
			stmt.executeUpdate(sql);
			System.out.println("Database dropped successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close Statement
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			// Close Connection
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}