package com.mycompany.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Weather {

    private Date time;

    private BigDecimal windspeed;

    private SpeedUnit windspeedUnit;

    private BigDecimal temperature;

    private TemperatureUnit temperatureUnit;

}
