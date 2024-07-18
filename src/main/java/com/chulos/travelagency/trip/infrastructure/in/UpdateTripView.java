package com.chulos.travelagency.trip.infrastructure.in;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.trip.application.UpdateTripUseCase;
import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.utils.MyUtils;

public class UpdateTripView {
    // get application
    private final UpdateTripUseCase updateTripUseCase;
    private final Scanner scanner;

    public UpdateTripView(UpdateTripUseCase updateTripUseCase, Scanner scanner) {
        this.updateTripUseCase = updateTripUseCase;
        this.scanner = scanner;
    }

    public void start() {

        while (true) {
            try {
                // intro message
                System.out.println("UPDATE TRIP");
                // get data
                int id = MyUtils.getIntInput("Input trip id: ", scanner);
                Date date = MyUtils.getDateInput("Input new trip date (yyyy-MM-dd): ", scanner);
                double price = MyUtils.getDoubleInput("Input new price: ", scanner);
                int flightFareId = MyUtils.getIntInput("Input new flight fare id: ", scanner);
                // create new trip
                Trip trip = new Trip(id, date, price, flightFareId);
                // execute update and get response
                String response = updateTripUseCase.execute(trip);
                // display the response
                MyUtils.displayMessageAndClearScreen(response, 2);                
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! Only numbers are valid. Please try again", 2);
            } catch (ParseException e) {
                MyUtils.displayMessageAndClearScreen("Ups! Invalid date format. Please enter the date in yyyy-MM-dd format", 3);
            }
        }
    }
}
