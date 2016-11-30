package com.mycompany.services;

import com.mycompany.models.Coordinates;
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

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WeatherStationServiceImplTest {

    private static final LngLatAlt LVIV_LOCATION = new LngLatAlt(24.0297, 49.8397);

    @Mock
    private WeatherStationRepository repository;

    @Mock
    private ProximityService proximityService;

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
        Mockito.when(proximityService.getUnit()).thenReturn(DistanceUnit.KILOMETERS);
        Mockito.when(proximityService.getProximity()).thenReturn(100D);

    }

    @Test
    public void testGetStationsWithProximity() throws Exception {
        //Single station
        List<WeatherStation> stationsWithProximity = service.getStationsWithProximity(Arrays.asList(new Coordinates(24,49)));
        assertThat(stationsWithProximity.size()).isEqualTo(1);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        //Many stations
        stationsWithProximity = service.getStationsWithProximity(Arrays.asList(new Coordinates(24,49), new Coordinates(30,50), new Coordinates(13,52)));
        assertThat(stationsWithProximity.size()).isEqualTo(3);
        assertThat(stationsWithProximity.get(0).getName()).isEqualTo("Lviv");
        assertThat(stationsWithProximity.get(1).getName()).isEqualTo("Kyiv");
        assertThat(stationsWithProximity.get(2).getName()).isEqualTo("Berlin");
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