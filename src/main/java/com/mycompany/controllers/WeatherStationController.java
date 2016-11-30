package com.mycompany.controllers;

import com.mycompany.models.Coordinates;
import com.mycompany.models.WeatherStation;
import com.mycompany.services.WeatherStationService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WeatherStationController {

    @Autowired
    private WeatherStationService service;


    @PutMapping("/weatherstations")
    public List<Feature> getStations(@RequestBody List<Coordinates> coordinates){
        log.info("PUT /weatherstations " + coordinates);
        List<WeatherStation> stations = service.getStationsWithProximity(coordinates);
        return service.convertStationsToFeatures(stations);
    }

}
