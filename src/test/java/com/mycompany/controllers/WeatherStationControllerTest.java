package com.mycompany.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.models.WeatherStation;
import com.mycompany.repositories.WeatherStationRepository;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherStationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private WeatherStationRepository weatherStationRepository;

    private MockMvc mockMvc;

    private List<WeatherStation> stations;

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(wac).build();
        stations = weatherStationRepository.findAll();
        assertThat(stations.get(0).getName()).isEqualTo("Lviv");
        assertThat(stations.get(1).getName()).isEqualTo("Kyiv");
        assertThat(stations.get(2).getName()).isEqualTo("Berlin");
        assertThat(stations.get(3).getName()).isEqualTo("Paris");
        assertThat(stations.get(4).getName()).isEqualTo("New York");
        assertThat(stations.get(5).getName()).isEqualTo("Sydney");
        assertThat(stations.get(6).getName()).isEqualTo("Montevideo");
    }

    @Test
    public void testGetStationsWithProximityParameter() throws Exception {
        //Given
        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/five_weather_stations.json"));
        String json = new String(encoded, "UTF-8");
        //When
        MvcResult result = mockMvc.perform(
                put("/weatherstations")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andReturn();
        //Then
        List<Feature> features = featuresFromJson(result.getResponse().getContentAsString());
        assertThat(features.size()).isEqualTo(5);
        testFeature(features.get(0), stations.get(0));
        testFeature(features.get(1), stations.get(1));
        testFeature(features.get(2), stations.get(2));
        testFeature(features.get(3), stations.get(3));
        testFeature(features.get(4), stations.get(4));
    }

    private List<Feature> featuresFromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Feature> features = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Feature.class));
        return features;
    }

    private void testFeature(Feature feature, WeatherStation expectedStation) throws IOException {
        assertThat(feature.getGeometry()).isEqualTo(expectedStation.getLocation());
        assertThat(feature.getProperties().size()).isEqualTo(1);
        ObjectMapper mapper = new ObjectMapper();
        WeatherStation actualStation = mapper.convertValue(feature.getProperties().get("WeatherStation"),
                WeatherStation.class);
        assertThat(actualStation).isEqualTo(expectedStation);
    }

}