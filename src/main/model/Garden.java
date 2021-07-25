package model;

public class Garden extends TourStop {


    public Garden(String name, String location) {
        super(name, location);
    }

    @Override
    public String getTourStopType() {
        return "";
    }
}
