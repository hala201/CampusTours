package model;

public class Museum extends TourStop {
    //subclass of TourStop of type Museum

    public Museum(String name, String location) {
        super(name, location);
    }
    //EFFECTS: returns the tour stop type "Museum"
    @Override
    public String getTourStopType() {
        return "Museum";
    }
}
