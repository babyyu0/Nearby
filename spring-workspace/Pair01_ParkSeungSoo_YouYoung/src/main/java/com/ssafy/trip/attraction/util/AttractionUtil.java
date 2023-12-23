package com.ssafy.trip.attraction.util;

public class AttractionUtil {
    public static double calDist(double lat1, double lon1, double lat2, double lon2) {
        return 6371000 * Math.acos(Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(lon2) - Math.toRadians(lon1))
                + Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
        );
    }
}
