package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatingResultSetExample {

	public static void printResultSet(ResultSet rs) throws SQLException {
		// Ensure we start with first row
		rs.beforeFirst();
		while (rs.next()) {
			System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Designation: "
					+ rs.getString("designation") + ", Salary: " + rs.getInt("salary"));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

			System.out.println("List result set for reference....");
			printResultSet(rs);

			// Loop through result set and add 1000 in salary
			// Move to Before first position so while-loop works properly

			rs.beforeFirst();
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int newSalary = rs.getInt("salary") + 1000;
				rs.updateInt("salary", newSalary);
				rs.updateRow();
			}

			System.out.println("List result set showing new salary...");
			printResultSet(rs);

			// Insert a record into the table.
			System.out.println("Inserting a new record...");

			// Move to insert row and add column data with updateXXX()
			rs.moveToInsertRow();
			rs.updateInt("id", 6);
			rs.updateString("name", "Rocky");
			rs.updateString("designation", "Team Lead");
			rs.updateInt("salary", 95000);

			// Commit row
			rs.insertRow();

			System.out.println("List result set showing new set...");
			printResultSet(rs);

			// Deleting the added row.
			rs.last();
			rs.deleteRow();

			System.out.println("List result set showing row after deletion.");
			printResultSet(rs);

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
