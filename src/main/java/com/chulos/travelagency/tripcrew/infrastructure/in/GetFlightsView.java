package com.chulos.travelagency.tripcrew.infrastructure.in;

import java.util.List;
import com.chulos.travelagency.flight.domain.entity.Flight;
import com.chulos.travelagency.tripcrew.application.GetFlightsUseCase;
import com.chulos.travelagency.utils.MyUtils;

public class GetFlightsView {
    // get app and scanner
    private final GetFlightsUseCase getFlightsUseCase;
    private boolean isEmpty;

    // constructor
    public GetFlightsView(GetFlightsUseCase getFlightsUseCase) {
        this.getFlightsUseCase = getFlightsUseCase;
    }

    // start method
    public boolean start() {
        // get flights
        List<Flight> flights = getFlightsUseCase.execute();

        // verify if there are flights
        if (flights != null && !flights.isEmpty()) {
            String leftAlignFormat = "| %-4d | %-17d |%n";
            System.out.format("+------+-------------------+%n");
            System.out.format("| ID   | Connection Number |%n");
            System.out.format("+------+-------------------+%n");
            
            // iterate flights list
            for (Flight flight : flights) {
                System.out.format(leftAlignFormat, flight.getId(), flight.getConnectionNumber());
            }
            System.out.format("+------+-------------------+%n");
        } else {
            MyUtils.displayMessageAndClearScreen("Ups! it seems that there are not records yet. Please try again", 3);
            isEmpty = true;
        }

        return isEmpty;
    }
}