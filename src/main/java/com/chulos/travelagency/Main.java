package com.chulos.travelagency;

import java.util.Scanner;

import com.chulos.travelagency.tripbooking.application.AddBookingUseCase;
import com.chulos.travelagency.tripbooking.application.DeleteBookingUseCase;
import com.chulos.travelagency.tripbooking.application.FindBookingByIdUseCase;
import com.chulos.travelagency.tripbooking.application.FindBookingCustomerUseCase;
import com.chulos.travelagency.tripbooking.application.UpdateBookingUseCase;
import com.chulos.travelagency.tripbooking.domain.service.TripBookingService;
import com.chulos.travelagency.tripbooking.infrastructure.in.AddBookingView;
import com.chulos.travelagency.tripbooking.infrastructure.in.DeleteBookingView;
import com.chulos.travelagency.tripbooking.infrastructure.in.FindBookingByCustomerView;
import com.chulos.travelagency.tripbooking.infrastructure.in.FindBookingByIdView;
import com.chulos.travelagency.tripbooking.infrastructure.in.UpdateBookingView;
import com.chulos.travelagency.tripbooking.infrastructure.out.TripBookingRepository;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TripBookingService tripBookingService = new TripBookingRepository();

        // add booking test (Functional)
        // AddBookingUseCase addBookingUseCase = new AddBookingUseCase(tripBookingService);
        // AddBookingView addBookingView = new AddBookingView(addBookingUseCase, scanner);
        // addBookingView.start();

        // delete booking (Functional)
        // DeleteBookingUseCase deleteBookingUseCase = new DeleteBookingUseCase(tripBookingService);
        // DeleteBookingView deleteBookingView = new DeleteBookingView(deleteBookingUseCase, scanner);
        // deleteBookingView.start();

        // find booking by customer (Functional)
        // FindBookingCustomerUseCase findBookingCustomerUseCase = new FindBookingCustomerUseCase(tripBookingService);
        // FindBookingByCustomerView findBookingByCustomerView = new FindBookingByCustomerView(findBookingCustomerUseCase, scanner);
        // findBookingByCustomerView.start();

        // find trip by id (Functional)
        // FindBookingByIdUseCase findBookingByIdUseCase = new FindBookingByIdUseCase(tripBookingService);
        // FindBookingByIdView findBookingByIdView = new FindBookingByIdView(findBookingByIdUseCase, scanner);
        // findBookingByIdView.start();

        // update trip booking (Functional)
        // UpdateBookingUseCase updateBookingUseCase = new UpdateBookingUseCase(tripBookingService);
        // UpdateBookingView updateBookingView = new UpdateBookingView(updateBookingUseCase, scanner);
        // updateBookingView.start();
    }   
}