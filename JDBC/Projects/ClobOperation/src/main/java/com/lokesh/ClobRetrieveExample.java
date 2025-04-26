package com.lokesh;
import java.io.*;
import java.sql.*;

public class ClobRetrieveExample {

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
            String sql = "SELECT name, text_data FROM ClobExample WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1); // Assuming the ID of the text to retrieve is 1

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fileName = resultSet.getString("name");
                Reader reader = resultSet.getCharacterStream("text_data");

                // Save the text file to the resources folder
                File outputFile = new File("src/main/resources/retrieved_" + fileName);
                FileWriter writer = new FileWriter(outputFile);

                int character;
                while ((character = reader.read()) != -1) {
                    writer.write(character);
                }

                System.out.println("Text successfully retrieved and saved to resources folder.");
                writer.close();
                reader.close();
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
