package model;

public class TourStop {
    //UBC campus places available to be visited during the tour
    //Superclass that has generic methods that apply to all tour stops

    String name;
    String location;
    int recommendedDurationOfVisit;
    FoodPlace nearestFoodPlace;
    boolean isVisited;
    boolean shouldRevisit;

    //EFFECTS: make an unvisited tour stop
    public void tourStop(String name, String location){}

    //setter
    public void setName(){}

    //setter
    public void setLocation(){}

    //setter
    public void setRecommendedDurationOfVisit(){}

    //setter
    public void setNearestFoodPlace(){}

    //getter
    public boolean getIsVisited() {
        return false;
    }

    //setter
    public void setVisited() {}

    //getter
    public boolean getShouldRevisit() {
        return false;
    }

    //setter
    public void setShouldRevisit() {}
}
