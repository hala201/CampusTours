package presistence;

import model.TourRoute;
import model.TourStop;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;


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
            HashMap<String, TourStop> visited = tr.getVisitedRoute();
            HashMap<String,TourStop> unvisited = tr.getToBeVisitedRoute();
            assertEquals(0, visited.size());
            assertEquals(2, tr.tourLength());
            checkTourStop("Buchanan", "North" , unvisited.get(0));
            checkTourStop("IKB", "North", unvisited.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
