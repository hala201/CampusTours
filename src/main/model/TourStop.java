package model;

import java.util.Objects;

public abstract class TourStop {
    //Provides the specification required for a tour stop at ubc
    //it's  super type extended by different types of methods

    protected String name;
    protected String area;
    protected FoodPlace nearestFoodPlace;
    protected boolean isVisited;
    protected boolean shouldRevisit;



    //EFFECTS: make an unvisited tour stop
    public TourStop(String name, String area) {
        this.name = name;
        this.area = area;
        isVisited = false;
        shouldRevisit = false;
    }

    //setter
    public String getName() {
        return name;
    }


    //setter
    public void setNearestFoodPlace(FoodPlace foodPlace) {
        this.nearestFoodPlace = foodPlace;
    }

    public String getArea() {
        return area;
    }

    public FoodPlace placeToEat() {
        return nearestFoodPlace;
    }

    //getter
    public boolean isVisited() {
        return isVisited;
    }

    //EFFECTS: if stop was not visited before, change status and return true
    //         if should be revisited, return true and false otherwise
    public boolean visit() {
        if (shouldRevisit || !isVisited) {
            isVisited = true;
            return true;
        }
        return false;
    }

    public void shouldRevisit() {
        if (isVisited) {
            shouldRevisit = true;
        }
    }

    public abstract String getTourStopType();


}

