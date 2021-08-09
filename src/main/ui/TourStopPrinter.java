package ui;

import model.TourStop;

import java.util.HashMap;
import java.util.Map;

//Class to print out the list of stops using string buffer
public class TourStopPrinter {
    //This class implementation was done by the help of StackOverflow
    //EFFECTS: print out the list
    public static String printList(HashMap<String, TourStop> tourStops) {
        StringBuffer sbf = new StringBuffer();
        for (Map.Entry<String, TourStop> entry : tourStops.entrySet()) {
            sbf.append(entry.getKey())
                    .append(" ").append(entry.getValue().getTourStopType())
                    .append("\n");
        }
        return sbf.toString();
    }
}
