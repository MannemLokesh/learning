package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavepointExample2 {
	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";
	static final String QUERY = "SELECT id, first, last, age FROM Employees";
	static final String DELETE_QUERY = "DELETE FROM Employees WHERE ID = 8";

	public static void printResultSet(ResultSet rs) throws SQLException {
		// Ensure we start with the first row
		rs.beforeFirst();
		while (rs.next()) {
			// Display values
			System.out.print("ID: " + rs.getInt("id"));
			System.out.print(", Age: " + rs.getInt("age"));
			System.out.print(", First: " + rs.getString("first"));
			System.out.println(", Last: " + rs.getString("last"));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false); // Disable auto-commit mode

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// Create a savepoint object before executing the delete query
			Savepoint beforeDeleteSavepoint = conn.setSavepoint();
			stmt.executeUpdate(DELETE_QUERY);

			System.out.println("Table EMPLOYEES after executing DELETE_QUERY:");
			rs = stmt.executeQuery(QUERY);
			printResultSet(rs);

			// Rollback the changes after savepoint
			conn.rollback(beforeDeleteSavepoint);

			System.out.println("Table EMPLOYEES after rollback:");
			rs = stmt.executeQuery(QUERY);
			printResultSet(rs);

			// Commit if no issues (optional here)
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("Rolling back transaction due to an error...");
					conn.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
		} finally {
			// Clean up resources manually
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
	}
}
