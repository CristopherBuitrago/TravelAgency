package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.customer.infrastructure.in.CustomerController;
import com.chulos.travelagency.flight.infrastructure.in.FlightController;
import com.chulos.travelagency.plane.infrastructure.in.PlaneController;
import com.chulos.travelagency.trip.infrastructure.in.TripController;
import com.chulos.travelagency.tripcrew.infrastructure.in.CrewController;

public class RolAdminController implements IMenuController {
    private final Scanner scanner;

    public RolAdminController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int option;
        do {
            System.out.println("\n╔═══════════════════════════════════════════╗");
            System.out.println("║             MENU ADMINISTRATOR            ║");
            System.out.println("├═══════════════════════════════════════════┤");
            System.out.println("║ 1. Registrar Avión                        ║");
            System.out.println("║ 2. Asignar Tripulación a Trayecto         ║");
            System.out.println("║ 3. Crear vuelo                            ║");
            System.out.println("║ 4. Sección Trayectos                      ║");
            System.out.println("║ 5. Sección Clientes                       ║");
            System.out.println("║ 6. Salir                                  ║");
            System.out.println("╚═══════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    PlaneController planecontroller = new PlaneController(scanner);
                    planecontroller.run();
                    break;
                case 2:
                    CrewController crewController = new CrewController(scanner);
                    crewController.run();
                    break;
                case 3:
                    FlightController flightController = new FlightController(scanner);
                    flightController.run();
                    break;
                case 4:
                    TripController tripController = new TripController(scanner);
                    tripController.run();
                    break;
                case 5:
                    CustomerController customerController = new CustomerController(scanner);
                    customerController.run();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 5);
    }
}