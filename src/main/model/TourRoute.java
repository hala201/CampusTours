package model;

import org.json.JSONArray;
import org.json.JSONObject;
import presistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TourRoute implements Writable {
    // A list of tour stops

    protected List<TourStop> toBeVisitedRoute;
    protected List<TourStop> visitedRoute;
    public final int maxStopsCount = 4;
    protected String facultyOfInterest;
    protected String name;

    //EFFECTS: make a list of the visited and to be visited tour stops
    public TourRoute(String name) {
        visitedRoute = new ArrayList<>();
        toBeVisitedRoute = new ArrayList<>();
        this.name = name;
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
                toBeVisitedRoute.add(tourStop);
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
        if (toBeVisitedRoute.contains(tourStop)) {
            tourStop.visit();
            visitedRoute.add(tourStop);
            return true;
        }
        return false;
    }

    //EFFECTS: return true if given tour stop is in toBeVisitedRoute and false otherwise
    public boolean containsTourStop(TourStop tourStop) {
        return toBeVisitedRoute.contains(tourStop);
    }

    //EFFECTS: return true if given tour stop is in unvisitedRoute and false otherwise
    public boolean containsVisitedTourStop(TourStop tourStop) {
        return visitedRoute.contains(tourStop);
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

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Visited Tour Stops", visitedTourStopsToJson());
        json.put("Unvisited Tour Stops", unvisitedTourStopsToJson());
        json.put("name", name);
        return json;
    }

    //EFFECTS: returns tour stops in visited tour route as a JSON array
    private JSONArray visitedTourStopsToJson() {
        JSONArray jsonArray = new JSONArray();

        visitedRoute.forEach((key, value)
                -> jsonArray.put(value.toJson()));

        return jsonArray;
    }

    //EFFECTS: returns tour stops in unvisited tour route as a JSON array
    private JSONArray unvisitedTourStopsToJson() {
        JSONArray jsonArray = new JSONArray();

        toBeVisitedRoute.forEach((key, value)
                -> jsonArray.put(value.toJson()));

        return jsonArray;
    }

}
