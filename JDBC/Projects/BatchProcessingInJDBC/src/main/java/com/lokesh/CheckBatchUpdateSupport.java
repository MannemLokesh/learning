package com.lokesh;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckBatchUpdateSupport {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/organization";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 1. Establish connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. Get DatabaseMetaData object
            DatabaseMetaData metaData = connection.getMetaData();

            // 3. Check if batch updates are supported
            boolean isBatchSupported = metaData.supportsBatchUpdates();

            // 4. Display the result
            if (isBatchSupported) {
                System.out.println("Batch updates are supported by the JDBC driver.");
            } else {
                System.out.println("Batch updates are NOT supported by the JDBC driver.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}
