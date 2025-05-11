package com.loja.model.util;
//
// Classe SequenceManager, que terá o método getValue, recebendo o nome da sequência como parâmetro e retornando o próximo valor.

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SequenceManager {
    private static final String SQL_GET_VALUE = "SELECT NEXT VALUE FOR %s AS next_value";
    private static final String SEQUENCE_NAME = "dbo.sequencia";

    public static int getValue() {
        try (Connection connection = ConectorBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(SQL_GET_VALUE, SEQUENCE_NAME))) {
            if (resultSet.next()) {
                return resultSet.getInt("next_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // or throw an exception
    }
}
