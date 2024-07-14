package com.chulos.travelagency.user.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;
import com.chulos.travelagency.user.infrastructure.config.DatabaseConfig;

public class UserRepository implements UserService {
    // attributes
    Connection connection = null;
    CallableStatement callableStatement = null;

    // implementation of methods from UserService interface
    @Override
    public void createUser(User user) {
        String sql = "{CALL create_user(?,?,?,?,?)}";

        try{
            // establish connection
            connection = DatabaseConfig.getConnection();

            // prepare the call
            callableStatement = connection.prepareCall(sql);

            // config input parameters
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getPassword());

            // config out parameters
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

            // execute procedure
            callableStatement.execute();

            // get out parameter value
            String response = callableStatement.getString(4);
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(int id) {
        
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsers'");
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
