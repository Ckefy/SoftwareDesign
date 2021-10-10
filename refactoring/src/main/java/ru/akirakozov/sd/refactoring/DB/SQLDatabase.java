package ru.akirakozov.sd.refactoring.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabase {
    private static final String DB_URL = "jdbc:sqlite:test.db";

    public static void createTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                           "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                           " NAME           TEXT    NOT NULL, " +
                           " PRICE          INT     NOT NULL)";
            statement.executeUpdate(query);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
