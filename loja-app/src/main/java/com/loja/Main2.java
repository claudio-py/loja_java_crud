package com.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main2 {
 private static final String DEFAULT_URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String URL = System.getenv().getOrDefault("JDBC_URL", DEFAULT_URL);
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        int retries = 10;

        while (retries > 0) {
            try {
                System.out.println("Attempting to connect to the database...");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database.");
                break;
            } catch (SQLException e) {
                System.out.println("Database not ready, retrying in 10 second...");
                Thread.sleep(10000);
                retries--;
                if (retries == 0) {
                    System.err.println("Failed to connect after retries.");
                    throw e;
                }
            }
        }

        Statement statement = connection.createStatement();
        String selectAllsql = "SELECT * FROM vw_PESSOA_Juridica";
        ResultSet resultSet = statement.executeQuery(selectAllsql);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("nome"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
