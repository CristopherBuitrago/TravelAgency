package com.chulos.travelagency.tripbooking.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.tripbooking.domain.entity.TripBooking;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;

public class TripBookingRepository implements TripBookingService{
    // attributes
    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    String response = null;

    @Override
    public String addBooking(TripBooking tripBooking) {
        String sql = "{CALL add_trip_booking(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, tripBooking.getCustomerId());
            callableStatement.setInt(2, tripBooking.getTripId());
            callableStatement.setDate(3, new Date(tripBooking.getReservationDate().getTime()));
            // config out parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        
        // return the answer
        return response;
    }

    @Override
    public String deleteBooking(int id) {
        String sql = "{CALL delete_trip_booking(?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, id);
            // register out parameter
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            // execute call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        // return response
        return response;
    }

    @Override
    public List<TripBooking> findBookingByCustomer(int customerId) {
        String sql = "{CALL find_booking_customer(?)}";
        List<TripBooking> bookings = new ArrayList<>();

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, customerId);
            // execute query and save it into a set
            resultSet = callableStatement.executeQuery();

            // verify if there are responses into the set
            while (resultSet.next()) {
                // get data if there are rows
                int id = resultSet.getInt("id");
                String name = resultSet.getString("customer");
                int tripId = resultSet.getInt("trip_id");
                double paymentAmount = resultSet.getDouble("payment_amount");
                Date bookingDate = resultSet.getDate("booking_date");
                // create new trip booking
                TripBooking newTripBooking = new TripBooking(id, name, tripId, paymentAmount, bookingDate);
                // add the trip into the list
                bookings.add(newTripBooking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        // return the bookings
        return bookings;
    }

    @Override
    public TripBooking findBookingById(int id) {
        String sql = "{CALL find_booking_id}";
        TripBooking newTripBooking = null;

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, id);
            // execute query and save it into a set
            resultSet = callableStatement.executeQuery();

            // verify if there are responses into the set
            if (resultSet.next()) {
                // get data if there are rows
                int idd = resultSet.getInt("id");
                String name = resultSet.getString("customer");
                int tripId = resultSet.getInt("trip_id");
                double paymentAmount = resultSet.getDouble("payment_amount");
                Date bookingDate = resultSet.getDate("booking_date");
                // create new trip booking
                newTripBooking = new TripBooking(idd, name, tripId, paymentAmount, bookingDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        // return the newTripBooking
        return newTripBooking;
    }

    @Override
    public String updateBooking(TripBooking tripBooking) {
        String sql = "{CALL update_trip_booking(?,?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the call
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, tripBooking.getId());
            callableStatement.setInt(2, tripBooking.getCustomerId());
            callableStatement.setInt(3, tripBooking.getTripId());
            callableStatement.setInt(4, tripBooking.getPaymentId());
            // register out parameters
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            // get response
            response = callableStatement.getString(5);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }
    
    // close the resources
    private void closeResources () {
        try {
            if (connection != null) connection.close();
            if (callableStatement != null) callableStatement.close();
            if (resultSet != null) callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}