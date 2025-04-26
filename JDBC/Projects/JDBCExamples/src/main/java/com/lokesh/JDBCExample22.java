package com.lokesh;

import java.sql.*;

public class JDBCExample22 {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sel_qry = "select * from employees ";
	        String del_qry = "DELETE FROM employees ORDER BY age LIMIT 3 ";
	        Statement stmt = conn.createStatement();
	        
	        ResultSet rs = stmt.executeQuery(sel_qry);
	        System.out.println(" Displaying records before deletion ");
	        System.out.println(" ----------------------------------" );
	        showResults(rs);
	        
	        stmt.executeUpdate(del_qry);
	        System.out.println("Displaying records after deletion..");
	        System.out.println(" ----------------------------------" );
	        rs = stmt.executeQuery(sel_qry);
	        showResults(rs);
	        rs.close();
	        stmt.close();
	        conn.close();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void showResults(ResultSet res) {
		try {
			while(res.next()) {
				System.out.print("ID: " + res.getInt(1));
				System.out.print(", FirstName: " + res.getString(2));
				System.out.print(", LastName: " + res.getString(3));
				System.out.println(", AGE: " + res.getInt(4));
			}
			System.out.println(" ----------------------------------" );
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}