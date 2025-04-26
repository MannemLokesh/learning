package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRollbackTransactionExample {
	static final String MYSQL_URL = "jdbc:mysql://localhost/organization";
	static final String USER_NAME = "root";
	static final String PASSWORD = "root";
	static final String INSERT_QUERY1 = "INSERT INTO Employees (first, last, age) values('Jeevan', 'Rao', 35)";
	static final String INSERT_QUERY2 = "INSERT INTO Employees11 (first, last, age) values('Aditya', 'Chaube', 40)";
	static final String SELECT_QUERY = "SELECT id, age, first, last FROM Employees";

	public static void printResultSet(ResultSet rs) throws SQLException {
		// Ensure we start with first row
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
			// Open database connection
			conn = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD);
			System.out.println(" Connection established with organization database.");

			conn.setAutoCommit(false);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			System.out.println("Before inserting a row into Employees table");
			stmt.executeUpdate(INSERT_QUERY1);
			System.out.println("Inserted a row into Employees table");
			stmt.executeUpdate(INSERT_QUERY2);

			System.out.println("Before committing 2 inserts.");
			conn.commit();
			System.out.println(" After committing 2 inserts.");

			rs = stmt.executeQuery(SELECT_QUERY);
			System.out.println("List ALL records of Employees table..");

			printResultSet(rs);

			// Clean-up resources
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("If error has occured, discard changes");
			try {
				if (conn != null)
					conn.rollback();
				// Checking if records are in 'before-insert' state.
				rs = stmt.executeQuery(SELECT_QUERY);
				System.out.println("List ALL records of Employees table..");
				printResultSet(rs);
				// Clean-up resources
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
				System.out.println("If error has occured, discard changes");
				try {
					if (conn != null)
						conn.rollback();

					// Checking if records are in 'before-insert' state.
					rs = stmt.executeQuery(SELECT_QUERY);
					System.out.println("List ALL records of Employees table..");

					printResultSet(rs);
				} catch (SQLException se21) {
					se21.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
					} catch (SQLException se31) {
						se31.printStackTrace();
					}
					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se32) {
						se32.printStackTrace();
					}
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se33) {
						se33.printStackTrace();
					}
				}
			}
		}
	}
}