package model;

public class FacultyBuilding extends TourStop {
    // subclass of TourStop of type Faculty Building

    public FacultyBuilding(String name, String location) {
        super(name, location);
    }


    //EFFECTS: returns the tour stop type "Faculty Building"
    @Override
    public String getTourStopType() {
        return "Faculty Building";
    }
}
