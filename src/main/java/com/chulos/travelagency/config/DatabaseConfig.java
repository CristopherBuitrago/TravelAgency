    package com.chulos.travelagency.config;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DatabaseConfig {
        // attributes
        private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:41941/TravelAgency";
        private static final String USER = "root";
        private static final String PASSWORD = "OJXhzGFHvvbZhUbgIcEpbptVOBBAOMaE";

        // method to get connection
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
