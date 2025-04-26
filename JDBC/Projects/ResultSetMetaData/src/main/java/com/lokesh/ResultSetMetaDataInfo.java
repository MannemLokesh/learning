package com.lokesh;

import java.sql.*;

public class ResultSetMetaDataInfo {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        String query = "SELECT * FROM DateTimeExample";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // Get metadata
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("Column Metadata:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ":");
                System.out.println("  Name: " + metaData.getColumnName(i));
                System.out.println("  Type: " + metaData.getColumnTypeName(i));
                System.out.println("  Display Size: " + metaData.getColumnDisplaySize(i));
                System.out.println("  Nullable: " + (metaData.isNullable(i) == ResultSetMetaData.columnNullable ? "YES" : "NO"));
                System.out.println("  Auto Increment: " + metaData.isAutoIncrement(i));
                System.out.println("-----------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources explicitly
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
