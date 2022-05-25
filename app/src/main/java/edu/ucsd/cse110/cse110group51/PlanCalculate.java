package edu.ucsd.cse110.cse110group51;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class PlanCalculate {
    String destination;

    public PlanCalculate() {
    }

    // returns the path's destination
    public String getDestination() {
        return destination;
    }

    // extracted returns an ArrayList<String> of directions
    public List<String> extracted(String start, ArrayList<String> exhibits) {
        ArrayList<String> Directions = new ArrayList<String>();
        //use exhibitListInFunc as an ArrayList to add and remove without changing exhibitList
        ArrayList<String> exhibitListInFunc = new ArrayList<String>();
        for (String exhibit : exhibits) {
            exhibitListInFunc.add(exhibit);
        }
        int shortestExhibit = 0;
        float shortestLength = 0;
        float currentLength = 0;

        shortestExhibit = 0;
        shortestLength = 0;

        // run through the list of exhibitListInFunc, which should store the same values as exhibitList
        for (int i = 0; i < exhibitListInFunc.size(); i++) {
            currentLength = 0;
            String input = exhibitListInFunc.get(i);

            // in the special case that an exhibit is within a group
            if (MainActivity.vInfo.get(input).group_id !=null) {
                input=MainActivity.vInfo.get(MainActivity.vInfo.get(input).group_id).id;
            }

            // find the shortest path between start and input, which is either an exhibit or a group of exhibits
            MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, input);
            for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                currentLength += MainActivity.g.getEdgeWeight(e);
            }
            if (shortestLength == 0 || currentLength < shortestLength) {
                shortestLength = currentLength;
                shortestExhibit = i;
            }
        }

        // save the shortest distance exhibit from start in shortestInput
        String shortestInput = exhibitListInFunc.get(shortestExhibit);
        if (MainActivity.vInfo.get(shortestInput).group_id !=null) {
            shortestInput=MainActivity.vInfo.get(MainActivity.vInfo.get(shortestInput).group_id).id;
        }

        // save the path description again
        MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, shortestInput);

        // save our intended exhibit to visit using this path in destination
        destination = exhibitListInFunc.get(shortestExhibit);

        //add a string of directions to Directions String array
        for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
            if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                String strToInsert = "Walk "
                        +
                        MainActivity.g.getEdgeWeight(e) +
                        " Of meters along " +
                        MainActivity.eInfo.get(e.getId()).street +
                        " from " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name +
                        " to " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;

                if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id !=null) {

                    if (MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                        strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                    }
                }
                Directions.add(strToInsert);
                start=MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id;
            } else {
                String strToInsert = "Walk "
                        +
                        MainActivity.g.getEdgeWeight(e) +
                        " Of meters along " +
                        MainActivity.eInfo.get(e.getId()).street +
                        " from " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name +
                        " to " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id !=null) {

                    if (MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                        strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                    }
                }
                Directions.add(strToInsert);
                start=MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id;
            }
        }
        return Directions;
    }
}