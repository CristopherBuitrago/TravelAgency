package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.tripbooking.application.DeleteBookingUseCase;
import com.chulos.travelagency.tripbooking.application.FindBookingByIdUseCase;
import com.chulos.travelagency.tripbooking.application.UpdateBookingUseCase;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;
import com.chulos.travelagency.tripbooking.infrastructure.in.DeleteBookingView;
import com.chulos.travelagency.tripbooking.infrastructure.in.FindBookingByIdView;
import com.chulos.travelagency.tripbooking.infrastructure.in.UpdateBookingView;
import com.chulos.travelagency.tripbooking.infrastructure.out.TripBookingRepository;
import com.chulos.travelagency.utils.MyUtils;

public class RolCustomerController implements IMenuController {
    private final Scanner scanner;

    public RolCustomerController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        TripBookingService tripBookingService = new TripBookingRepository();
        FindBookingByIdUseCase findBookingByIdUseCase = new FindBookingByIdUseCase(tripBookingService);
        int option = 0;
        do {
            try {
                System.out.println("\n╔══════════════════════════════════════════╗");
                System.out.println("║               MENU CUSTOMER              ║");
                System.out.println("├══════════════════════════════════════════┤");
                System.out.println("║ 1. Consultar Reserva de Vuelo            ║");
                System.out.println("║ 2. Cancelar Reserva de Vuelo             ║");
                System.out.println("║ 3. Modificar Reserva de Vuelo            ║");
                System.out.println("║ 4. Salir                                 ║");
                System.out.println("╚══════════════════════════════════════════╝");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        FindBookingByIdView findBookingByIdView = new FindBookingByIdView(findBookingByIdUseCase,
                                scanner);
                        findBookingByIdView.start();
                        break;
                    case 2:
                        DeleteBookingUseCase deleteBookingUseCase = new DeleteBookingUseCase(tripBookingService);
                        DeleteBookingView deleteBookingView = new DeleteBookingView(deleteBookingUseCase, scanner);
                        deleteBookingView.start();
                        break;
                    case 3:
                        UpdateBookingUseCase updateBookingUseCase = new UpdateBookingUseCase(tripBookingService);
                        UpdateBookingView updateBookingView = new UpdateBookingView(updateBookingUseCase, scanner);
                        updateBookingView.start();
                        break;
                    case 4:
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

        } while (option != 4);
    }
}
