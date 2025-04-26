package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample13 {
	static final String DB_URL = "jdbc:mysql://localhost/organization";
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
			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO Registration VALUES (100, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration VALUES (101, 'Mahnaz', 'Fatma', 25)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration VALUES (102, 'Zaid', 'Khan', 30)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration VALUES (103, 'Sumit', 'Mittal', 28)";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
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
