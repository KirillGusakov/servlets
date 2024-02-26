package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private final static String URL_KEY = "db.url";
    private final static String USERNAME_KEY = "db.username";
    private final static String PASSWORD_KEY = "db.password";



    private ConnectionManager () {

    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection get () {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getKey(URL_KEY),
                    PropertiesUtil.getKey(USERNAME_KEY),
                    PropertiesUtil.getKey(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
