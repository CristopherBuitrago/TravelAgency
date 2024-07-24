package com.chulos.travelagency.tripcrew.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.employee.domain.Employee;
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
        String sql = "{CALL select_flight(?,?)}";
        try {
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, flightId);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            // execute
            callableStatement.execute();
            // get response
            response = callableStatement.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public String addPilot(int employeeId, int flightId) {
        String sql = "{CALL add_employee_flight(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            //set parameters
            callableStatement.setInt(1, 1);
            callableStatement.setInt(2, employeeId);
            callableStatement.setInt(3, flightId);
            // register our parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public String addCopilot(int employeeId, int flightId) {
        String sql = "{CALL add_employee_flight(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            //set parameters
            callableStatement.setInt(1, 2);
            callableStatement.setInt(2, employeeId);
            callableStatement.setInt(3, flightId);
            // register our parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public String addAttendant(int employeeId, int flightId) {
        String sql = "{CALL add_employee_flight(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            //set parameters
            callableStatement.setInt(1, 4);
            callableStatement.setInt(2, employeeId);
            callableStatement.setInt(3, flightId);
            // register our parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
    }

    @Override
    public String addTechnical(int employeeId, int flightId) {
        String sql = "{CALL add_employee_flight(?,?,?,?)}";

        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            callableStatement = connection.prepareCall(sql);
            //set parameters
            callableStatement.setInt(1, 6);
            callableStatement.setInt(2, employeeId);
            callableStatement.setInt(3, flightId);
            // register our parameters
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            // execute the call
            callableStatement.execute();
            // get response
            response = callableStatement.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return response;
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
        } finally {
            closeResources();
        }

        return availableFlights;
    }
    
    private void closeResources () {
        try {
            if (connection != null) connection.close();
            if (preparedStatement != null) preparedStatement.close();
            if (callableStatement != null) callableStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployees(int roleType) {
        String sql = "{CALL get_employees_by_role(?)}";
        List<Employee> availableEmployees = new ArrayList<>();
        try {
            // get connection
            connection = DatabaseConfig.getConnection();
            // prepare the statement
            callableStatement = connection.prepareCall(sql);
            // set parameters
            callableStatement.setInt(1, roleType);
            // execute the statement and save the data into a set
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                // get data
                employee.setId(resultSet.getInt("id"));
                employee.setFullName(resultSet.getString("employee"));
                employee.setRole(resultSet.getString("role"));
                // add to list
                availableEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return availableEmployees;
    }
 
}
