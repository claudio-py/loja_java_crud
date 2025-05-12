package com.loja.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConectorBD {
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String URL = System.getenv().getOrDefault("JDBC_URL", DEFAULT_URL);
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";
    private static Connection connection = null;
    private static int retries = 10;
    private static final int RETRY_DELAY = 10000; // 10 seconds

    // Singleton pattern for managing a single connection instance
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            while (retries > 0) {
                try {
                    System.out.println("Attempting to connect to the database...");
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("Connected to the database.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Database not ready, retrying in " + (RETRY_DELAY / 1000) + " seconds...");
                    try {
                        Thread.sleep(RETRY_DELAY);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    retries--;
                    if (retries == 0) {
                        System.err.println("Failed to connect after retries.");
                        throw e;
                    }
                }
            }
        }
        return connection;
    }

    public static PreparedStatement getPrepared(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static ResultSet getSelect(String sql) throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement.executeQuery(sql);
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}