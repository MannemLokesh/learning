package com.lokesh;

import java.sql.*;

// This class demonstrates use of selecting a database.
public class JDBCExample4 {
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

			// Query to show databases
			rs1 = stmt.executeQuery("SHOW DATABASES");
			System.out.println("DATABASES");
			System.out.println("-------------------------------------------");
			while (rs1.next()) {
				System.out.println(rs1.getString(1));
			}

			System.out.println("-------------------------------------------------------");

			// Select the database TUTORIALSPOINT
			stmt.executeUpdate("USE ORGANIZATION");

			// Query to select data from employees table
			rs2 = stmt.executeQuery("SELECT * FROM employees");
			System.out.println("Id of employees");
			while (rs2.next()) {
				System.out.println("id= " + rs2.getInt("id"));
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
