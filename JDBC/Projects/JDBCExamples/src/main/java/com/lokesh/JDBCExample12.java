package com.lokesh;

import java.sql.*;

public class JDBCExample12 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sel_query = "select * from deptest";
			ResultSet rs1 = stmt.executeQuery(sel_query);
			System.out.println("Rows from deptest");
			System.out.println("--------------------");
			while (rs1.next()) {
				System.out.println("dept_id: " + rs1.getInt(1));
				System.out.println("dept_name: " + rs1.getString(2));
			}

			String QUERY = "TRUNCATE TABLE deptest";
			stmt.execute(QUERY);

			System.out.println("Table deptest rows successfully deleted.");
			System.out.println("----------------------------------------");
			System.out.println(" Doing a select on deptest...");
			rs1 = stmt.executeQuery(sel_query);
			if (!rs1.next()) {
				System.out.println(" **** ResultSet is empty *****");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}