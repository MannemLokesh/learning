package com.lokesh;
import java.sql.*;

public class DynamicTablePrinter {
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

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
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
