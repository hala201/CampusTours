package model;

public class Museum extends TourStop {
    public Museum(String name, String location) {
        super(name, location);
    }

    @Override
    public String getTourStopType() {
        return "";
    }
}
