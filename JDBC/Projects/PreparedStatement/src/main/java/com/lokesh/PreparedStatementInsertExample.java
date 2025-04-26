package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementInsertExample {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			PreparedStatement stmt = con.prepareStatement("INSERT INTO employee values(?, ?, ?, ?)");
			
			stmt.setInt(1, 6);
			stmt.setString(2, "Rocky");
			stmt.setString(3, "Team Lead");
			stmt.setInt(4, 90000);
			
			int count = stmt.executeUpdate();
			
			System.out.println(count + " records inserted");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
