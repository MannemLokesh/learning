package com.lokesh;

import java.sql.*;

public class CallStoredProcedure {
	
    public static void main(String[] args) {
    	
        String jdbcURL = "jdbc:mysql://localhost:3306/organization";
        String username = "root";
        String password = "root";

        // Values to be inserted
        String name = "Alice Johnson";
        String department = "Finance";
        double salary = 68000.00;

        // SQL to call the stored procedure
        String sql = "{CALL InsertCompanyRecord(?, ?, ?)}";

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Prepare the callable statement
            callableStatement = connection.prepareCall(sql);

            // Set input parameters
            callableStatement.setString(1, name);
            callableStatement.setString(2, department);
            callableStatement.setDouble(3, salary);

            // Execute the stored procedure
            callableStatement.execute();
            System.out.println("Record inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources explicitly
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
