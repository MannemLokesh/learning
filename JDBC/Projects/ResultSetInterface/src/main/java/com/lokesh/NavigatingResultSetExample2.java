package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NavigatingResultSetExample2 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

			// Move cursor to the third row.
			System.out.println("Moving cursor to the 3rd row...");
			rs.absolute(3);

			// Extract data from result set
			System.out.println("Displaying record of 3rd row...");
			System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Designation: "
					+ rs.getString("designation") + ", Salary: " + rs.getInt("salary"));

			// Move cursor to the first row.
			System.out.println("Moving cursor to the first row...");
			rs.first();

			// Extract data from result set
			System.out.println("Displaying record...");
			System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Designation: "
					+ rs.getString("designation") + ", Salary: " + rs.getInt("salary"));

			System.out.println("Moving cursor to the next row...");
			rs.next();

			// Extract data from result set
			System.out.println("Displaying record...");
			System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Designation: "
					+ rs.getString("designation") + ", Salary: " + rs.getInt("salary"));

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
