package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewingResultSetExample {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			
			System.out.println("Viewing Records using column names");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("designation") + "\t" + rs.getInt("salary"));
			}
			
			rs.beforeFirst(); // Moving courser to initial stage
			
			System.out.println("\nViewing Records using column index");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4));
			}
			
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
