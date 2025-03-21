package com.example.user_service.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class LocationConverter {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Point convertToPoint(double longitude, double latitude) {
        Coordinate coordinate = new Coordinate(longitude, latitude);
        return geometryFactory.createPoint(coordinate);
    }
}