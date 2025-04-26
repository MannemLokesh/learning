package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavepointExample1 {
    static final String DB_URL = "jdbc:mysql://localhost/organization";
    static final String USER = "root";
    static final String PASS = "root";
    static final String QUERY = "SELECT id, first, last, age FROM Employees";
    static final String DELETE_QUERY = "DELETE FROM Employees WHERE ID = 8";
    static final String DELETE_QUERY_1 = "DELETE FROM Employees WHERE ID = 9";

    public static void printResultSet(ResultSet rs) throws SQLException {
        // Ensure we start with the first row
        rs.beforeFirst();
        while (rs.next()) {
            // Display values
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", Age: " + rs.getInt("age"));
            System.out.print(", First: " + rs.getString("first"));
            System.out.println(", Last: " + rs.getString("last"));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // Set auto-commit to false

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Fetch and display initial data
            rs = stmt.executeQuery(QUERY);
            System.out.println("List result set for reference....");
            printResultSet(rs);

            // Delete row with ID = 8 (set a savepoint first)
            Savepoint savepoint1 = conn.setSavepoint("ROWS_DELETED_1");
            System.out.println("Deleting row....");
            stmt.executeUpdate(DELETE_QUERY);

            // Roll back to savepoint1
            conn.rollback(savepoint1);
            System.out.println("Rolled back to savepoint1");

            // Delete row with ID = 9 (set another savepoint)
            Savepoint savepoint2 = conn.setSavepoint("ROWS_DELETED_2");
            System.out.println("Deleting row....");
            stmt.executeUpdate(DELETE_QUERY_1);

            // Fetch and display updated data
            rs = stmt.executeQuery(QUERY);
            System.out.println("List result set for reference....");
            printResultSet(rs);

            // Commit the changes (if needed)
            conn.commit();

        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.out.println("Rolling back transaction due to an error...");
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
