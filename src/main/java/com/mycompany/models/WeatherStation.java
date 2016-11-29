package com.mycompany.models;

import lombok.Data;
import org.geojson.Point;

@Data
public class WeatherStation {

    private Long id;

    private String name;

    private Point location;

    private Weather currentWeather;

    public static WeatherStation create(Long id, String name, double longitude, double latitude){
        WeatherStation station = new WeatherStation();
        station.setId(id);
        station.setName(name);
        station.setLocation(new Point(longitude, latitude));
        return station;
    }

}
