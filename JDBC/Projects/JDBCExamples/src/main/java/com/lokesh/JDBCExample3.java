package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCExample3 {
	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		System.out.println("Connecting to a selected database...");
		Connection conn = null;
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection
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