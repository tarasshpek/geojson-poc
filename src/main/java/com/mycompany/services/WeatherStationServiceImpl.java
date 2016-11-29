package com.mycompany.services;

import com.mycompany.models.DistanceUnit;
import com.mycompany.models.WeatherStation;
import com.mycompany.repositories.WeatherStationRepository;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStationServiceImpl implements WeatherStationService {

    @Autowired
    private WeatherStationRepository repository;

    @Value("${geojson.proximity.unit}")
    private DistanceUnit distanceUnit;

    @Value("${geojson.proximity}")
    private double proximity;

    @Override
    public List<WeatherStation> getStationsWithProximity(List<LngLatAlt> coordinates) {
        DistancePredicate predicate = new DistancePredicate(distanceUnit, proximity, coordinates);
        return repository.findAll().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Feature> convertStationsToFeatures(List<WeatherStation> stations) {
        List<Feature> features = new ArrayList<>(stations.size());
        for (WeatherStation station : stations){
            Feature feature = new Feature();
            feature.setGeometry(station.getLocation());
            feature.setProperty("WeatherStation", station);
            features.add(feature);
        }
        return features;
    }


}
