package com.chulos.travelagency.payment.infrastructure.in;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.payment.application.CreateEfecPaymentUseCase;
import com.chulos.travelagency.payment.domain.entity.Payment;
import com.chulos.travelagency.utils.MyUtils;

public class CreateEfecPaymentView {
    // application and scanner
    private final CreateEfecPaymentUseCase createEfecPaymentUseCase;
    private final Scanner scanner;

    // constructor
    public CreateEfecPaymentView(CreateEfecPaymentUseCase createEfecPaymentUseCase, Scanner scanner) {
        this.createEfecPaymentUseCase = createEfecPaymentUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start () {
        while (true) {
            try {
                // intro message
                System.out.println("CREATE CASH PAYMENT");
                // get the data
                double paymentAmount = MyUtils.getDoubleInput("Input payment amount: ", scanner);
                Date paymentDate = MyUtils.getDateInput("Input payment date (yyyy-MM-dd): ", scanner);
                int customerId = MyUtils.getIntInput("Input customer id: ", scanner);
                int trip = MyUtils.getIntInput("Input the trip id: ", scanner);
                
                // create a new payment
                Payment payment = new Payment();
                // get the values to payment
                payment.setPaymentAmount(paymentAmount);
                payment.setPaymentDate(paymentDate);
                payment.setCustomerId(customerId);
                payment.setPurchasedTrip(trip);
                // create new payment and get the response
                String response = createEfecPaymentUseCase.execute(payment);
                // display the response
                MyUtils.displayMessageAndClearScreen(response, 3);
                // out of the cycle
                break;
                
            } catch (ParseException e) {
                MyUtils.displayMessageAndClearScreen("Invalid date format. Make sure you are utilize (yyyy-MM-dd) format. Please try again", 3);
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
    }
}