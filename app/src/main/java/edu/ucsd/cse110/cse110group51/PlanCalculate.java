package edu.ucsd.cse110.cse110group51;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;

public class PlanCalculate {
    String start;
    String destination;
    ArrayList<String> exhibits;
    public PlanCalculate(String start, ArrayList<String> exhibits) {
        this.start=start;
        this.exhibits=exhibits;
    }
    public String getDestination() {
        return destination;
    }
    public void extracted() {
        ArrayList<String> Directions = new ArrayList<String>();
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
            }
        }
        // set Directions in MainActivity to Directions
        MainActivity.Directions = Directions;
    }
}