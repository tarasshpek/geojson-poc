package com.mycompany.controllers;

import com.mycompany.models.Coordinates;
import com.mycompany.models.WeatherStation;
import com.mycompany.services.WeatherStationService;
import lombok.extern.slf4j.Slf4j;
import org.geojson.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WeatherStationController {

    @Autowired
    private WeatherStationService service;

    /**
     * This method returns list of Geojson Feature objects
     * with WeatherStation object in properties and updated weather information
     * for each station.
     *
     * Weather stations filtered by received list of Coordinates
     * with proximity (can be set in application.properties)
     *
     * @param coordinates
     * @return list of Geojson Feature objects
     */
    @PutMapping("/weather")
    public List<Feature> updateWeather(@RequestBody List<Coordinates> coordinates){
        log.info("PUT /weather " + coordinates);
        List<WeatherStation> stations = service.getStationsWithProximity(coordinates);
        return service.convertStationsToFeatures(stations);
    }

}
