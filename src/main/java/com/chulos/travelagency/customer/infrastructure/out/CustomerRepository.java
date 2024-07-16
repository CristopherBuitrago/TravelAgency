package com.chulos.travelagency.customer.infrastructure.out;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.customer.domain.entity.Customer;
import com.chulos.travelagency.customer.domain.service.CustomerService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class CustomerRepository implements CustomerService{
    // attributes
    String response = null;
    Connection connection = null;
    CallableStatement callableStatement = null;

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
        }

        // return the response
        return response;
    }

    @Override
    public Customer findCustomerById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
