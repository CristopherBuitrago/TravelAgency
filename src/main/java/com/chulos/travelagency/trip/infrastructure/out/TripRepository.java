package com.chulos.travelagency.trip.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.trip.domain.service.TripService;

public class TripRepository implements TripService{
    // attributes
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;
    private String response = null;

    @Override
    public String createTrip(Trip trip) {
        String sql = "{CALL create_trip(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setDate(1, new Date(trip.getDate().getTime()));
            callableStatement.setDouble(2,  trip.getPrice());
            callableStatement.setInt(3, trip.getFlightFareId());
            // register out parameter
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            response = "Error creating trip: " + e.getMessage();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public Trip findTripById(int id) {
        String sql = "{CALL find_trip(?)}";
        Trip newTrip = null;

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, id);
            // save result into a set
            resultSet = callableStatement.executeQuery();

            // verify if there are trip
            if (resultSet.next()) {
                // get columns value
                int key = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                Double price = resultSet.getDouble("price");
                String flightFare = resultSet.getString("flight_fare");
                // set the values into a new trip
                newTrip = new Trip(key, date, price, flightFare);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return newTrip;
    }

    @Override
    public String updateTrip(Trip trip) {
        String sql = "{CALL update_trip(?,?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters to call
            callableStatement.setInt(1, trip.getId());
            callableStatement.setDate(2, new Date(trip.getDate().getTime()));
            callableStatement.setDouble(3, trip.getPrice());
            callableStatement.setInt(4, trip.getFlightFareId());
            // register out parameter
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            // execute update
            callableStatement.executeUpdate();
            // get the response
            response = callableStatement.getString(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public String deleteTrip(int id) {
        String sql = "{CALL delete_trip(?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters to call
            callableStatement.setInt(1, id);
            // register out parameters
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            // execute the update
            callableStatement.executeUpdate();
            // get response
            response = callableStatement.getString(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }
    
    private void closeResources(){
        try {
            if (connection != null) connection.close();
            if (resultSet != null) resultSet.close();
            if (callableStatement != null) callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
