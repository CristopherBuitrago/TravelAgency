package com.chulos.travelagency.plane.domain.service;

import com.chulos.travelagency.plane.domain.entity.Plane;

public interface PlaneService {
    String createPlane (Plane plane);
    Plane findPlaneByPlate (String plate);
    String updatePlane (Plane plane);
    String deletePlane (String plate);
}
