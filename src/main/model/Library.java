package model;

public class Library extends TourStop {
    public Library(String name, String location) {
        super(name, location);
    }

    @Override
    public String getTourStopType() {
        return "Library";
    }
}
