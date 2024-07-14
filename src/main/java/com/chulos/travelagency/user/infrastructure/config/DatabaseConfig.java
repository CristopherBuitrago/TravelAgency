    package com.chulos.travelagency.user.infrastructure.config;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DatabaseConfig {
        // attributes
        private static final String URL = "jdbc:mysql://localhost:3306/travelagency";
        private static final String USER = "root";
        private static final String PASSWORD = "12345";

        // method to get connection
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
