package com.mycompany.services;

import com.mycompany.models.DistanceUnit;
import com.mycompany.models.WeatherStation;
import com.mycompany.repositories.MockedWeatherStationRepository;
import com.mycompany.repositories.WeatherStationRepository;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WeatherStationServiceImplTest {

    private static final LngLatAlt LVIV_LOCATION = new LngLatAlt(24.0297, 49.8397);

    @Mock
    private WeatherStationRepository repository;

    private List<WeatherStation> mockedStations;

    @InjectMocks
    private WeatherStationService service = new WeatherStationServiceImpl();

    @Before
    public void setUp(){
        mockedStations = new MockedWeatherStationRepository().findAll();
        assertThat(mockedStations.get(0).getName()).isEqualTo("Lviv");
        assertThat(mockedStations.get(1).getName()).isEqualTo("Kyiv");
        assertThat(mockedStations.get(2).getName()).isEqualTo("Berlin");
        assertThat(mockedStations.get(3).getName()).isEqualTo("Paris");
        assertThat(mockedStations.get(4).getName()).isEqualTo("New York");
        assertThat(mockedStations.get(5).getName()).isEqualTo("Sydney");
        assertThat(mockedStations.get(6).getName()).isEqualTo("Montevideo");
        Mockito.when(repository.findAll()).thenReturn(mockedStations);
    }

    @Test
    public void testGetStationsWithProximity() throws Exception {
        /*//1 km
        List<WeatherStation> stationsWithProximity = service.getStationsWithProximity(DistanceUnit.KILOMETERS, 1, LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude());
        assertThat(stationsWithProximity.size()).isEqualTo(1);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        //1000 km
        stationsWithProximity = service.getStationsWithProximity(DistanceUnit.KILOMETERS, 1000, LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude());
        assertThat(stationsWithProximity.size()).isEqualTo(3);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        assertThat(stationsWithProximity.get(1).getName()).isEqualTo("Kyiv");
        assertThat(stationsWithProximity.get(2).getName()).isEqualTo("Berlin");
        //10000 km
        stationsWithProximity = service.getStationsWithProximity(DistanceUnit.KILOMETERS, 10000, LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude());
        assertThat(stationsWithProximity.size()).isEqualTo(5);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        assertThat(stationsWithProximity.get(1).getName()).isEqualTo("Kyiv");
        assertThat(stationsWithProximity.get(2).getName()).isEqualTo("Berlin");
        assertThat(stationsWithProximity.get(3).getName()).isEqualTo("Paris");
        //100000 km
        stationsWithProximity = service.getStationsWithProximity(DistanceUnit.KILOMETERS, 100000, LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude());
        assertThat(stationsWithProximity.size()).isEqualTo(7);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        assertThat(stationsWithProximity.get(1).getName()).isEqualTo("Kyiv");
        assertThat(stationsWithProximity.get(2).getName()).isEqualTo("Berlin");
        assertThat(stationsWithProximity.get(3).getName()).isEqualTo("Paris");
        assertThat(stationsWithProximity.get(4).getName()).isEqualTo("New York");
        assertThat(stationsWithProximity.get(5).getName()).isEqualTo("Sydney");
        assertThat(stationsWithProximity.get(6).getName()).isEqualTo("Montevideo");*/
    }

    @Test
    public void testConvertStationsToFeatures() throws Exception {
        //Given
        int expectedSize = 7;
        //When
        List<Feature> features = service.convertStationsToFeatures(mockedStations);
        //Then
        assertThat(features.size()).isEqualTo(expectedSize);
        for (int i = 0; i < expectedSize; i++){
            testFeature(features.get(i), mockedStations.get(i));
        }
    }

    private void testFeature(Feature feature, WeatherStation station){
        assertThat(feature.getGeometry()).isEqualTo(station.getLocation());
        assertThat(feature.getProperties().size()).isEqualTo(1);
        assertThat(feature.getProperties().get("WeatherStation")).isEqualTo(station);
    }

}