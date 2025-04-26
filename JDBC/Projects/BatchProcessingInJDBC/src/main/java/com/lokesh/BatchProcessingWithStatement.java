package com.lokesh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingWithStatement {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/organization";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // 1. Establish connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. Disable auto-commit mode
            connection.setAutoCommit(false);

            // 3. Create Statement object
            statement = connection.createStatement();

            // 4. Add SQL commands to the batch for `employees` table
            statement.addBatch("INSERT INTO company (name, department, salary) VALUES ('John Doe', 'Engineering', 75000.00)");
            statement.addBatch("INSERT INTO company (name, department, salary) VALUES ('Jane Smith', 'Marketing', 65000.00)");

            // 6. Execute the batch
            int[] updateCounts = statement.executeBatch();

            // 7. Commit the transaction
            connection.commit();

            System.out.println("Batch executed successfully. Rows affected: " + updateCounts.length);

        } catch (SQLException e) {
            e.printStackTrace();

            // Rollback in case of an error
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
