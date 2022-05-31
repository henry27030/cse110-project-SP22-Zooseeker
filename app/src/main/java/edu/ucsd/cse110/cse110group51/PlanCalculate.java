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
        // Directions to return
        ArrayList<String> Directions = new ArrayList<String>();

        for (String Nodes: keys) {
            //if (coordinate.equals(Coord.of(MainActivity.vInfo.get(Nodes).coords.lat, (MainActivity.vInfo.get(Nodes).coords.lng)))) {
            if (coordinate.equals(MainActivity.vInfo.get(Nodes).coords)) {
                start = MainActivity.vInfo.get(Nodes).id;
            }
        }

        // in the case that start's coords are not that of a node's
        // create a user node and two edges from the User node's location to replace the edge it is on
        if (start==null) {
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
        }

        String compare1 = start;
        String compare2 = null;


        // use exhibitListInFunc as an ArrayList to add and remove without changing exhibitList
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
            compare2 = input;

            // in the case that two exhibits are in the same group
            if (MainActivity.vInfo.get(compare2).group_id!=null) {
                if (MainActivity.vInfo.get(compare1).id.equals(MainActivity.vInfo.get(compare2).group_id)) {
                    Directions.add(MainActivity.vInfo.get(compare2).name + " is also within this exhibit." );
                    destination = compare2;
                    return Directions;
                }
                if (MainActivity.vInfo.get(compare1).group_id!=null) {
                    if (MainActivity.vInfo.get(compare1).group_id.equals(MainActivity.vInfo.get(compare2).group_id)) {
                        Directions.add(MainActivity.vInfo.get(compare2).name + " is also within this exhibit.");
                        destination = compare2;
                        return Directions;
                    }
                }
            }
            //in the case that User is already at one of the chosen exhibits
            if (MainActivity.vInfo.get(start).id!=null) {
                if (MainActivity.vInfo.get(start).id.equals(MainActivity.vInfo.get(input).id)) {
                    Directions.add("User is currently at a chosen exhibit: " +
                            MainActivity.vInfo.get(input).name +
                            ". Please select NEXT to obtain the directions to the next exhibit.");
                    destination = input;
                    return Directions;
                }
            }
            // in the special case that an exhibit is within a group
            if (MainActivity.vInfo.get(input).group_id !=null) {
                input=MainActivity.vInfo.get(MainActivity.vInfo.get(input).group_id).id;
            }
            if (MainActivity.vInfo.get(start).group_id !=null) {
                start=MainActivity.vInfo.get(MainActivity.vInfo.get(start).group_id).id;
            }
            //use Dijkstra's to find exhibit with shortest path
            MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, input);
            for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                currentLength += MainActivity.g.getEdgeWeight(e);
            }
            if (shortestLength == 0 || currentLength < shortestLength) {
                shortestLength = currentLength;
                shortestExhibit = i;
            }
        }

        // save the shortest distance exhibit from start in shortestInput and see if it belongs to a group
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
            //briefDirections are disabled
            if (!MainActivity.briefDirections) {
                // in the case that User starts at the target of an edge
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
                }
                // in the case that User starts normally: User starts at the source of an edge
                else {
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
            //briefDirections are enabled
            else if (MainActivity.briefDirections) {
                // reads in the first set of directions
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
                // in the case that the street is repeated in a message, don't output it
                else if (currentStreet.equals(MainActivity.eInfo.get(e.getId()).street)) {
                    distanceVal+=MainActivity.g.getEdgeWeight(e);
                    if (MainActivity.vInfo.get(start).name.equals(MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name)) {
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name;
                    }
                    else {
                        target = MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                    }
                }
                // in the case that street isn't repeated but doesn't match the previous message's street
                else {
                    String strToInsert = "Walk " + distanceVal + " ft from " + source + " toward " + target;
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
                // in the case that User starts at the target of an edge
                // output the message accordingly
                if (target.equals(MainActivity.vInfo.get(shortestInput).name)) {
                    String strToInsert = "Walk " + distanceVal + " ft from " + source + " toward " + target;
                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);

                }
                // in the case that User starts normally: User starts at the source of an edge
                // output the message accordingly
                if (source.equals(MainActivity.vInfo.get(shortestInput).name)) {
                    String strToInsert = "Walk " + distanceVal + " ft from " + target + " toward " + source;
                    if (MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id != null) {

                        if (MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).id.equals(MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).group_id)) {
                            strToInsert = strToInsert + " and find " + MainActivity.vInfo.get(exhibitListInFunc.get(shortestExhibit)).name + "s inside.";
                        }
                    }
                    Directions.add(strToInsert);
                }
            }
        }
        //reverse changes to nodes and edges if made
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