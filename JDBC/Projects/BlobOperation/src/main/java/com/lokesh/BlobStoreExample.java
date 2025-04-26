package com.lokesh;

import java.io.*;
import java.sql.*;

public class BlobStoreExample {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Read the file from the resources folder
            File file = new File("src/main/resources/store_image.jpg");
            FileInputStream inputStream = new FileInputStream(file);

            // Prepare the SQL statement
            String sql = "INSERT INTO BlobExample (name, file_data) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setBinaryStream(2, inputStream, (int) file.length());

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("File successfully stored as BLOB.");
            }

            inputStream.close();
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
