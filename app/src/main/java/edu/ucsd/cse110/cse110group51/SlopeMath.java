package edu.ucsd.cse110.cse110group51;

import androidx.annotation.NonNull;

import org.jgrapht.alg.util.Pair;

public class SlopeMath {

    public static Pair<Double, Double> returnSlopeB (String source, String target) {
        double sourceLat = MainActivity.vInfo.get(source).lat;
        double sourceLng = MainActivity.vInfo.get(source).lng;
        double targetLat = MainActivity.vInfo.get(target).lat;
        double targetLng = MainActivity.vInfo.get(target).lng;
        double a = 5;
        double b = 5;
        Pair<Double, Double> returnValue = new Pair<Double, Double> (a,b);
        return returnValue;
    }
}
