package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample2 {
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

			// Execute a query
			String sql = "CREATE SCHEMA Sample_db1";
			stmt.executeUpdate(sql);
			System.out.println("Schema created successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
