package com.chulos.travelagency;

<<<<<<< HEAD
public class Main {
    public static void main(String[] args) {
        
    }
=======
import java.util.Scanner;

import com.chulos.travelagency.tripbooking.infrastructure.in.TripBookingController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TripBookingController tripBookingController = new TripBookingController(scanner);
        tripBookingController.run();
    }   
>>>>>>> trip_booking
}