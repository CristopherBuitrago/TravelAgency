package com.chulos.travelagency.auth.infrastructure.in.rol_controller;

import java.util.Scanner;

import com.chulos.travelagency.auth.domain.service.IMenuController;
import com.chulos.travelagency.plane.infrastructure.in.PlaneController;

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
            System.out.println("║ 3. Consultar Información de Avión         ║");
            System.out.println("║ 4. Consultar Información de Trayecto      ║");
            System.out.println("║ 5. Registrar Aeropuerto                   ║");
            System.out.println("║ 6. Consultar Información de Aeropuerto    ║");
            System.out.println("║ 7. Actualizar Información de Avión        ║");
            System.out.println("║ 8. Eliminar Avión                         ║");
            System.out.println("║ 9. Asignar Aeronave a Trayecto            ║");
            System.out.println("║ 10. Actualizar Información de Trayecto    ║");
            System.out.println("║ 11. Eliminar Trayecto                     ║");
            System.out.println("║ 12. Actualizar Información de Aeropuerto  ║");
            System.out.println("║ 13. Eliminar Aeropuerto                   ║");
            System.out.println("║ 14. Eliminar Trayecto                     ║");
            System.out.println("║ 15. Eliminar Trayecto                     ║");
            System.out.println("║ 16. Eliminar Trayecto                     ║");
            System.out.println("║ 17. Eliminar Trayecto                     ║");
            System.out.println("║ 18. Eliminar Trayecto                     ║");
            System.out.println("║ 19. Eliminar Trayecto                     ║");
            System.out.println("║ 20. Eliminar Trayecto                     ║");
            System.out.println("║ 21. Eliminar Trayecto                     ║");
            System.out.println("║ 22. Eliminar Trayecto                     ║");
            System.out.println("║ 23. Eliminar Trayecto                     ║");
            System.out.println("║ 24. Eliminar Trayecto                     ║");
            System.out.println("║ 25. Eliminar Trayecto                     ║");
            System.out.println("║ 26. Eliminar Trayecto                     ║");
            System.out.println("║ 27. Eliminar Trayecto                     ║");
            System.out.println("║ 28. Salir                                 ║");
            System.out.println("╚═══════════════════════════════════════════╝");
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
        } while (option != 28);
    }
}