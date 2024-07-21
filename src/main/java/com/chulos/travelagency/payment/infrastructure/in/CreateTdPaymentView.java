package com.chulos.travelagency.payment.infrastructure.in;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.payment.application.CreateTdPaymentUseCase;
import com.chulos.travelagency.payment.domain.entity.Payment;
import com.chulos.travelagency.utils.MyUtils;

public class CreateTdPaymentView {
    // application and scanner
    private final CreateTdPaymentUseCase createTdPaymentUseCase;
    private final Scanner scanner;
    private static final int MAX_NUMBER_CARD = 10;

    // constructor
    public CreateTdPaymentView(CreateTdPaymentUseCase createTdPaymentUseCase, Scanner scanner) {
        this.createTdPaymentUseCase = createTdPaymentUseCase;
        this.scanner = scanner;
    }

    // start method
    public void start () {
        while (true) {
            try {
                // intro message
                System.out.println("CREATE DEBIT CARD PAYMENT");
                // get the data
                String cardNumber = MyUtils.getInput("Input card number(10): ", scanner);
                double paymentAmount = MyUtils.getDoubleInput("Input payment amount: ", scanner);
                Date paymentDate = MyUtils.getDateInput("Input payment date (yyyy-MM-dd): ", scanner);
                int customerId = MyUtils.getIntInput("Input customer id: ", scanner);
                int trip = MyUtils.getIntInput("Input the trip id: ", scanner);
                // verify if is input valid
                if (cardNumber.length() <= MAX_NUMBER_CARD) {
                    // if is valid, create a new payment
                    Payment payment = new Payment();
                    // get the values to payment
                    payment.setCardNumber(cardNumber);
                    payment.setPaymentAmount(paymentAmount);
                    payment.setPaymentDate(paymentDate);
                    payment.setCustomerId(customerId);
                    payment.setPurchasedTrip(trip);
                    // create new payment and get the response
                    String response = createTdPaymentUseCase.execute(payment);
                    // display the response
                    MyUtils.displayMessageAndClearScreen(response, 3);
                    // out of the cycle
                    break;
                } else {
                    // if is not an input valid show a message error
                    MyUtils.displayMessageAndClearScreen("Ups! it seems there are response that exceeds the limit characters. Please try again", 3);
                }
            } catch (ParseException e) {
                MyUtils.displayMessageAndClearScreen("Invalid date format. Make sure you are utilize (yyyy-MM-dd) format. Please try again", 3);
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the buffer
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
    }
}