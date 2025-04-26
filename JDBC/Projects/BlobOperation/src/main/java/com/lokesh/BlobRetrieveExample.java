package com.lokesh;

import java.io.*;
import java.sql.*;

public class BlobRetrieveExample {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Prepare the SQL query
            String sql = "SELECT name, file_data FROM BlobExample WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1); // Assuming the ID of the file to retrieve is 1

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fileName = resultSet.getString("name");
                InputStream inputStream = resultSet.getBinaryStream("file_data");

                // Save the file to the resources folder
                File outputFile = new File("src/main/resources/retrieved_" + fileName);
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File successfully retrieved and saved to resources folder.");
                outputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
