package presistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            TourRoute tr = new TourRoute("My tour route");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTourRoute() {
        try {
            TourRoute tr = new TourRoute("My tour route");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTourRoute.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTourRoute.json");
            assertEquals("My tour route", tr.getName());
            assertEquals(0, tr.tourLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTourRoute() {
        try {
            TourRoute tr = new TourRoute("My tour route");
            tr.addTourStop(new Library("IKB", "North"));
            tr.addTourStop(new FacultyBuilding("Buchanan", "North"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTourRoute.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTourRoute.json");
            tr = reader.read();
            assertEquals("My tour route", tr.getName());
            HashMap<String, TourStop> visited = tr.getVisitedRoute();
            HashMap<String, TourStop> unvisited = tr.getToBeVisitedRoute();

//            assertEquals(2, unvisited.size());
//            checkTourStop("IKB", "North", unvisited.get(0));
//            checkTourStop("Buchanan", "North", unvisited.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
