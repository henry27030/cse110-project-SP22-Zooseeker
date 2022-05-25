package edu.ucsd.cse110.cse110group51;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class PlanCalculate {
    String destination;
    public PlanCalculate() {
    }

    public String getDestination() {
        return destination;
    }
    public List<String> extracted(String start, ArrayList<String> exhibits) {
        ArrayList<String> Directions = new ArrayList<String>();
        if(exhibits.size() == 0){
            // if there is no exhibits, return list with 0 elements
            return Directions;
        }
        //use exhibitListInFunc as an ArrayList to add and remove without changing exhibitList
        ArrayList<String> exhibitListInFunc = new ArrayList<String>();
        for (String exhibit : exhibits) {
            exhibitListInFunc.add(exhibit);
        }
        int shortestExhibit = 0;
        float shortestLength = 0;
        float currentLength = 0;
        //int instructionCount = 1;
        shortestExhibit = 0;
        shortestLength = 0;
        for (int i = 0; i < exhibitListInFunc.size(); i++) {
            currentLength = 0;
            MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, exhibitListInFunc.get(i));
            for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                currentLength += MainActivity.g.getEdgeWeight(e);
            }
            if (shortestLength == 0 || currentLength < shortestLength) {
                shortestLength = currentLength;
                shortestExhibit = i;
            }
        }
        MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, exhibitListInFunc.get(shortestExhibit));
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
                Directions.add(strToInsert);
                start=MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id;
            }
        }
        // set Directions in MainActivity to Directions
        //MainActivity.Directions = Directions;
        return Directions;
    }
    //public List<String> DirectDestination (String destination)
}