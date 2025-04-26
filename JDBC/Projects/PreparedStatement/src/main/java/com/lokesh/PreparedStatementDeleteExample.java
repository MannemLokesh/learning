package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementDeleteExample {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			PreparedStatement stmt = con.prepareStatement("DELETE FROM employee WHERE id=?");

			stmt.setInt(1, 6);

			int count = stmt.executeUpdate();

			System.out.println(count + " records deleted");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
