package com.lokesh;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProcessingWithPreparedStatement {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/organization";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. Establish connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. Disable auto-commit mode
            connection.setAutoCommit(false);

            // 3. Prepare the SQL statement
            String sql = "INSERT INTO company (name, department, salary) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // 4. Add multiple SQL commands to the batch
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setString(2, "Engineering");
            preparedStatement.setBigDecimal(3, new BigDecimal("75000.00"));
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Jane Smith");
            preparedStatement.setString(2, "Marketing");
            preparedStatement.setBigDecimal(3, new BigDecimal("65000.00"));
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Emily Johnson");
            preparedStatement.setString(2, "HR");
            preparedStatement.setBigDecimal(3, new BigDecimal("55000.00"));
            preparedStatement.addBatch();

            // 5. Execute the batch
            int[] updateCounts = preparedStatement.executeBatch();

            // 6. Commit the transaction
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
                if (preparedStatement != null) {
                    preparedStatement.close();
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
