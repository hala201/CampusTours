package model;

import java.util.HashMap;
import java.util.Map;

public class TourRoute {
    String facultyOfInterest;

    public HashMap<String, TourStop> tourRoute;
    public HashMap<String, TourStop> visitedRoute;
    public int maxSize = 4;

    //will be useful for the next phase
//    public final String listCenter = "";
//    public final String listNorth = "";
//    public final String listSouth = "";

    //for now I resolved that by requiring the add to add compatible stops

    //REQUIRE: all tour stops must be in the same area
    //         tour does not exceed 5 stops
    //EFFECTS: make a list of the visited and to be visited tour stops
    public TourRoute() {
        visitedRoute = new HashMap<>();
        tourRoute = new HashMap<>();
    }

    //REQUIRE: should not exceed the maximum number of tour stops
    //MODIFIES: this
    //EFFECTS: adds a tour stop to the list of tour stops, returns true if added successfully
    public boolean addTourStop(TourStop tourStop) {
        if (tourRoute.size() < maxSize) {
            if (tourStop.getArea() == recommendArea()
                    && !containsTourStop(tourStop)) {
                tourRoute.put(tourStop.getName(), tourStop);
                return true;
            }
        }
        return false;
    }

    public TourStop getTourStopByName(String name) {
        return tourRoute.get(name);
    }

    //MODIFIES: this
    //EFFECTS: sets isVisited field in the TourStop class to true
    public void markAsVisited(TourStop tourStop) {
        if (tourRoute.containsValue(tourStop)) {
            tourStop.visit();
            visitedRoute.put(tourStop.name, tourStop);
        }
    }


    public void displayNextStops() {
//        tourRoute.forEach((key, value)
//                -> System.out.println(value.getName() + " " + value.getTourStopType()));
    }

    public void displayVisitedStops() {
//        visitedRoute.forEach((key, value)
//                -> System.out.println("You visited the following "
//                +
//                value.getTourStopType() + " : " + value));
    }

    public boolean containsTourStop(TourStop tourStop) {
        return tourRoute.containsValue(tourStop);
    }


    public boolean containsVisitedTourStop(TourStop tourStop) {
        return visitedRoute.containsValue(tourStop);
    }

    public int tourLength() {
        return tourRoute.size();
    }


    public String recommendArea() {
        if (facultyOfInterest == "LFS"
                || facultyOfInterest == "Engineering"
                || facultyOfInterest == "Forestry"
                || facultyOfInterest == "KIN") {
            return "South";
        } else if (facultyOfInterest == "Science"
                || facultyOfInterest == "Education"
                || facultyOfInterest == "Busincess") {
            return "Center";
        } else if (facultyOfInterest == "Arts") {
            return "North";
        }
        return "North";
    }

    public void setFacultyOfInterest(String faculty) {
        facultyOfInterest = faculty;
    }


}
