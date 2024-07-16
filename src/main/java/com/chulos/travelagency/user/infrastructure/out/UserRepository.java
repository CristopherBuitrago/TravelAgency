package com.chulos.travelagency.user.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;

public class UserRepository implements UserService {
    // Attributes
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    String response = null;

    // Implementation of methods from UserService interface
    @Override
    public String createUser(User user) {
        String sql = "{CALL create_user(?,?,?,?,?)}";

        try {
            // Establish connection
            connection = DatabaseConfig.getConnection();
            // Prepare the call
            callableStatement = connection.prepareCall(sql);
            // Configure input parameters
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getRoleCode());
            // config out parameters
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            // Execute procedure
            callableStatement.execute();
            // Set response
            response = callableStatement.getString(5);
        } catch (SQLException e) {
            e.printStackTrace();
            response = "An error occurred: " + e.getMessage();
        } finally {
            closeResources();
        }
        return response;
    }

    @Override
    public User findById(int id) {
        String sql = "{CALL find_user(?)}";
        User user = null;

        try {
            // Connect with database
            connection = DatabaseConfig.getConnection();
            // Prepare the callable statement
            callableStatement = connection.prepareCall(sql);
            // Set the parameter
            callableStatement.setInt(1, id);
            // Execute the query and save into a result set
            resultSet = callableStatement.executeQuery();

            // Verify if user was found
            if (resultSet.next()) {
                // Create new user
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleName(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        String sql = "{CALL get_users()}";
        List<User> users = new ArrayList<>();

        try {
            // Connect to database
            connection = DatabaseConfig.getConnection();
            // Prepare the call
            callableStatement = connection.prepareCall(sql);
            // Execute call and save result set
            resultSet = callableStatement.executeQuery();

            // Add users into a list
            while (resultSet.next()) {
                // Create new user
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleName(resultSet.getString("role"));
                // Add new user
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return users;
    }

    @Override
    public String updateUser(User user) {
        String sql = "{CALL update_user(?,?,?,?,?,?)}";

        try {
            // Get connection
            connection = DatabaseConfig.getConnection();
            // Prepare call
            callableStatement = connection.prepareCall(sql);
            // Set arguments to call
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getUsername());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getPassword());
            callableStatement.setString(5, user.getRoleCode());
            // config out parameters
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            // Execute the call
            callableStatement.executeUpdate();
            // Set response
            response = callableStatement.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
            response = "An error occurred: " + e.getMessage();
        } finally {
            closeResources();
        }
        return response;
    }

    @Override
    public String deleteUser(int id) {
        String sql = "{CALL delete_user(?,?)}";

        try {
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            // Set input parameter
            callableStatement.setInt(1, id);
            // Register out parameter
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            // Execute the call
            callableStatement.executeUpdate();
            // Get response from out parameter
            response = callableStatement.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
            response = "An error occurred: " + e.getMessage();
        } finally {
            closeResources();
        }
        return response;
    }

    // Close resources method
    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (callableStatement != null) callableStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
