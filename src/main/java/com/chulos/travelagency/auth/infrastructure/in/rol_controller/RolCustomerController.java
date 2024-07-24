package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.plane.infrastructure.in.PlaneController;

public class RolCustomerController implements IMenuController{
    private final Scanner scanner;

    public RolCustomerController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int option;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println(  "║               MENU CUSTOMER              ║");
            System.out.println(  "├══════════════════════════════════════════┤");
            System.out.println(  "║ 1. Buscar Vuelos                         ║");
            System.out.println(  "║ 2. Seleccionar Vuelo                     ║");
            System.out.println(  "║ 3. Añadir Pasajeros                      ║");
            System.out.println(  "║ 4. Seleccionar Asientos                  ║");
            System.out.println(  "║ 5. Realizar Pago                         ║");
            System.out.println(  "║ 6. Consultar Reserva de Vuelo            ║");
            System.out.println(  "║ 7. Cancelar Reserva de Vuelo             ║");
            System.out.println(  "║ 8. Modificar Reserva de Vuelo            ║");
            System.out.println(  "║ 9. Salir                                 ║");
            System.out.println(  "╚══════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    PlaneController planecontroller = new PlaneController(scanner);
                    planecontroller.run();

                case 2:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 3:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 4:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 5:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 6:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 7:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 8:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 9);
    }
}
