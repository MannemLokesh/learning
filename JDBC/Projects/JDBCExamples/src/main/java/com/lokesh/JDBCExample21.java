package com.lokesh;

import java.sql.*;

public class JDBCExample21 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";
	static final String QUERY = "SELECT id, first, last, age FROM Registration";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "DELETE FROM Registration WHERE id = 101";
			Statement stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
	        ResultSet rs = stmt.executeQuery(QUERY);
	        
	        while(rs.next()) {
	        	//Display values
	            System.out.print("ID: " + rs.getInt("id"));
	            System.out.print(", Age: " + rs.getInt("age"));
	            System.out.print(", First: " + rs.getString("first"));
	            System.out.println(", Last: " + rs.getString("last"));
	        }
	        
	        rs.close();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}