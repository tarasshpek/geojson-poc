package com.mycompany.services;

import com.mycompany.models.DistanceUnit;

public interface ProximityService {

    DistanceUnit getUnit();

    Double getProximity();

}
