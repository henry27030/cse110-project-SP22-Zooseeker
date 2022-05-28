package edu.ucsd.cse110.cse110group51;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlanCalculate {
    String destination;
    IdentifiedWeightedEdge identifiedEdge;
    IdentifiedWeightedEdge newEdge1;
    IdentifiedWeightedEdge newEdge2;
    //
    String currentStreet = null;
    String source = null;
    String target = null;
    double distanceVal = 0;
    double totalDistance = 0;

    public PlanCalculate() {
    }

    // returns the path's destination
    public String getDestination() {
        return destination;
    }

    // extracted returns an ArrayList<String> of directions
    public List<String> extracted(Coord coordinate, ArrayList<String> exhibits) {
        String start = null;
        Set<String> keys=MainActivity.vInfo.keySet();

        for (String Nodes: keys) {
            //if (coordinate.equals(Coord.of(MainActivity.vInfo.get(Nodes).coords.lat, (MainActivity.vInfo.get(Nodes).coords.lng)))) {
            if (coordinate.equals(MainActivity.vInfo.get(Nodes).coords)) {
                start = MainActivity.vInfo.get(Nodes).id;
            }
        }
        //testing
        if (start==null) {
            //time to add in the edges and such
            //adding User to vInfo
            identifiedEdge = SlopeMath.edgeUserIsOn(coordinate);
            ZooData.VertexInfo userInfo = new ZooData.VertexInfo();
            userInfo.id = "User";
            userInfo.name = "User";
            userInfo.kind = ZooData.VertexInfo.Kind.EXHIBIT;
            MainActivity.vInfo.put("User", userInfo);
            MainActivity.g.addVertex("User");

            IdentifiedWeightedEdge edgeToRemove;

            String Source = MainActivity.g.getEdgeSource(identifiedEdge);
            String Target = MainActivity.g.getEdgeTarget(identifiedEdge);
            edgeToRemove=MainActivity.g.removeEdge(Source, Target);
            newEdge1= (IdentifiedWeightedEdge) edgeToRemove.clone();
            newEdge2= (IdentifiedWeightedEdge) edgeToRemove.clone();
            MainActivity.g.addEdge("User", Target, newEdge1);
            MainActivity.g.addEdge(Source, "User", newEdge2);

            //get distance between the User and Target
            double fromUserToTarget = SlopeMath.returnDistance(coordinate, MainActivity.vInfo.get(Target).coords);
            double fromSourceToUser = MainActivity.g.getEdgeWeight(identifiedEdge) - fromUserToTarget;
            MainActivity.g.getEdgeWeight(identifiedEdge);
            MainActivity.g.setEdgeWeight(newEdge1, fromUserToTarget);
            MainActivity.g.setEdgeWeight(newEdge2, fromSourceToUser);

            start = "User";
        }//

        ArrayList<String> Directions = new ArrayList<String>();
        //Directions.add(identifiedEdge.getId());//testing

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
            // add UserNode into vInfo
            // add two edges with weights in the MainActivity.g
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
            totalDistance+=MainActivity.g.getEdgeWeight(e);
            if (!MainActivity.briefDirections) {
                if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                    String strToInsert = "Walk "
                            +
                            MainActivity.g.getEdgeWeight(e) +
                            " ft along " +
                            MainActivity.eInfo.get(e.getId()).street +
                            " from " +
                            MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name +
                            " to " +
                            MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;

                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);
                    start = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id;
                } else {
                    String strToInsert = "Walk "
                            +
                            MainActivity.g.getEdgeWeight(e) +
                            " ft along " +
                            MainActivity.eInfo.get(e.getId()).street +
                            " from " +
                            MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name +
                            " to " +
                            MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);
                    start = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id;
                }
            }
            else if (MainActivity.briefDirections) {
                if (currentStreet==null) {
                    currentStreet = MainActivity.eInfo.get(e.getId()).street;
                    distanceVal+=MainActivity.g.getEdgeWeight(e);
                    if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                        source = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                        start = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id;
                    }
                    else {
                        source = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                        start = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id;
                    }
                }
                else if (currentStreet.equals(MainActivity.eInfo.get(e.getId()).street)) {
                    distanceVal+=MainActivity.g.getEdgeWeight(e);
                    if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                    }
                    else {
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                    }
                }
                else {
                    String strToInsert = "Walk " + distanceVal + " from "  + " ft toward " + target;
                    Directions.add(strToInsert);
                    distanceVal = 0;
                    currentStreet = MainActivity.eInfo.get(e.getId()).street;
                    distanceVal+=MainActivity.g.getEdgeWeight(e);
                    if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                        source = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                        start = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id;
                    }
                    else {
                        source = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                        start = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id;
                    }
                }
                if (target.equals(MainActivity.vInfo.get(shortestInput).name)) {
                    String strToInsert = "Walk " + distanceVal + " from " + source + " ft toward " + target;
                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);

                }
                if (source.equals(MainActivity.vInfo.get(shortestInput).name)) {
                    String strToInsert = "Walk " + distanceVal + " from " + target + " ft toward " + source;
                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);
                }
            }
        }
/* testing purposes
//exactly 26 "TESTTEST" which means that exactly 26 edges as required with the correct pair
        if (MainActivity.edgeSlopeBInfo.size()==26) {
            Set<String> EdgeKeys = MainActivity.eInfo.keySet();
            double a = 5;
            double b = 5;
            Pair<Double, Double> returnValue = new Pair<Double, Double> (a,b);
            for (String Edge: EdgeKeys) {
                if (MainActivity.edgeSlopeBInfo.get(Edge).equals(returnValue)) {
                    Directions.add("TESTTEST");
                }
            }
        }

 */

        //reverse changes if made
        if (identifiedEdge != null) {
            String source = MainActivity.g.getEdgeSource(newEdge2);
            String target = MainActivity.g.getEdgeTarget(newEdge1);
            MainActivity.vInfo.remove("User");
            MainActivity.g.removeEdge(newEdge1);
            MainActivity.g.removeEdge(newEdge2);
            MainActivity.g.addEdge(source, target, identifiedEdge);
        }
        return Directions;
    }
}