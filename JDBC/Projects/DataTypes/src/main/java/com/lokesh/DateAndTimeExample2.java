package com.lokesh;

import java.sql.*;

public class DateAndTimeExample2 {
	public static void main(String[] args) {
		// JDBC URL, user name, and password
		String jdbcUrl = "jdbc:mysql://localhost:3306/organization";
		String username = "root";
		String password = "root";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Establish connection
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Insert data
			String insertQuery = "INSERT INTO DateTimeExample (event_date, event_time, event_timestamp) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertQuery);

			java.sql.Date sqlDate = java.sql.Date.valueOf("2024-12-04"); // YYYY-MM-DD
			java.sql.Time sqlTime = java.sql.Time.valueOf("14:30:00"); // HH:MM:SS
			java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf("2024-12-04 14:30:00"); // YYYY-MM-DD HH:MM:SS

			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setTime(2, sqlTime);
			preparedStatement.setTimestamp(3, sqlTimestamp);

			int rowsInserted = preparedStatement.executeUpdate();
			System.out.println("Rows inserted: " + rowsInserted);

			// Retrieve data
			String selectQuery = "SELECT id, event_date, event_time, event_timestamp FROM DateTimeExample";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);

			System.out.println("Retrieved Data:");
			System.out.printf("%-5s %-12s %-12s %-20s%n", "ID", "Date", "Time", "Timestamp");
			System.out.println("--------------------------------------------");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date eventDate = resultSet.getDate("event_date");
				Time eventTime = resultSet.getTime("event_time");
				Timestamp eventTimestamp = resultSet.getTimestamp("event_timestamp");

				System.out.printf("%-5d %-12s %-12s %-20s%n", id, eventDate, eventTime, eventTimestamp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources manually
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
