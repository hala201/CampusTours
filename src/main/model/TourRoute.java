package model;

import java.util.ArrayList;
import java.util.List;

public class TourRoute {
    int totalDuration;
    String facultyOfInterest;

    List<TourStop> myRoute = new ArrayList<TourStop>();

    //EFFECTS: make an empty tour list
    public void tourRoute() {
    }


    //MODIFIES: this
    //EFFECTS: adds a tour stop to the list of tour stops
    public void addTourStop(TourStop tourStop) {
    }

    //MODIFIES: this
    //EFFECTS: sets isVisited field in the TourStop class to true
    public void markAsVisited(TourStop tourStop) {
    }

    //MODIFIES: this
    //EFFECTS: sets the stop to be revisited
    public void markToBeRevisited() {
    }

    //MODIFIES: this
    //EFFECTS: delete the given tour stop from the list
    public void removeTourStop(TourStop tourStop) {
    }

}
