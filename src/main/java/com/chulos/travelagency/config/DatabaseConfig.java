package com.chulos.travelagency.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // attributes
    private static final String URL = "jdbc:mysql://localhost:3306/TravelAgency";
    private static final String USER = "campus2023";
    private static final String PASSWORD = "campus2023";

    // method to get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}