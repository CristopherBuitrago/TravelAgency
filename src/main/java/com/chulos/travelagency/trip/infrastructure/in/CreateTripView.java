package com.chulos.travelagency.trip.infrastructure.in;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.trip.application.CreateTripUseCase;
import com.chulos.travelagency.trip.domain.entity.Trip;
import com.chulos.travelagency.utils.MyUtils;

public class CreateTripView {
    // use case and scanner
    private final CreateTripUseCase createTripUseCase;
    private final Scanner scanner;
    
    public CreateTripView(CreateTripUseCase createTripUseCase, Scanner scanner) {
        this.createTripUseCase = createTripUseCase;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            try {
                // show intro message
                System.out.println("CREATE TRIP");
                // get data
                Date date = getDateInput("Input trip date (yyyy-MM-dd): ");
                double price = getDoubleInput("Input trip price: ");
                int flightFareId = getIntInput("Input flight fare id: ");
                // create new trip
                Trip newTrip = new Trip(0, date, price, flightFareId);
                // create trip and get response
                String response = createTripUseCase.execute(newTrip);
                // display response
                MyUtils.displayMessageAndClearScreen(response, 2);
                break;
            } catch (ParseException e) {
                MyUtils.displayMessageAndClearScreen("Ups! Invalid date format. Please enter the date in yyyy-MM-dd format", 3);
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear buffer
                MyUtils.displayMessageAndClearScreen("Ups! Invalid answer, only numbers are valid. Please try again", 2);
            }
        }
    }

    private Date getDateInput(String prompt) throws ParseException{
        System.out.print(prompt);
        String input = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false); // Strict date parsing
        return formatter.parse(input);
    }

    private double getDoubleInput(String prompt) throws InputMismatchException{
        System.out.print(prompt);
        double input = scanner.nextDouble();
        scanner.nextLine(); // clear buffer
        return input;
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return input;
    }
}
