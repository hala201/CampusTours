package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourRouteTest {
    TourRoute tourRoute;

    @BeforeEach
    public void setup() {
        tourRoute = new TourRoute();
    }

    @Test
    public void testTourRouteConstructor() {
        assertEquals(tourRoute.tourLength(), 0);
    }

    @Test
    public void testAddTourStop() {
        TourStop ikb = new Library("IKB", "North");
        assertFalse(tourRoute.containsTourStop(ikb));
        tourRoute.addTourStop(ikb);
        assertTrue(tourRoute.containsTourStop(ikb));
        assertFalse(tourRoute.containsVisitedTourStop(ikb));
        assertEquals(tourRoute.tourLength(), 1);

    }

    @Test
    public void testAddTourStopAlreadyAdded() {
        TourStop tourStop = new Library("IKB", "North");
        assertTrue(tourRoute.addTourStop(tourStop));
        assertFalse(tourRoute.addTourStop(tourStop));
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

        assertTrue(tourRoute.containsTourStop(buchanan)
        && tourRoute.containsTourStop(roseGarden));

        assertTrue(tourRoute.markAsVisited(buchanan)
        && tourRoute.markAsVisited(roseGarden));

        assertTrue(tourRoute.containsVisitedTourStop(buchanan)
                && tourRoute.containsVisitedTourStop(roseGarden));


    }

    @Test
    public void testMarkAsVisitedNotVisited() {
        TourStop tourStop = new Library("IKB", "North");
        assertFalse(tourRoute.containsVisitedTourStop(tourStop));
        assertFalse(tourRoute.markAsVisited(tourStop));
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

        assertEquals(tourRoute.tourLength(), 4);

        assertFalse(tourRoute.addTourStop(musicBuilding));
    }

    @Test
    public void testAddUnrecommendedStops() {
        TourStop buchanan = new FacultyBuilding("Buchanan Building", "North");
        TourStop roseGarden = new Garden("Rose Garden", "North");
        TourStop museumOfAnthropology = new Museum("Museum of Anthropology", "North");
        TourStop ed = new Library("UBC Education Library", "Center");

        assertEquals(tourRoute.tourLength(), 0);

        tourRoute.addTourStop(buchanan);
        tourRoute.addTourStop(roseGarden);
        tourRoute.addTourStop(museumOfAnthropology);

        assertEquals(tourRoute.tourLength(), 3);

        assertFalse(tourRoute.addTourStop(ed));
    }

    @Test
    public void testExceedLimit() {
        TourStop buchanan = new FacultyBuilding("Buchanan Building", "North");
        TourStop roseGarden = new Garden("Rose Garden", "North");
        TourStop koerner = new Library("Koerner Library", "North");
        TourStop museumOfAnthropology = new Museum("Museum of Anthropology", "North");
        TourStop ed = new Library("UBC Education Library", "North");

        assertEquals(tourRoute.tourLength(), 0);

        tourRoute.addTourStop(buchanan);
        tourRoute.addTourStop(roseGarden);
        tourRoute.addTourStop(koerner);
        tourRoute.addTourStop(museumOfAnthropology);

        assertEquals(tourRoute.tourLength(), 4);
        assertTrue(tourRoute.tourLength() >= tourRoute.maxSize);

        assertFalse(tourRoute.addTourStop(ed));
    }

    @Test
    public void testRecommendAre() {
        tourRoute.setFacultyOfInterest("LFS");
        assertEquals(tourRoute.recommendArea(), "South");
        tourRoute.setFacultyOfInterest("Engineering");
        assertEquals(tourRoute.recommendArea(), "South");
        tourRoute.setFacultyOfInterest("Kinesiology");
        assertEquals(tourRoute.recommendArea(), "South");
        tourRoute.setFacultyOfInterest("Forestry");
        assertEquals(tourRoute.recommendArea(), "South");

        tourRoute.setFacultyOfInterest("Science");
        assertEquals(tourRoute.recommendArea(), "Center");
        tourRoute.setFacultyOfInterest("Education");
        assertEquals(tourRoute.recommendArea(), "Center");
        tourRoute.setFacultyOfInterest("Business");
        assertEquals(tourRoute.recommendArea(), "Center");

        tourRoute.setFacultyOfInterest("Arts");
        assertEquals(tourRoute.recommendArea(), "North");
        tourRoute.setFacultyOfInterest(null);
        assertEquals(tourRoute.recommendArea(), "North");
    }

    @Test
    public void testGetTourStopByName() {
        TourStop tourStop = new Library("IKB", "North");
        tourRoute.addTourStop(tourStop);
        assertEquals(tourStop, tourRoute.getTourStopByName("IKB"));
    }

    @Test
    public void testSetFacultyOfInterest() {
        tourRoute.setFacultyOfInterest("LFS");
        assertEquals(tourRoute.getFacultyOfInterest(), "LFS");
    }
}
