package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.customer.infrastructure.in.CustomerController;
import com.chulos.travelagency.tripbooking.infrastructure.in.TripBookingController;
import com.chulos.travelagency.utils.MyUtils;

public class RolSalesAgentController implements IMenuController {
    private final Scanner scanner;

    public RolSalesAgentController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int option = 0;
        do {
            try {
                System.out.println("\n╔═══════════════════════════════════════════╗");
                System.out.println("║              MENU SALES AGENT             ║");
                System.out.println("├═══════════════════════════════════════════┤");
                System.out.println("║ 1. Reservas de vuelo                      ║");
                System.out.println("║ 2. Seccion Clientes                       ║");
                System.out.println("║ 3. Salir                                  ║");
                System.out.println("╚═══════════════════════════════════════════╝");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        TripBookingController tripBookingController = new TripBookingController(scanner);
                        tripBookingController.run();
                        break;
                    case 2:
                        CustomerController customerController = new CustomerController(scanner);
                        customerController.run();
                        break;
                    case 3:
                        MyUtils.displayMessageAndClearScreen("Saliendo...", 2);
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                MyUtils.displayMessageAndClearScreen("Only numbers are valid", 2);
            } catch (IllegalStateException e) {
                System.out.println("ERROR: Scanner not exists.");
                return;
            } catch (NoSuchElementException e) {
                System.out.println("Error: No more input available");
                return;
            }

        } while (option != 3);
    }
}
