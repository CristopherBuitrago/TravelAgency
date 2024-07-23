package com.chulos.travelagency.flight.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.flight.domain.service.FlightService;

public class FlightRepository implements FlightService{
    // utils & variables
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private String response = null;

    @Override
    public String createFlight(Flight flight) {
        String sql = "{CALL create_flight(?,?,?,?,?,?)}";
        
        try {
            // connect
            connection = DatabaseConfig.getConnection();
            // prepare
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, flight.getConnectionNumber());
            callableStatement.setInt(2, flight.getTripId());
            callableStatement.setInt(3, flight.getPlaneId());
            callableStatement.setTime(4, Time.valueOf(flight.getDepartureTime()));
            callableStatement.setTime(5, Time.valueOf(flight.getArrivalTime()));
            // register out parameters
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            // execute
            callableStatement.execute();
            // get response
            response = callableStatement.getString(6);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }
    
    private void closeResources() {
        try {
            if(connection != null) connection.close();
            if(callableStatement != null) callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
