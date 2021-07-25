package model;

import java.util.Objects;

public abstract class TourStop {
    //Provides the specification required for a tour stop at ubc
    //it's  super type extended by different types of methods

    protected String name;
    protected FoodPlace nearestFoodPlace;
    protected boolean isVisited;
    protected boolean shouldRevisit;
    protected String tourStopType;


    //EFFECTS: make an unvisited tour stop
    public TourStop(String name, String area){}

    //setter
    public String getName(){
        return "";
    }


    //setter
    public void setNearestFoodPlace(FoodPlace foodPlace){}

    public String getNearestFoodPlace(){
        return "";
    }

    //getter
    public boolean isVisited() {
        return false;
    }

    //EFFECTS: if stop was not visited before, change status and return true
    //         if should be revisited, return true and false otherwise
    public boolean visit() {
        return false;
    }

    public void shouldRevisit() {}

    public abstract String getTourStopType();

    //EFFECTS: to check if two tour stops are the same
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (! (o instanceof TourStop)) {
            return false;
        }

        TourStop tourStop = (TourStop) o;
        return name.equals(tourStop.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public String getArea() {
        return "";
    }

    public FoodPlace placeToEat(){
        return null;
    }
}

