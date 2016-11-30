package com.mycompany.services;

import com.mycompany.models.Coordinates;
import com.mycompany.models.DistanceUnit;
import com.mycompany.models.WeatherStation;
import com.mycompany.utils.DistanceUtils;
import org.geojson.LngLatAlt;

import java.util.List;
import java.util.function.Predicate;

public class DistancePredicate implements Predicate<WeatherStation> {

    private DistanceUnit unit;

    private double proximity;

    private List<Coordinates> coordinates;

    public DistancePredicate(DistanceUnit unit, double proximity, List<Coordinates> coordinates) {
        this.unit = unit;
        this.proximity = proximity;
        this.coordinates = coordinates;
    }

    @Override
    public boolean test(WeatherStation weatherStation){
        for (Coordinates c : coordinates){
            if (test(weatherStation, c.getLatitude(), c.getLongitude())){
                return true;
            }
        }
        return false;
    }

    private boolean test(WeatherStation weatherStation, double baseLatitude, double baseLongitude){
        double distance = DistanceUtils.distance(baseLatitude, baseLongitude,
                weatherStation.getLocation().getCoordinates().getLatitude(),
                weatherStation.getLocation().getCoordinates().getLongitude(),
                unit);
        return distance <= proximity;
    }

}
