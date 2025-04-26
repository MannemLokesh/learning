package com.lokesh;

import java.sql.*;

// This class demonstrates use of UPDATE using Java PreparedStatement        
public class JDBCExample19 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String upd_qry = "update employees set age = ? where id =? ";
			PreparedStatement pstmt = conn.prepareStatement(upd_qry);
			pstmt.setInt(1, 36);
			pstmt.setInt(2, 3);
			pstmt.executeUpdate();

			String sel_qry = "select * from employees where id = 3";
			ResultSet rs = pstmt.executeQuery(sel_qry);

			System.out.println("Displaying updated record..");
			while (rs.next()) {
				System.out.print(" ID: " + rs.getInt(1));
				System.out.print(" FirstName: " + rs.getString(2));
				System.out.print(" LastName: " + rs.getString(3));
				System.out.println(" AGE: " + rs.getInt(4));
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}