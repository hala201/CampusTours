package presistence;

import model.FoodPlace;
import model.TourStop;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTourStop(String name, String area, TourStop tourStop) {
        assertEquals(name, tourStop.getName());
        assertEquals(area, tourStop.getArea());
    }
}
