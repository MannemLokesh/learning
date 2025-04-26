package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementUpdateExample {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			PreparedStatement stmt = con.prepareStatement("UPDATE employee SET name=? WHERE id=?");
			
			stmt.setString(1, "Arul");
			stmt.setInt(2, 6);
			
			int count = stmt.executeUpdate();
			
			System.out.println(count + " records updated");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
