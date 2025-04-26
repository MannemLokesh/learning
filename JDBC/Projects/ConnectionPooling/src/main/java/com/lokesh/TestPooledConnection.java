package com.lokesh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPooledConnection {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			HikariCPManager hcpm = new HikariCPManager();
			conn = hcpm.getPooledConnection();

			if (conn != null) {
				// Prepare statement
				String sql = "SELECT * FROM employees";
				pstmt = conn.prepareStatement(sql);

				// Execute query
				rs = pstmt.executeQuery();

				// Process and print results
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first");
					String lname = rs.getString("last");
					System.out.println("ID: " + id + ", First Name: " + fname + ",  Last Name:  " + lname);
				}
			} else {
				System.out.println("Error getting connection.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close all resources
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Close connection pool
		HikariCPManager.close();
	}
}