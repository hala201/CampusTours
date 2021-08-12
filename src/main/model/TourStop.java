package model;

import org.json.JSONObject;
import presistence.Writable;


public abstract class TourStop implements Writable {
    //Supertype extended by four types
    //Represents spots on campus worth checking out

    //fields protected allowing access to subtypes
    protected String name;
    protected String area;
    protected FoodPlace nearestFoodPlace;
    protected boolean isVisited;
    protected boolean shouldRevisit;


    //EFFECTS: make an unvisited tour stop
    public TourStop(String name, String area) {
        this.name = name;
        this.area = area;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public String getArea() {
        return area;
    }

    // setter
    public void setNearestFoodPlace(FoodPlace foodPlace) {
        this.nearestFoodPlace = foodPlace;
    }

    //EFFECTS: returns the nearest food place to eat
    public FoodPlace placeToEat() {
        return nearestFoodPlace;
    }

    //EFFECTS: returns true if tour stop was visited before and false otherwise
    public boolean isVisited() {
        return isVisited;
    }

    // will have more functionality in upcoming phase
    //MODIFIES: this
    //EFFECTS: returns true and marks as visited if tour stop should be revisited or was not visited yet
    //         and false otherwise
    public boolean visit() {
        if (shouldRevisit || !isVisited) {
            isVisited = true;
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: marks a visited stop to be revisited
    public void shouldRevisit() {
        if (isVisited) {
            shouldRevisit = true;
        }
    }

    //EFFECTS: returns the tour stop type
    public abstract String getTourStopType();

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("area", area);
        json.put("type", getTourStopType());
        return json;
    }

}

