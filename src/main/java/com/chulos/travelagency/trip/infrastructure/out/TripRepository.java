package com.chulos.travelagency.trip.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.trip.domain.service.TripService;

public class TripRepository implements TripService{
    // attributes
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    @Override
    public String createTrip(Trip trip) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTrip'");
    }

    @Override
    public Trip findTripById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findTripById'");
    }

    @Override
    public String updateTrip(Trip trip) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTrip'");
    }

    @Override
    public String deleteTrip(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTrip'");
    }
    
}
