package com.lokesh;

import java.sql.*;

//This class demonstrates use of SELECT DATABASE() command and SHOW TABLES
public class JDBCExample5 {

	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// Create a statement
			stmt = conn.createStatement();

			// Make TUTORIALSPOINT the current database
			stmt.executeUpdate("USE ORGANIZATION");

			// Check the currently selected database
			rs1 = stmt.executeQuery("SELECT DATABASE()");
			while (rs1.next()) {
				System.out.println("Current database: " + rs1.getString(1));
			}

			// Show tables in the current database
			rs2 = stmt.executeQuery("SHOW TABLES");
			System.out.println("List of tables in current database TUTORIALSPOINT");
			System.out.println("---------------------------------------------------");
			while (rs2.next()) {
				System.out.println(rs2.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close ResultSet rs2
			try {
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			// Close ResultSet rs1
			try {
				if (rs1 != null) {
					rs1.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

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
