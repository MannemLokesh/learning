package com.lokesh;

import java.io.*;
import java.sql.*;

public class ClobStoreExample {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Read the text file from the resources folder
            File file = new File("src/main/resources/store_file.txt");
            FileReader reader = new FileReader(file);

            // Prepare the SQL statement
            String sql = "INSERT INTO ClobExample (name, text_data) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setCharacterStream(2, reader, file.length());

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Text successfully stored as CLOB.");
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
