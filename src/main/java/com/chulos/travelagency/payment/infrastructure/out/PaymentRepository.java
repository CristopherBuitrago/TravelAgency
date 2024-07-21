package com.chulos.travelagency.payment.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.payment.domain.entity.Payment;
import com.chulos.travelagency.payment.domain.service.PaymentService;

public class PaymentRepository implements PaymentService{
    // utils
    Connection connection = null;
    CallableStatement callableStatement = null;
    String response = null;

    @Override
    public String createEfectPayment(Payment payment) {
        String sql = "{CALL create_payment_efec(?,?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setDouble(1, payment.getPaymentAmount());
            callableStatement.setDate(2, new Date(payment.getPaymentDate().getTime()));
            callableStatement.setInt(3, payment.getCustomerId());
            callableStatement.setInt(4, payment.getPurchasedTrip());
            // register out parameters
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get the response
            response = callableStatement.getString(5);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        // return the response
        return response;
    }

    @Override
    public String createTcPayment(Payment payment) {
        String sql = "{CALL create_payment_tc(?,?,?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setString(1, payment.getCardNumber());
            callableStatement.setDouble(2, payment.getPaymentAmount());
            callableStatement.setDate(3, new Date(payment.getPaymentDate().getTime()));
            callableStatement.setInt(4, payment.getCustomerId());
            callableStatement.setInt(5, payment.getPurchasedTrip());
            // register out parameters
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get the response
            response = callableStatement.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        // return the response
        return response;
    }

    @Override
    public String createTdPayment(Payment payment) {
        String sql = "{CALL create_payment_td(?,?,?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setString(1, payment.getCardNumber());
            callableStatement.setDouble(2, payment.getPaymentAmount());
            callableStatement.setDate(3, new Date(payment.getPaymentDate().getTime()));
            callableStatement.setInt(4, payment.getCustomerId());
            callableStatement.setInt(5, payment.getPurchasedTrip());
            // register out parameters
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get the response
            response = callableStatement.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        // return the response
        return response;
    }

    private void closeResources () {
        try {
            if (connection != null) connection.close();
            if (callableStatement != null) callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
