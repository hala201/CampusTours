package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TourRoute {
    int totalDuration;
    String facultyOfInterest;

    private HashMap<String, TourStop> tourRoute;
    private HashMap<String, TourStop> visitedRoute;
    private int maxSize = 5;


    //EFFECTS: make a list of the visited and unvisited stops
    public TourRoute() {
    }


    //EFFECTS: selects a tour route according to faculty
    //           - Arts/Fine Arts: North end of main mall
    //           - Engineering/LFS/KIN/ south end of main mall
    //           - Science/Sauder/Education center of main mall
    //         adds two food place by default
    //         maximum duration of each route is 2 hours
    public TourRoute displayRecommendTourStops(String faculty) {
        return null;
    }

    //REQUIRE: should not exceed the maximum number of tour stops
    //MODIFIES: this
    //EFFECTS: adds a tour stop to the list of tour stops
    public void addTourStop(TourStop tourStop) {
    }

    //MODIFIES: this
    //EFFECTS: sets isVisited field in the TourStop class to true
    public void markAsVisited(TourStop tourStop) {
    }

    public String displayVisitedStop(String name) {
        return "";
    }

    public String displayVisitedStops() {
        return "";
    }

    public String displayTourRoute() {
        return "";
    }

    public boolean containsUnvisitedTourStop() {
        return false;
    }

    public boolean containsVisitedTourStop() {
        return false;
    }

    public int tourLength() {
        return 0;
    }

    public void load(TourRoute route) {

    }

    public void save(TourRoute route) {

    }
//    //MODIFIES: this
//    //EFFECTS: sets the stop to be revisited
//    public void markToBeRevisited() {
//    }

    //MODIFIES: this
    //EFFECTS: delete the given tour stop from the list
    public void removeTourStop(TourStop tourStop) {
    }

}
