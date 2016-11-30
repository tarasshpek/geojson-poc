package com.mycompany.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProximityServiceImplTest {

    @Autowired
    private ProximityServiceImpl service;

    @Test
    public void testGetUnitIsNotNull() throws Exception {
        assertThat(service.getUnit()).isNotNull();
    }

    @Test
    public void testGetProximityIsNotNull() throws Exception {
        assertThat(service.getProximity()).isNotNull();
    }

}