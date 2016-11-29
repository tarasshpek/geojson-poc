package com.mycompany.repositories;

import com.mycompany.models.Weather;
import com.mycompany.models.WeatherStation;
import org.springframework.stereotype.Component;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.List;

@Component
public class MockedWeatherStationRepository implements WeatherStationRepository {

    private List<WeatherStation> stations = fakeWeatherStations();

    @Override
    public List<WeatherStation> findAll() {
        for (WeatherStation station : stations){
            station.setCurrentWeather(generateRandomWeather());
        }
        return stations;
    }

    private static List<WeatherStation> fakeWeatherStations(){
        WeatherStation lvivStation = WeatherStation.create(1L, "Lviv", 24.0297, 49.8397);
        WeatherStation kyivStation = WeatherStation.create(2L, "Kyiv", 30.5234, 50.4501);;
        WeatherStation berlinStation = WeatherStation.create(3L, "Berlin", 13.404954, 52.520007);
        WeatherStation parisStation = WeatherStation.create(4L, "Paris", 2.352222, 48.856614);
        WeatherStation newYorkStation = WeatherStation.create(5L, "New York", -74.005941, 40.712784);
        WeatherStation sydneyStation = WeatherStation.create(6L, "Sydney", 151.209296, -33.868820);
        WeatherStation montevideoStation = WeatherStation.create(7L, "Montevideo", -56.164531, -34.901113);
        return Arrays.asList(lvivStation, kyivStation, berlinStation, parisStation, newYorkStation, sydneyStation, montevideoStation);
    }

    private static Weather generateRandomWeather(){
        PodamFactory factory = new PodamFactoryImpl();
        Weather weather = factory.manufacturePojo(Weather.class);
        return weather;
    }

}
