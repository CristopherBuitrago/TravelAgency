package com.chulos.travelagency.plane.infrastructure.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.chulos.travelagency.config.DatabaseConfig;
import com.chulos.travelagency.plane.domain.entity.Plane;
import com.chulos.travelagency.plane.domain.service.PlaneService;

public class PlaneRepository implements PlaneService {
    // Atributes
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    String response = null;

    // Implementacion of methods from PlaneService interface
    @Override
    public String createPlane(Plane plane) {
        String sql = "{CALL create_plane(?,?,?,?,?,?,?)}";

        try {
            // Connect to database
            connection = DatabaseConfig.getConnection();
            if (connection != null) {
                System.out.println("\n╭─────────────────────────────────────╮");
                System.out.println(  "│   Successful Database Connection!   │");
                System.out.println(  "╰─────────────────────────────────────╯");
            }
            // Prepare the call statement
            callableStatement = connection.prepareCall(sql);

            // Set parameters
            callableStatement.setString(1, plane.getPlate());
            callableStatement.setInt(2, plane.getChairs());
            callableStatement.setInt(3, plane.getStatus());
            callableStatement.setInt(4, plane.getModel());
            callableStatement.setDate(5, (Date) plane.getFabricationDate());
            callableStatement.setInt(6, plane.getAirline());

            // Register out parameters
            callableStatement.registerOutParameter(7, Types.VARCHAR);

            // Execute the call
            callableStatement.execute();

            // Get the result
            resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                response = "Avión creado correctamente";
            } else {
                response = "Error al crear el avión";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al crear el avión: " + e.getMessage();
        } finally {
            closeResources();
        }
        return response;
    }

    @Override
    public Plane findPlaneByPlate(String plate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPlaneByPlate'");
    }

    @Override
    public String updatePlane(Plane plane) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlane'");
    }

    @Override
    public String deletePlane(String plate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePlane'");
    }

    // Close resources method
    private void closeResources() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (callableStatement != null)
                callableStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
