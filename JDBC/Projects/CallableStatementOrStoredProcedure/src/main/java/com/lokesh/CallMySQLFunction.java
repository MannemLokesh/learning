package com.lokesh;

import java.sql.*;

public class CallMySQLFunction {
	
    public static void main(String[] args) {
    	
        String jdbcURL = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        // Values to pass to the function
        double num1 = 10.5;
        double num2 = 20.3;

        // SQL to call the function
        String sql = "SELECT AddTwoNumbers(?, ?) AS Result";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Prepare the statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, num1);
            preparedStatement.setDouble(2, num2);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result
            if (resultSet.next()) {
                double result = resultSet.getDouble("Result");
                System.out.println("The sum of " + num1 + " and " + num2 + " is: " + result);
            }

        } catch (SQLException e) {
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