package edu.ucsd.cse110.cse110group51;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import androidx.annotation.NonNull;

import org.jgrapht.alg.util.Pair;

import java.util.Set;

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
        double d_lat = (targetLat - sourceLat);
        double d_lng = (targetLng - sourceLng);
        double d_ft_v = d_lat * 363843.57; //this decimal is conversion factor
        double d_ft_h = d_lng * 307515.50; //this decimal is conversion factor

        double slope = d_ft_v/d_ft_h; //longitude is our x, latitude is our y
        double b = (targetLat*363843.57) - (slope * (targetLng*307515.50));

        // returning slope and b from y=mx+b
        Pair<Double, Double> returnValue = new Pair<Double, Double> (slope,b);
        return returnValue;
    }

    //return IdentifiedWeightedEdge that the User is on based on coordinates
    public static IdentifiedWeightedEdge edgeUserIsOn (Coord coordinates) {
        IdentifiedWeightedEdge returnValue = null;
        String edgeFound = null;
        double YDifference = -1;
        double UserLat = coordinates.lat;
        double UserLng = coordinates.lng;
        double ConvertedUserLat = UserLat * 363843.57;
        double ConvertedUserLng = UserLng * 307515.50;
        Set<String> EdgeKeys = MainActivity.eInfo.keySet();
        for (String Edge: EdgeKeys) {
            double slope = MainActivity.edgeSlopeBInfo.get(Edge).getFirst();
            double b = MainActivity.edgeSlopeBInfo.get(Edge).getSecond();
            double possibleYDifference = abs(ConvertedUserLat - ((slope*ConvertedUserLng) + b));
            if (YDifference==-1 || possibleYDifference<YDifference) {
                YDifference = possibleYDifference;
                edgeFound = Edge;
            }
        }
        Set<IdentifiedWeightedEdge> edgeList;
        edgeList = MainActivity.g.edgeSet();
        for (IdentifiedWeightedEdge Edges: edgeList) {
            if (Edges.getId().equals(edgeFound)) {
                returnValue = Edges;
            }
        }
        return returnValue;
    }

    // use distance formula with coordinate conversions
    public static double returnDistance (Coord coord1, Coord coord2) {
        double d_lat = abs(coord1.lat - coord2.lat);
        double d_lng = abs(coord1.lng - coord2.lng);

        double d_ft_v = d_lat * 363843.57;
        double d_ft_h = d_lng * 307515.50;

        double d_ft = sqrt((d_ft_v * d_ft_v) + (d_ft_h * d_ft_h));

        return Math.ceil(d_ft);
    }
}
