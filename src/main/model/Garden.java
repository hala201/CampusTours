package model;

public class Garden extends TourStop {
    //subclass of TourStop of type Garden


    public Garden(String name, String location) {
        super(name, location);
    }

    //EFFECTS: returns the tour stop type "Garden"
    @Override
    public String getTourStopType() {
        return "Garden";
    }
}
