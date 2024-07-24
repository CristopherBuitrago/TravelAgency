package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.plane.infrastructure.in.PlaneController;

public class RolSalesAgentController implements IMenuController{
    private final Scanner scanner;

    public RolSalesAgentController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int option;
        do {
            System.out.println("\n╔═══════════════════════════════════════════╗");
            System.out.println(  "║              MENU SALES AGENT             ║");
            System.out.println(  "├═══════════════════════════════════════════┤");
            System.out.println(  "║ 1. Crear Reserva de Vuelo                 ║");
            System.out.println(  "║ 2. Consultar Información de Cliente       ║");
            System.out.println(  "║ 3. Consultar Reserva de Vuelo             ║");
            System.out.println(  "║ 4. Registrar Cliente                      ║");
            System.out.println(  "║ 5. Actualizar Información de CLiente      ║");
            System.out.println(  "║ 6. Eliminar Reserva de Vuelo              ║");
            System.out.println(  "║ 7. Consultar Información de Vuelo         ║");
            System.out.println(  "║ 8. Consultar Asignación de Tripulación    ║");
            System.out.println(  "║ 9. Consultar Escalas de un Trayecto       ║");
            System.out.println(  "║ 10. Consultar Tarifa de Vuelo             ║");
            System.out.println(  "║ 11. Consultar Tipo de Documento           ║");
            System.out.println(  "║ 12. Salir                                 ║");
            System.out.println(  "╚═══════════════════════════════════════════╝");
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
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 10:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 11:
                    System.out.println(" - Lógica no implementada aún");
                    break;
                case 12:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 12);
    }
}
