package com.mycompany.utils;

import com.mycompany.models.DistanceUnit;
import org.geojson.LngLatAlt;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;

public class DistanceUtilsTest {

    private static final LngLatAlt KYIV_LOCATION = new LngLatAlt(30.5234, 50.4501);

    private static final LngLatAlt LVIV_LOCATION = new LngLatAlt(24.0297, 49.8397);

    private static final double KYIV_LVIV_DISTANCE_KILOMETERS = 467.50789837372434;

    private static final double KYIV_LVIV_DISTANCE_MILES = 290.49594019285144;

    private static final double KYIV_LVIV_DISTANCE_NAUTICAL_MILES = 252.26667446347219;

    @Test
    public void testDistanceInKilometers() throws Exception {
        double distance = DistanceUtils.distance(KYIV_LOCATION.getLatitude(), KYIV_LOCATION.getLongitude(),
                LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude(),
                DistanceUnit.KILOMETERS);
        assertThat(distance).isEqualTo(KYIV_LVIV_DISTANCE_KILOMETERS);
    }

    @Test
    public void testDistanceInNauticalMiles() throws Exception {
        double distance = DistanceUtils.distance(KYIV_LOCATION.getLatitude(), KYIV_LOCATION.getLongitude(),
                LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude(),
                DistanceUnit.NAUTICAL_MILES);
        assertThat(distance).isEqualTo(KYIV_LVIV_DISTANCE_NAUTICAL_MILES);
    }

    @Test
    public void testDistanceInMiles() throws Exception {
        double distance = DistanceUtils.distance(KYIV_LOCATION.getLatitude(), KYIV_LOCATION.getLongitude(),
                LVIV_LOCATION.getLatitude(), LVIV_LOCATION.getLongitude(),
                DistanceUnit.MILES);
        assertThat(distance).isEqualTo(KYIV_LVIV_DISTANCE_MILES);
    }

}