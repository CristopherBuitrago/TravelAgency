package com.chulos.travelagency.flight.infrastructure.in;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.flight.application.CreateFlightUseCase;
import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.utils.MyUtils;

public class CreateFlightView {
    // get application
    private final CreateFlightUseCase createFlightUseCase;
    private final Scanner scanner;

    // constructor to get app and scanner
    public CreateFlightView(CreateFlightUseCase createFlightUseCase, Scanner scanner) {
        this.createFlightUseCase = createFlightUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start() {
        while (true) {
            try {
                //intro message
                System.out.println("CREATING FLIGHT");
                // get data
                int connectionNumber = MyUtils.getIntInput("Input connection number (10 digits): ", scanner);
                int tripId = MyUtils.getIntInput("Input trip id: ", scanner);
                int planeId = MyUtils.getIntInput("Input plane id: ", scanner);
                LocalTime departureTime = MyUtils.getTimeInput("Input departure time (HH:mm): ", scanner);
                LocalTime arrivalTime = MyUtils.getTimeInput("Input arrival time (HH:mm): ", scanner);
                // verify if is input valid 
                if (String.valueOf(connectionNumber).length() > 10) {
                    MyUtils.displayMessageAndClearScreen("Ups! connection number so long. Please try again", 2);
                    continue;
                }
                // create a new flight
                Flight flight = new Flight(0, connectionNumber, tripId, planeId, departureTime, arrivalTime, 0);
                // create flight and get response
                String response = createFlightUseCase.execute(flight);
                // display response
                MyUtils.displayMessageAndClearScreen(response, 2);
                // out the application
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            } catch (DateTimeParseException e) {
                MyUtils.displayMessageAndClearScreen("Ups! invalid format, please follow the (HH:mm) format", 2);
            }
        }
    }
}