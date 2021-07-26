package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourRouteTest {
    TourRoute tourRoute;

    @BeforeEach
    public void setup() {
        tourRoute =  new TourRoute();
    }

    @Test
    public void testTourRouteConstructor(){
        assertEquals(tourRoute.tourLength(), 0);
    }

    @Test
    public void testAddTourStop() {
        TourStop ikb = new Library("IKB", "North");
        assertFalse(tourRoute.containsTourStop(ikb));
        tourRoute.addTourStop(ikb);
        assertTrue(tourRoute.containsTourStop(ikb));
        assertFalse(tourRoute.containsVisitedTourStop(ikb));
    }

    @Test
    public void testMarkAsVisited() {
        TourStop ikb = new Library("IKB", "North");
        assertFalse(tourRoute.containsTourStop(ikb));
        assertTrue(tourRoute.addTourStop(ikb));
        tourRoute.markAsVisited(ikb);
        assertTrue(tourRoute.containsVisitedTourStop(ikb));
    }

    @Test
    public void testMarkAsVisitedMultiple() {
        TourStop buchanan = new FacultyBuilding("Buchanan Building", "North");
        TourStop roseGarden = new Garden("Rose Garden", "North");
        TourStop koerner = new Library("Koerner Library", "North");
        TourStop museumOfAnthropology = new Museum("Museum of Anthropology", "North");

        tourRoute.addTourStop(buchanan);
        tourRoute.addTourStop(roseGarden);
        tourRoute.addTourStop(koerner);
        tourRoute.addTourStop(museumOfAnthropology);

        tourRoute.markAsVisited(buchanan);
        tourRoute.markAsVisited(roseGarden);

        assertTrue(tourRoute.containsVisitedTourStop(buchanan)
        && tourRoute.containsVisitedTourStop(roseGarden));

//        assertEquals(tourRoute.displayVisitedStops(),
//                "Buchanan Building, Rose Garden");
//        assertEquals(tourRoute.displayTourRoute(), "" +
//                "Koerner Library, Museum Of Anthropology");
    }

    @Test
    public void testAddMultipleStops() {
        TourStop buchanan = new FacultyBuilding("Buchanan Building", "North");
        TourStop roseGarden = new Garden("Rose Garden", "North");
        TourStop koerner = new Library("Koerner Library", "North");
        TourStop museumOfAnthropology = new Museum("Museum of Anthropology", "North");
        TourStop musicBuilding = new FacultyBuilding("Music Building", "North");

        assertEquals(tourRoute.tourLength(), 0);

        assertTrue(tourRoute.addTourStop(buchanan));
        tourRoute.addTourStop(roseGarden);
        tourRoute.addTourStop(koerner);
        tourRoute.addTourStop(museumOfAnthropology);

        assertEquals(tourRoute.tourLength(),4);

        assertTrue(tourRoute.addTourStop(musicBuilding));
    }

    @Test
    public void testAddUnrecommendedStops() {
        TourStop buchanan = new FacultyBuilding("Buchanan Building", "North");
        TourStop roseGarden = new Garden("Rose Garden", "North");
        TourStop koerner = new Library("Koerner Library", "North");
        TourStop museumOfAnthropology = new Museum("Museum of Anthropology", "North");
        TourStop ed = new Library("UBC Education Library", "Center");

        assertEquals(tourRoute.tourLength(), 0);

        tourRoute.addTourStop(buchanan);
        tourRoute.addTourStop(roseGarden);
        tourRoute.addTourStop(koerner);
        tourRoute.addTourStop(museumOfAnthropology);

        assertEquals(tourRoute.tourLength(),4);

        assertFalse(tourRoute.addTourStop(ed));
    }
}
