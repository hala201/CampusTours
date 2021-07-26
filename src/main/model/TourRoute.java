package model;

import java.util.HashMap;

public class TourRoute {
    String facultyOfInterest;

    protected HashMap<String, TourStop> unvisitedRoute;
    protected HashMap<String, TourStop> visitedRoute;
    public final int maxSize = 4;

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
        unvisitedRoute = new HashMap<>();
    }

    //REQUIRE: should not exceed the maximum number of tour stops
    //MODIFIES: this
    //EFFECTS: adds a tour stop to the list of tour stops, returns true if added successfully
    public boolean addTourStop(TourStop tourStop) {
        if (unvisitedRoute.size() < maxSize) {
            if (tourStop.getArea() == recommendArea()
                    && !containsTourStop(tourStop)) {
                unvisitedRoute.put(tourStop.getName(), tourStop);
                return true;
            }
        }
        return false;
    }

    public TourStop getTourStopByName(String name) {
        return unvisitedRoute.get(name);
    }

    //MODIFIES: this
    //EFFECTS: sets isVisited field in the TourStop class to true
    public boolean markAsVisited(TourStop tourStop) {
        if (unvisitedRoute.containsValue(tourStop)) {
            tourStop.visit();
            visitedRoute.put(tourStop.name, tourStop);
            return true;
        }
        return false;
    }


    public boolean containsTourStop(TourStop tourStop) {
        return unvisitedRoute.containsValue(tourStop);
    }


    public boolean containsVisitedTourStop(TourStop tourStop) {
        return visitedRoute.containsValue(tourStop);
    }

    public int tourLength() {
        return unvisitedRoute.size();
    }


    public String recommendArea() {
        if (facultyOfInterest == "LFS"
                || facultyOfInterest == "Engineering"
                || facultyOfInterest == "Forestry"
                || facultyOfInterest == "Kinesiology") {
            return "South";
        } else if (facultyOfInterest == "Science"
                || facultyOfInterest == "Education"
                || facultyOfInterest == "Business") {
            return "Center";
        } else if (facultyOfInterest == "Arts") {
            return "North";
        }
        return "North";
    }

    public void setFacultyOfInterest(String faculty) {
        facultyOfInterest = faculty;
    }

    public String getFacultyOfInterest() {
        return facultyOfInterest;
    }

    public HashMap<String, TourStop> getUnvisitedRoute() {
        return unvisitedRoute;
    }

    public HashMap<String, TourStop> getVisitedRoute() {
        return visitedRoute;
    }


}
