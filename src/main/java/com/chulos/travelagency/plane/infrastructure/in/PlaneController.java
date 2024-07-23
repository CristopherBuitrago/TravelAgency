package com.chulos.travelagency.plane.infrastructure.in;

import java.util.Scanner;

import com.chulos.travelagency.plane.application.CreatePlaneUseCase;
import com.chulos.travelagency.plane.domain.service.PlaneService;
import com.chulos.travelagency.plane.infrastructure.out.PlaneRepository;

public class PlaneController {
    private final PlaneService planeService;
    private final CreatePlaneView createPlaneView;
    private Scanner scanner;

    // constructor
    public PlaneController(Scanner scanner) {
        // get the repository
        this.planeService = new PlaneRepository();
        // create plane
        CreatePlaneUseCase createPlaneUseCase = new CreatePlaneUseCase(planeService);
        this.createPlaneView = new CreatePlaneView(createPlaneUseCase, scanner);
        // get the scanner
        this.scanner = scanner;
    }
}
