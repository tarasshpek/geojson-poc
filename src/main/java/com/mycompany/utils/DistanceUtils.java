package com.mycompany.utils;

import com.mycompany.models.DistanceUnit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DistanceUtils {

    //Calculates distance between two points
    public static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == DistanceUnit.KILOMETERS) {
            dist = dist * 1.609344;
        } else if (unit == DistanceUnit.NAUTICAL_MILES) {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    //Decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //Radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
