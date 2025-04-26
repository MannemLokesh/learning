package com.lokesh;

import java.sql.*;

//This class demonstrates the way of creating a table which is exactly similar to another table.        
public class JDBCExample9 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String QUERY1 = "CREATE TABLE EMPLOYEES_O LIKE EMPLOYEES";
			stmt.execute(QUERY1);

			System.out.println("Duplicate Table Created");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}