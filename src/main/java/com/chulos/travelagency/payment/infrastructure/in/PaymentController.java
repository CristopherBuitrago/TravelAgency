package com.chulos.travelagency.payment.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.payment.application.CreateEfecPaymentUseCase;
import com.chulos.travelagency.payment.application.CreateTcPaymentUseCase;
import com.chulos.travelagency.payment.application.CreateTdPaymentUseCase;
import com.chulos.travelagency.payment.domain.service.PaymentService;
import com.chulos.travelagency.payment.infrastructure.out.PaymentRepository;
import com.chulos.travelagency.utils.MyUtils;

public class PaymentController {
    // get all views and scanner
    private final CreateTcPaymentView createTcPaymentView;
    private final CreateTdPaymentView createTdPaymentView;
    private final CreateEfecPaymentView createEfecPaymentView;
    private final Scanner scanner;

    // constructor
    public PaymentController(Scanner scanner) {
        // get scanner
        this.scanner = scanner;
        // get the service
        PaymentService paymentService = new PaymentRepository();
        // get the applications (use cases)
        CreateTcPaymentUseCase createTcPaymentUseCase = new CreateTcPaymentUseCase(paymentService);
        CreateTdPaymentUseCase createTdPaymentUseCase = new CreateTdPaymentUseCase(paymentService);
        CreateEfecPaymentUseCase createEfecPaymentUseCase = new CreateEfecPaymentUseCase(paymentService);
        // initialize the views
        this.createTcPaymentView = new CreateTcPaymentView(createTcPaymentUseCase, scanner);
        this.createTdPaymentView = new CreateTdPaymentView(createTdPaymentUseCase, scanner);
        this.createEfecPaymentView = new CreateEfecPaymentView(createEfecPaymentUseCase, scanner);
    }

    // run method
    public void run() {
        while (true) {
            try {
                // show menu
                int option = showMenu();

                switch (option) {
                    case 1:
                        MyUtils.clearScreen();
                        createTcPaymentView.start();
                        break;
                    case 2:
                        MyUtils.clearScreen();
                        createTdPaymentView.start();
                        break;
                    case 3:
                        MyUtils.clearScreen();
                        createEfecPaymentView.start();
                        break;
                    case 4:
                        MyUtils.displayMessageAndClearScreen("exiting the application...", 2);
                        return;
                    default:
                        MyUtils.displayMessageAndClearScreen("Ups! choose a valid option", option);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
    }

    // show menu
    private int showMenu () {
        System.out.println("PAYMENTS");
        System.out.println("1. Create payment with credit card");
        System.out.println("2. Create payment with debit card");
        System.out.println("3. Create payment with cash");
        System.out.println("4. Exit");
        int choose = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return choose;
    }
}
