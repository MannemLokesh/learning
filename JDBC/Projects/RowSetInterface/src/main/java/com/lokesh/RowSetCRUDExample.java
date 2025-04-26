package com.lokesh;

import javax.sql.rowset.*;
import java.sql.*;

// This class demonstrates use of basic functionality of rowsets
public class RowSetCRUDExample {

	static final String DB_URL = "jdbc:mysql://localhost/organization";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String args[]) throws SQLException {
		
		JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
		rowSet.setUrl(DB_URL);
		rowSet.setUsername(USER);
		rowSet.setPassword(PASS);
		rowSet.setCommand("select id, age, first, last from employees");
		rowSet.execute();

		while (rowSet.next()) {
			System.out.print("Id: " + rowSet.getInt("id"));
			System.out.print(" Age: " + rowSet.getInt("age"));
			System.out.print(" First: " + rowSet.getString("first"));
			System.out.println(" Last: " + rowSet.getString("last"));
		}

		System.out.println("---------------------------------------");
		// Position the cursor to the 3rd row
		rowSet.absolute(3);
		rowSet.updateInt("age", 20);
		rowSet.updateRow();

		// position the cursor to the last row
		rowSet.last();
		rowSet.deleteRow();

		// After updating the 3rd row and deleting the last row, doing a select to view
		// updated records.
		rowSet.setCommand("select id, age, first, last from employees");
		rowSet.execute();

		while (rowSet.next()) {
			System.out.print("Id: " + rowSet.getInt("id"));
			System.out.print(" Age: " + rowSet.getInt("age"));
			System.out.print(" First: " + rowSet.getString("first"));
			System.out.println(" Last: " + rowSet.getString("last"));
		}
	}
}