package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DafaultResultSetExample {
	public static void main(String[] args) {
		try {
			// Load and Register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// We can also use url like jdbc:mysql:///organization
			// If MySQL is running in localhost and default port 3306
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			//Statement stmt = con.createStatement();
			Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
