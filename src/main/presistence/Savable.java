package presistence;

import model.TourRoute;

import java.io.IOException;

public interface Savable {
    void save(TourRoute route) throws IOException;
}
