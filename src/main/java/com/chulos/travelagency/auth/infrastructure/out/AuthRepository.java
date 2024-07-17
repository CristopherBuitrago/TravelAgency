package com.chulos.travelagency.auth.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.auth.domain.service.AuthService;
import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.user.domain.entity.User;

public class AuthRepository implements AuthService{
    // attributes
    Connection connection = null;
    CallableStatement callableStatement = null;
    String response  = null;

    @Override
    public String login(Auth auth) {
        String sql = "{CALL login(?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setString(1,auth.getEmail());
            callableStatement.setString(2, auth.getPassword());
            // register out parameters
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            // execute the statement
            callableStatement.executeQuery();
            // get the response
            response = callableStatement.getString(3);

        } catch (SQLException e) {
            // if an error ocurred
            response = "Error in the database, make sure that the parameters are ok.";
        } finally{
            closeResources();
        }

        return response;
    }

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