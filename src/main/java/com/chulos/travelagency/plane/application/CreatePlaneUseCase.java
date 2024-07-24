package com.chulos.travelagency.plane.application;

import com.chulos.travelagency.plane.domain.entity.Plane;
import com.chulos.travelagency.plane.domain.service.PlaneService;

public class CreatePlaneUseCase {
    // atributes
    private final PlaneService planeService;

    // constructor
    public CreatePlaneUseCase(PlaneService planeService) {
        this.planeService = planeService;
    }
    
    // execute method
    public String execute(Plane plane){
        return planeService.createPlane(plane);
    }
}
