package com.mycompany.controllers;

import com.mycompany.models.WeatherStation;
import com.mycompany.services.WeatherStationService;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherStationController {

    @Autowired
    private WeatherStationService service;


    @GetMapping("/weatherstations")
    public List<Feature> getStations(List<LngLatAlt> coordinates){
        List<WeatherStation> stations = service.getStationsWithProximity(coordinates);
        return service.convertStationsToFeatures(stations);
    }

}
