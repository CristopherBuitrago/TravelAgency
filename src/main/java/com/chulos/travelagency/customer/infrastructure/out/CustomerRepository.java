package com.chulos.travelagency.customer.infrastructure.out;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.customer.domain.service.CustomerService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerRepository implements CustomerService{
    // attributes
    String response = null;
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    // database methods
    @Override
    public String createCustomer(Customer customer) {
        String sql = "{CALL create_customer(?,?,?,?,?,?)}";

        try {
            // stablish connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set the parameters
            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getLastName());
            callableStatement.setInt(3, customer.getAge());
            callableStatement.setString(4, customer.getDocumentType());
            callableStatement.setInt(5, customer.getDocumentNumber());
            // register out parameter
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            // execute the statement
            callableStatement.execute();
            // get response
            response = callableStatement.getString(6);
        } catch (Exception e) {
            // if any error ocurred print 
            e.printStackTrace();
            response = "An error ocurred";
        } finally {
            closeResources();
        }

        // return the response
        return response;
    }

    @Override
    public Customer findCustomerById(int id) {
        String sql = "{CALL find_customer(?)}";
        Customer customer = null;

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, id);
            // execute query
            resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                // create new customer
                customer = new Customer();
                // TODO: set values to customer
                
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
