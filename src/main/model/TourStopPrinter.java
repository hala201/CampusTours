package model;

import java.util.HashMap;
import java.util.Map;

public class TourStopPrinter {
    //This class implementation was done by the help of StackOverflow
    //EFFECTS: print out the list
    public static String printList(HashMap<String, TourStop> tourStops) {
        StringBuffer sbf = new StringBuffer();
        for (Map.Entry<String, TourStop> entry : tourStops.entrySet()) {
            sbf.append("Name: ").append(entry.getKey())
                    .append("   Type: ").append(entry.getValue().getTourStopType());
        }
        return sbf.toString();
    }
}
