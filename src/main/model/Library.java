package model;

public class Library extends TourStop {
    //subclass of TourStop of type Library

    public Library(String name, String location) {
        super(name, location);
    }
    //EFFECTS: returns the tour stop type "Library"
    @Override
    public String getTourStopType() {
        return "Library";
    }
}
