package com.chulos.travelagency;

import java.util.Scanner;

public class MyUtils {
    
    // hide the password
    public static String readPassword(Scanner scanner) {
        if (System.console() != null) {
            // Use Console.readPassword() if available (for secure password input)
            return new String(System.console().readPassword());
        } else {
            // Fallback to normal scanner input (less secure)
            return scanner.nextLine();
        }
    }

    // display message for seconds
    public static void displayMessageAndClearScreen(String message, int displayDurationSeconds) {
        System.out.println(message);

        // Start a new thread to handle message display and screen clearing
        new Thread(() -> {
            try {
                Thread.sleep(displayDurationSeconds * 1000);
                clearScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // clear screen
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Clear screen for Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Clear screen for Unix-like systems (including macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Error while clearing screen: " + e.getMessage());
        }
    }
}
