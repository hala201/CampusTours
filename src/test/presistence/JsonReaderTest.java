package presistence;

import model.TourRoute;
import model.TourStop;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TourRoute tr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTourRoute() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTourRoute.json");
        try {
            TourRoute tr = reader.read();
            assertEquals("My tour route", tr.getName());
            assertEquals(0, tr.tourLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTourRoute() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTourRoute.json");
        try {
            TourRoute tr = reader.read();
            assertEquals("My tour route", tr.getName());
            List<TourStop> visited = new ArrayList<TourStop>(tr.getVisitedRoute().values());
            List<TourStop> unvisited = new ArrayList<TourStop>(tr.getToBeVisitedRoute().values());
            assertEquals(4, unvisited.size());
            checkTourStop("IKB", "North", unvisited.get(0));
            checkTourStop("Buchanan", "North" , unvisited.get(3));
            checkTourStop("Rose","North", unvisited.get(1));
            checkTourStop("MOA", "North", unvisited.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
