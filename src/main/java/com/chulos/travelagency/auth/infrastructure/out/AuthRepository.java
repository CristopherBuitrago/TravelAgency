package com.chulos.travelagency.auth.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.auth.domain.service.AuthService;
import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.utils.MyUtils;

public class AuthRepository implements AuthService{
    // attributes
    Connection connection = null;
    CallableStatement callableStatement = null;
    String response  = null;
    
    // =================================================
    // Logica LOGIN
    @Override
    public String login(Auth auth) {
        String sql = "{CALL login(?,?,?,?)}";
        String response;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Get connection
            connection = DatabaseConfig.getConnection();
            if (connection != null) {
                System.out.println("\n╭─────────────────────────────────────╮");
                System.out.println(  "│   Successful Database Connection!   │");
                System.out.println(  "╰─────────────────────────────────────╯");
            }
            // Prepare call
            callableStatement = connection.prepareCall(sql);
            // Set parameters
            callableStatement.setString(1, auth.getEmail());
            callableStatement.setString(2, auth.getPassword());
            // Register out parameters
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // Execute the statement
            callableStatement.execute();
            // Get the response
            response = callableStatement.getString(3);
            String role = callableStatement.getString(4);

            // Debugging messages
            // System.out.println("Response from DB: " + response);
            // System.out.println("Role from DB (raw): " + role);

            // Remove any leading/trailing whitespace
            if (role != null) {
                role = role.trim();
                // System.out.println("Role from DB (trimmed): " + role);

                switch (role) {
                    case "ADMIN":
                        MyUtils.displayMessageAndClearScreen("Login Successful!", 3);
                        System.out.println("╔═════════════════════════════════════════╗");
                        System.out.println("║              ADMINISTRATOR              ║");
                        System.out.println("╚═════════════════════════════════════════╝");

                        break;
                    case "MTECH":
                        MyUtils.displayMessageAndClearScreen("Login Successful!", 3);
                        System.out.println("╔══════════════════════════════════════════╗");
                        System.out.println("║          MAINTENANCE TECHNICIAN          ║");
                        System.out.println("╚══════════════════════════════════════════╝");

                        break;
                    case "SA":
                        MyUtils.displayMessageAndClearScreen("Login Successful!", 3);
                        System.out.println("╔═════════════════════════════════════════╗");
                        System.out.println("║               SALES AGENT               ║");
                        System.out.println("╚═════════════════════════════════════════╝");

                        break;
                    case "CUSTOMER":
                        MyUtils.displayMessageAndClearScreen("Login Successful!", 3);
                        System.out.println("╔══════════════════════════════════════════╗");
                        System.out.println("║                 CUSTOMER                 ║");
                        System.out.println("╚══════════════════════════════════════════╝");
                        
                        break;
                    default:
                        System.out.println("Unrecognized role.");
                        break;
                }
            } else {
                System.out.println("Incorrect credentials.");
            }

        } catch (SQLException e) {
            // If an error occurred
            response = "Error in the database, make sure that the parameters are ok.";
            e.printStackTrace();
        } finally {
            // Close resources
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    // =================================================================
    // Logica REGISTER
    @Override
    public String register(User user) {
        String sql = "{CALL register (?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getPassword());
            // register out parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get the response
            response = callableStatement.getString(4);
        } catch (Exception e) {
            // if an error ocurred
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    // Close resources method
    private void closeResources() {
        try {
            if (callableStatement != null) callableStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
