package com.chulos.travelagency.tripcrew.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.tripcrew.domain.service.CrewService;

public class CrewRepository implements CrewService{
    // attributes
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private String response = null;

    @Override
    public String selectFlight(int flightId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectFlight'");
    }

    @Override
    public String addPilot(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPilot'");
    }

    @Override
    public String addCopilot(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCopilot'");
    }

    @Override
    public String addAttendant(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAttendant'");
    }

    @Override
    public String addTechnical(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTechnical'");
    }

    @Override
    public List<Flight> getFlights() {
        String sql = "SELECT * FROM available_flights";
        List<Flight> availableFlights = new ArrayList<>();
        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the statement
            preparedStatement = connection.prepareStatement(sql);
            // execute the statement and save the data into a set
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Flight flight = new Flight();
                // get data
                flight.setId(resultSet.getInt("id"));
                flight.setConnectionNumber(resultSet.getInt("connection_number"));
                // add to list
                availableFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableFlights;
    }
    
}
