package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "11111111";

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка при подключении к БД");
            throw new RuntimeException(e);
        }

        return connection;
    }
}
