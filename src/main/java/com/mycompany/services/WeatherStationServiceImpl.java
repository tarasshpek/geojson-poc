package com.mycompany.services;

import com.mycompany.models.Coordinates;
import com.mycompany.models.WeatherStation;
import com.mycompany.repositories.WeatherStationRepository;
import org.geojson.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStationServiceImpl implements WeatherStationService {

    @Autowired
    private WeatherStationRepository repository;

    @Autowired
    private ProximityService proximityService;

    @Override
    public List<WeatherStation> getStationsWithProximity(List<Coordinates> coordinates) {
        DistancePredicate predicate = new DistancePredicate(proximityService.getUnit(),
                proximityService.getProximity(), coordinates);
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
