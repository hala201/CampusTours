package model;

import java.util.HashMap;

public class TourRoute {
    // A list of tour stops

    protected HashMap<String, TourStop> toBeVisitedRoute;
    protected HashMap<String, TourStop> visitedRoute;
    public final int maxStopsCount = 4;
    protected String facultyOfInterest;

    //EFFECTS: make a list of the visited and to be visited tour stops
    public TourRoute() {
        visitedRoute = new HashMap<>();
        toBeVisitedRoute = new HashMap<>();
    }

    // will handle with an exception
    //REQUIRE: toBeVisitedRoute.size() < maxStopsCount
    //MODIFIES: this
    //EFFECTS: adds tour stop to the list of upcoming tour stops
    //        if tourStop is not be already in the list and
    //        its location is in the recommended area
    //        returns true if added successfully and false otherwise
    public boolean addTourStop(TourStop tourStop) {
        if (toBeVisitedRoute.size() < maxStopsCount) {
            if (tourStop.getArea() == recommendArea()
                    && !containsTourStop(tourStop)) {
                toBeVisitedRoute.put(tourStop.getName(), tourStop);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the TourStop in toBeVisited given its name
    public TourStop getTourStopByName(String name) {
        return toBeVisitedRoute.get(name);
    }

    //MODIFIES: this
    //EFFECTS: sets isVisited field in the TourStop class to true
    public boolean markAsVisited(TourStop tourStop) {
        if (toBeVisitedRoute.containsValue(tourStop)) {
            tourStop.visit();
            visitedRoute.put(tourStop.name, tourStop);
            return true;
        }
        return false;
    }

    //EFFECTS: return true if given tour stop is in toBeVisitedRoute and false otherwise
    public boolean containsTourStop(TourStop tourStop) {
        return toBeVisitedRoute.containsValue(tourStop);
    }

    //EFFECTS: return true if given tour stop is in unvisitedRoute and false otherwise
    public boolean containsVisitedTourStop(TourStop tourStop) {
        return visitedRoute.containsValue(tourStop);
    }

    //EFFECTS: return the number of stops in the tour route
    public int tourLength() {
        return toBeVisitedRoute.size();
    }

    // EFFECTS: returns the area of most relevant tour stops to faculty of interest
    //          if there is no preference, returns "North" where more diverse tour stops are
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

    // setter
    public void setFacultyOfInterest(String faculty) {
        facultyOfInterest = faculty;
    }

    // getter
    public String getFacultyOfInterest() {
        return facultyOfInterest;
    }

    //EFFECTS: returns tour stops to be visited
    public HashMap<String, TourStop> getToBeVisitedRoute() {
        return toBeVisitedRoute;
    }

    //EFFECTS: returns visited tour stops
    public HashMap<String, TourStop> getVisitedRoute() {
        return visitedRoute;
    }
}
