package com.lokesh;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPManager {
	public static HikariDataSource dataSource;
	public static HikariConfig config = null;

	// Set properties in constructor
	HikariCPManager() {
		config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost/organization");
		config.setUsername("root");
		config.setPassword("root");
		// Set maximum connection pool size
		config.setMaximumPoolSize(10);
		dataSource = new HikariDataSource(config);
	}

	public static Connection getPooledConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void close() {
		if (dataSource != null) {
			dataSource.close();
		}
	}
}