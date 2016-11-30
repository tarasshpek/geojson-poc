package com.mycompany.services;

import com.mycompany.models.DistanceUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProximityServiceImpl implements ProximityService {

    @Value("${geojson.proximity.unit}")
    private DistanceUnit distanceUnit;

    @Value("${geojson.proximity}")
    private double proximity;

    @Override
    public DistanceUnit getUnit() {
        return distanceUnit;
    }

    @Override
    public Double getProximity() {
        return proximity;
    }
}
