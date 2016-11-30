package com.mycompany.services;

import com.mycompany.models.Coordinates;
import com.mycompany.models.DistanceUnit;
import com.mycompany.models.WeatherStation;
import org.geojson.Feature;
import org.geojson.LngLatAlt;

import java.util.List;

public interface WeatherStationService {

    List<WeatherStation> getStationsWithProximity(List<Coordinates> coordinates);

    List<Feature> convertStationsToFeatures(List<WeatherStation> stations);

}
