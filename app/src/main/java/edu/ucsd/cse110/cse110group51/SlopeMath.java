package edu.ucsd.cse110.cse110group51;

import static java.lang.Math.abs;

import androidx.annotation.NonNull;

import org.jgrapht.alg.util.Pair;

public class SlopeMath {

    // returning slope and b from y=mx+b in the form of a pair<slope, b>
    public static Pair<Double, Double> returnSlopeB (String source, String target) {
        double sourceLat = MainActivity.vInfo.get(source).lat;
        double sourceLng = MainActivity.vInfo.get(source).lng;
        double targetLat = MainActivity.vInfo.get(target).lat;
        double targetLng = MainActivity.vInfo.get(target).lng;
        /*
        double slope = 5;
        double b = 5;
        */
        double d_lat = abs(targetLat-sourceLat);
        double d_lng = abs(targetLng - sourceLng);
        double d_ft_v = d_lat * 363843.57; //this decimal is conversion factor
        double d_ft_h = d_lng * 307515.50; //this decimal is conversion factor

        double slope = d_ft_v/d_ft_h; //longitude is our x, latitude is our y
        double b = sourceLat - (slope * sourceLng);

        // returning slope and b from y=mx+b
        Pair<Double, Double> returnValue = new Pair<Double, Double> (slope,b);
        return returnValue;
    }
}
