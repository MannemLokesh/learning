package com.lokesh;
import java.sql.*;

public class DatabaseMetaDataExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        Connection connection = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            DatabaseMetaData metaData = connection.getMetaData();

            // Fetch database details
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("JDBC Driver Name: " + metaData.getDriverName());
            System.out.println("Database URL: " + metaData.getURL());
            System.out.println("User Name: " + metaData.getUserName());
            System.out.println("Read-Only: " + metaData.isReadOnly());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
