package com.mycompany.services;

import com.mycompany.models.Coordinates;
import com.mycompany.models.WeatherStation;
import org.geojson.Feature;

import java.util.List;

public interface WeatherStationService {

    List<WeatherStation> getStationsWithProximity(List<Coordinates> coordinates);

    List<Feature> convertStationsToFeatures(List<WeatherStation> stations);

}
