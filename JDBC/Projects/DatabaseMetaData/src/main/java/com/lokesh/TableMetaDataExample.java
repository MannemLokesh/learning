package com.lokesh;

import java.sql.*;

public class TableMetaDataExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        Connection connection = null;
        ResultSet tables = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            DatabaseMetaData metaData = connection.getMetaData();

            // Retrieve table metadata
            tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables in the database:");

            while (tables.next()) {
                System.out.println("Table Name: " + tables.getString("TABLE_NAME"));
                System.out.println("Table Type: " + tables.getString("TABLE_TYPE"));
                System.out.println("-----------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (tables != null) tables.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
