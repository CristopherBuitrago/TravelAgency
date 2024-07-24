package com.chulos.travelagency.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
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

    // Display message for seconds and clear screen
    public static void displayMessageAndClearScreen(String message, int displayDurationSeconds) {
        System.out.println(message);
        try {
            Thread.sleep(displayDurationSeconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clearScreen();
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

    // encrypt password
    public static String encryptPassword(String password, int shift) {
        // List of characters (extended to include uppercase, lowercase, and digits)
        char[] values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()-_+={}[]|:;'><,./?".toCharArray();
        StringBuilder encryptedPassword = new StringBuilder();

        // Encrypt each character in the password
        for (char c : password.toCharArray()) {
            int index = new String(values).indexOf(c);
            if (index == -1) {
                // If the character is not in the list, leave it unchanged
                encryptedPassword.append(c);
            } else {
                // Calculate the new index with the shift, wrapping around using modulo
                int newIndex = (index + shift) % values.length;
                encryptedPassword.append(values[newIndex]);
            }
        }

        return encryptedPassword.toString();
    }

    // method to get a date
    public static Date getDateInput(String prompt, Scanner scanner) throws ParseException{
        System.out.print(prompt);
        String input = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false); // Strict date parsing
        return formatter.parse(input);
    }

    // method to get a LocalDate
    public static LocalDate getLocalDateInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida. Por favor, use el formato yyyy-MM-dd.");
            return getLocalDateInput(prompt, scanner); // Vuelve a pedir la entrada si el formato es incorrecto
        }
    }

    // method to get a double
    public static double getDoubleInput(String prompt, Scanner scanner) throws InputMismatchException{
        System.out.print(prompt);
        double input = scanner.nextDouble();
        scanner.nextLine(); // clear buffer
        return input;
    }   

    // method to get an int
    public static int getIntInput(String prompt, Scanner scanner) throws InputMismatchException{
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return input;
    }

    // method to get a string
    public static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static LocalTime getTimeInput(String prompt, Scanner scanner) throws DateTimeParseException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(input, formatter);
    }
}
