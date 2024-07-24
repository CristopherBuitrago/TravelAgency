package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.chulos.travelagency.tripcrew.application.AddAttendantUseCase;
import com.chulos.travelagency.tripcrew.application.AddCopilotUseCase;
import com.chulos.travelagency.tripcrew.application.AddEngineerUseCase;
import com.chulos.travelagency.tripcrew.application.AddPilotUseCase;
import com.chulos.travelagency.tripcrew.application.GetEmployeesUseCase;
import com.chulos.travelagency.tripcrew.application.GetFlightsUseCase;
import com.chulos.travelagency.tripcrew.application.SelectFlightUseCase;
import com.chulos.travelagency.tripcrew.domain.service.CrewService;
import com.chulos.travelagency.tripcrew.infrastructure.out.CrewRepository;
import com.chulos.travelagency.utils.MyUtils;

public class CrewController {
    // get all views, scanner
    private final GetEmployeesView getEmployeesView;
    private final GetFlightsView getFlightsView;
    private final AddAttendantView addAttendantView;
    private final AddCopilotView addCopilotView;
    private final AddEngineerView addEngineerView;
    private final AddPilotView addPilotView;
    private final SelectFlightView selectFlightView;
    private final Scanner scanner;
    private static final int MAX_PILOTS = 1;
    private static final int MAX_COPILOTS = 1;
    private static final int MAX_ENGINEERS = 1;
    private static final int MAX_ATTENDANTS = 3;

    // instance variables for counts
    private int countPilot = 0;
    private int countCopilot = 0;
    private int countEngineer = 0;
    private int countAttendant = 0;

    // constructor
    public CrewController(Scanner scanner) {
        // get scanner
        this.scanner = scanner;
        // get the service
        CrewService crewService = new CrewRepository();
        // get applications
        GetEmployeesUseCase getEmployeesUseCase = new GetEmployeesUseCase(crewService);
        GetFlightsUseCase getFlightsUseCase = new GetFlightsUseCase(crewService);
        AddAttendantUseCase addAttendantUseCase = new AddAttendantUseCase(crewService);
        AddCopilotUseCase addCopilotUseCase = new AddCopilotUseCase(crewService);
        AddEngineerUseCase addEngineerUseCase = new AddEngineerUseCase(crewService);
        AddPilotUseCase addPilotUseCase = new AddPilotUseCase(crewService);
        SelectFlightUseCase selectFlightUseCase = new SelectFlightUseCase(crewService);
        // initialize views
        this.getEmployeesView = new GetEmployeesView(getEmployeesUseCase);
        this.getFlightsView = new GetFlightsView(getFlightsUseCase);
        this.addAttendantView = new AddAttendantView(getEmployeesView, addAttendantUseCase, scanner);
        this.addCopilotView = new AddCopilotView(getEmployeesView, addCopilotUseCase, scanner);
        this.addEngineerView = new AddEngineerView(getEmployeesView, addEngineerUseCase, scanner);
        this.addPilotView = new AddPilotView(getEmployeesView, addPilotUseCase, scanner);
        this.selectFlightView = new SelectFlightView(getFlightsView, selectFlightUseCase, scanner);
    }

    public void run(){
        while (true) {
            try {
                // begin of the application
                int option = showPrincipalMenu();
                
                switch (option) {
                    case 1:
                        // clear screen
                        MyUtils.clearScreen();
                        // get flight
                        int flightId = selectFlight();
                        // verify if flight is zero
                        if (flightId == 0) {
                            break;
                        }
                        
                        // verify if the counts are equals to maximums
                        while (!maxCounts()) {
                            try {
                                // option
                                int option2 = showAddsMenu();
                                processAddition(option2, flightId);

                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
                            }
                        }

                        if (maxCounts()) {
                            MyUtils.displayMessageAndClearScreen("Great! you have done the registration.", 2);
                            return;
                        }

                        break;
                    case 2:
                        MyUtils.displayMessageAndClearScreen("Exiting the application...", 2);
                        return;
                    default:
                        MyUtils.displayMessageAndClearScreen("Ups! please choose a valid option", 2);
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Ups! only numbers are valid. Please try again", 2);
            }
        }
    }

    private int showPrincipalMenu() throws InputMismatchException{
        // intro message
        System.out.println("TRIPULATION CREW");
        System.out.println("1. Choose a flight to begin adding");
        System.out.println("2. Exit");
        int choose = scanner.nextInt();
        scanner.nextLine();
        return choose;
    }

    private int showAddsMenu() throws InputMismatchException {
        System.out.println("WHAT DO YOU WANT TO ADD?");
        System.out.println("1. Pilot");
        System.out.println("2. Copilot");
        System.out.println("3. Attendant");
        System.out.println("4. Engineer");
        int choose = scanner.nextInt();
        scanner.nextLine();
        return choose;
    }

    private int selectFlight(){
        while (true) {
            // process to select a flight
            int response = selectFlightView.start();
            // return response
            return response;
        }
    }

    private boolean maxCounts() {
        return countPilot == MAX_PILOTS &&
               countCopilot == MAX_COPILOTS &&
               countAttendant == MAX_ATTENDANTS &&
               countEngineer == MAX_ENGINEERS;
    }

    private void processAddition(int option2, int flightId) {
        String responseMessage = "";
        switch (option2) {
            case 1:
                // verify if max pilot
                if (countPilot == MAX_PILOTS) {
                    MyUtils.displayMessageAndClearScreen("Ups! you have completed the pilot's limit", 2);
                    break;
                }
                MyUtils.clearScreen();
                responseMessage = addPilotView.start(flightId);
                if ("Pilot added successful!".equals(responseMessage)) {
                    countPilot += 1;
                }
                break;
            case 2:
                // verify if max c0pilot
                if (countCopilot == MAX_COPILOTS) {
                    MyUtils.displayMessageAndClearScreen("Ups! you have completed the copilot's limit", 2);
                    break;
                }
                MyUtils.clearScreen();
                responseMessage = addCopilotView.start(flightId);
                if ("Co-pilot added successful!".equals(responseMessage)) {
                    countCopilot += 1;
                }
                break;
            case 3:
                // verify if max attendant
                if (countAttendant == MAX_ATTENDANTS) {
                    MyUtils.displayMessageAndClearScreen("Ups! you have completed the attendant's limit", 2);
                    break;
                }
                MyUtils.clearScreen();
                responseMessage = addAttendantView.start(flightId);
                if ("Attendant added successful!".equals(responseMessage)) {
                    countAttendant += 1;
                }
                break;
            case 4:
                // verify if max engineer
                if (countEngineer == MAX_ENGINEERS) {
                    MyUtils.displayMessageAndClearScreen("Ups! you have completed the engineer's limit", 2);
                    break;
                }
                MyUtils.clearScreen();
                responseMessage = addEngineerView.start(flightId);
                if ("Engineer added successful!".equals(responseMessage)) {
                    countEngineer += 1;
                }
                break;
            default:
                responseMessage = "Ups! please choose a valid option";
                break;
        }
        MyUtils.displayMessageAndClearScreen(responseMessage, 2);
    }
}
