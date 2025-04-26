package com.lokesh;

import java.sql.*;

//This class demonstrates use of updating multiple columns with a single SQL command
public class JDBCExample20 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String upd_qry = "update employees set age = 50, first='Shahbaz' where id =1 ";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(upd_qry);

			String sel_qry = "select * from employees where id = 1";
			ResultSet rs = stmt.executeQuery(sel_qry);

			System.out.println("Displaying updated record..");
			while (rs.next()) {
				System.out.print(" ID: " + rs.getInt(1));
				System.out.print(", FirstName: " + rs.getString(2));
				System.out.print(", LastName: " + rs.getString(3));
				System.out.println(", AGE: " + rs.getInt(4));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}