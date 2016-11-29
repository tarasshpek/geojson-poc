package com.mycompany.repositories;

import com.mycompany.models.WeatherStation;

import java.util.List;

public interface WeatherStationRepository {

    List<WeatherStation> findAll();

}
