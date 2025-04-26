package com.lokesh;

import java.sql.*;

// This file demonstrates use of DROP TABLE IF EXISTS command
public class JDBCExample11 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String QUERY = "DROP TABLE IF EXISTS emptest";
			stmt.execute(QUERY);

			System.out.println("Table emptest dropped successfully.");
			System.out.println("----------------------------------------");
			ResultSet rs = stmt.executeQuery("show tables");
			System.out.println("List of tables");
			System.out.println("----------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}