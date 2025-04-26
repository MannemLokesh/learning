package com.lokesh;

import java.sql.*;

//This class demonstrates use of INSERT..SELECT SQL, 
//where data is inserted in table using select from another table.
public class JDBCExample15 {
	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			// Data from students table (student id, first name) is inserted into
			// sampledb4(id, name)
			String ins_sel = "insert into sampletable(id, name) select studentid,"
					+ " firstname from students where studentid > 1004";

			stmt.executeUpdate(ins_sel);

			ResultSet rs = stmt.executeQuery("select * from sampletable ");

			System.out.println(
					"Displaying records of table sampletable/ Ids" + " greater than 1004 are from students table");
			System.out.println("--------------------------------------");

			while (rs.next()) {
				System.out.print("id: " + rs.getInt(1));
				System.out.println(" name: " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}