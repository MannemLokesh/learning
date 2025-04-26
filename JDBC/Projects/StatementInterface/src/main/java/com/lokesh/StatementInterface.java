package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementInterface {
	public static void main(String[] args) {
		try {
			// Load and Register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// We can also use url like jdbc:mysql:///organization
			// If MySQL is running in localhost and default port 3306
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			
			System.out.println("Existing records in table");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4));
			}
			
			System.out.println("\nInserting the new record into the table");
			stmt.executeUpdate("INSERT INTO employee values (6, 'Arul', 'Senior Developer', 80000)");
			
			System.out.println("\nUpdating the record in table");
			stmt.executeUpdate("UPDATE employee SET salary=85000 WHERE id=6");
			
			System.out.println("\nDeleting the record in table");
			int result = stmt.executeUpdate("delete from employee where id=6"); 
			
			System.out.println(result + " records affected");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
