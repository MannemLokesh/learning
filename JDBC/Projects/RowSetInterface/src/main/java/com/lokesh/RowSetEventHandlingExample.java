package com.lokesh;

import javax.sql.rowset.*;
import javax.sql.*;
import java.sql.*;

// This class demonsrates use of RowSetEvent and event-handling in RowSet
public class RowSetEventHandlingExample {

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
		rowSet.addRowSetListener(new CustomListener());

		while (rowSet.next()) {
			// Generating cursor Moved event
			System.out.print("Id: " + rowSet.getInt(1));
			System.out.print(" Age: " + rowSet.getInt("age"));
			System.out.print(" First: " + rowSet.getString("first"));
		}

		System.out.println("-------------------------------------------------");
		rowSet.absolute(3);
		rowSet.updateInt("age", 30);
		// Generating row changed event
		rowSet.updateRow();
	}
}

class CustomListener implements RowSetListener {
	public void cursorMoved(RowSetEvent evt) {
		System.out.println(" Cursor Moved...");
	}

	public void rowChanged(RowSetEvent evt) {
		System.out.println(" Row Changed...");
	}

	public void rowSetChanged(RowSetEvent evt) {
		System.out.println(" RowSet changed..");
	}
}