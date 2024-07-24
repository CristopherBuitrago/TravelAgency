package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.plane.infrastructure.in.PlaneController;

public class RolMaintenanceTechController implements IMenuController{
    private final Scanner scanner;

    public RolMaintenanceTechController(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int option;
        do {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println(  "║         MENU MAINTENANCE TECHNICIAN        ║");
            System.out.println(  "├════════════════════════════════════════════┤");
            System.out.println(  "║ 1. Registrar Revisión de Mantenimiento     ║");
            System.out.println(  "║ 2. Consultar Historial de Reviciones de Avión ║");
            System.out.println(  "║ 3. Actualizar Información de Revisión      ║");
            System.out.println(  "║ 4. Eliminar Revisión de Mantenimiento      ║");
            System.out.println(  "║ 5. Salir                                   ║");
            System.out.println(  "╚════════════════════════════════════════════╝");
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
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 5);
    }
}
