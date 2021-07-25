package presistence;

import model.TourRoute;

import java.io.IOException;

public interface Loadable {

    void load(TourRoute route) throws IOException;
}
