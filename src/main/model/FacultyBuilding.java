package model;

public class FacultyBuilding extends TourStop {

    public FacultyBuilding(String name, String location) {
        super(name, location);
    }

    @Override
    public String getTourStopType() {
        return "Faculty Building";
    }
}
