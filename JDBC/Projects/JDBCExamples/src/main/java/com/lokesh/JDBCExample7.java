package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample7 {
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

			// SQL query to create a table
			String sql = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
					+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
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