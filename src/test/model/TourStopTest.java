package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourStopTest {

    FacultyBuilding buchanan;
    Garden roseGarden;
    Library koerner;
    Museum museumOfAnthropology;
    TourStop ed;


    @BeforeEach
    public void setup() {
        buchanan = new FacultyBuilding("Buchanan Building", "North");
        roseGarden = new Garden("Rose Garden", "North");
        koerner = new Library("Koerner Library", "North");
        museumOfAnthropology = new Museum("Museum of Anthropology", "North");
        ed = new Library("UBC Education Library", "Center");
    }

    @Test
    public void testFoodPlaceConstructor() {
        FoodPlace foodPlace = new FoodPlace("Food Place");
        buchanan.setNearestFoodPlace(foodPlace);
        assertEquals(foodPlace, buchanan.placeToEat());
    }

    @Test
    public void testIsVisitedAndVisit() {
        assertFalse(ed.isVisited());
        ed.visit();
        assertTrue(ed.isVisited());
        assertFalse(ed.visit());
    }

    @Test
    public void testFacultyBuildingConstructor() {
        FoodPlace cafe = new FoodPlace("Stir It Up Cafe");
        buchanan.setNearestFoodPlace(cafe);
        assertEquals(buchanan.placeToEat(), cafe);
        assertEquals(buchanan.getName(), "Buchanan Building");
        assertEquals(buchanan.getArea(), "North");
        assertEquals(buchanan.getTourStopType(), "Faculty Building");
        assertFalse(buchanan.isVisited());
    }

    @Test
    public void testVisitFacultyBuilding() {
        buchanan.visit();
        assertTrue(buchanan.isVisited());
        assertFalse(buchanan.visit());
        buchanan.shouldRevisit();
        assertTrue(buchanan.isVisited());
        assertTrue(buchanan.visit());
    }

    @Test
    public void testGardenConstructor() {
        FoodPlace sage = new FoodPlace("Sage");
        roseGarden.setNearestFoodPlace(sage);
        assertEquals(roseGarden.placeToEat(), sage);
        assertEquals(roseGarden.getName(), "Rose Garden");
        assertEquals(roseGarden.getArea(), "North");
        assertEquals(roseGarden.getTourStopType(), "Garden");
        assertFalse(roseGarden.isVisited());
    }

    @Test
    public void testVisitGarden() {
        roseGarden.visit();
        assertTrue(roseGarden.isVisited());
        assertFalse(roseGarden.visit());
        roseGarden.shouldRevisit();
        assertTrue(roseGarden.isVisited());
        assertTrue(roseGarden.visit());
    }

    @Test
    public void testLibraryConstructor() {
        FoodPlace ike = new FoodPlace("Ike Cafe");
        koerner.setNearestFoodPlace(ike);
        assertEquals(koerner.placeToEat(), ike);
        assertEquals(koerner.getArea(), "North");
        assertEquals(koerner.getName(), "Koerner Library");
        assertEquals(koerner.getTourStopType(), "Library");
        assertFalse(koerner.isVisited());
    }

    @Test
    public void testVisitLibraryBuilding() {
        koerner.visit();
        assertTrue(koerner.isVisited());
        assertFalse(koerner.visit());
        koerner.shouldRevisit();
        assertTrue(koerner.isVisited());
        assertTrue(koerner.visit());
    }

    @Test
    public void testMuseumConstructor() {
        FoodPlace moa = new FoodPlace("Museum Of Anthropology Cafe");
        museumOfAnthropology.setNearestFoodPlace(moa);
        assertEquals(museumOfAnthropology.placeToEat(), moa);
        assertEquals(museumOfAnthropology.getName(), "Museum of Anthropology");
        assertEquals(museumOfAnthropology.getArea(), "North");
        assertEquals(museumOfAnthropology.getTourStopType(), "Museum");
        assertFalse(museumOfAnthropology.isVisited());
    }

    @Test
    public void testVisitMuseum() {
        museumOfAnthropology.visit();
        assertTrue(museumOfAnthropology.isVisited());
        assertFalse(museumOfAnthropology.visit());
        museumOfAnthropology.shouldRevisit();
        assertTrue(museumOfAnthropology.isVisited());
        assertTrue(museumOfAnthropology.visit());
    }

    @Test
    public void testShouldRevisit() {
        buchanan.visit();
        assertTrue(buchanan.isVisited());
        buchanan.shouldRevisit();
        assertTrue(buchanan.shouldRevisit);
    }

    @Test
    public void testShouldRevisitNotVisitedYet() {
        assertFalse(buchanan.isVisited());
        assertFalse(buchanan.shouldRevisit);
    }

}

