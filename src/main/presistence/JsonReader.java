package presistence;

import model.FacultyBuilding;
import model.TourRoute;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.TourStop;
import org.json.*;


// Represents a reader that reads tourRoute from JSON data stored in file
public class JsonReader {

    private String source;

    //EFFECTS: constructs a reader tp read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads tourRoute from file and returns it;
    //throws IOException if an error occurs reading data from file
    public TourRoute read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTourRoute(jsonObject);
    }

    //EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses tourRoute from JSON object and return it
    private TourRoute parseTourRoute(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TourRoute tr = new TourRoute(name);
        addTourStops(tr, jsonObject);
        return tr;
    }

    //MODIFIES: this
    //EFFECTS: parses tour stops from JSON object and adds them to tour route
    private void addTourStops(TourRoute tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("UnvisitedRoute");
        for (Object json : jsonArray) {
            JSONObject nextTourStop = (JSONObject) json;
            addTourStop(tr, nextTourStop);
        }
    }

    //MODIFIES: tr
    //EFFECTS: parses tour stop from JSON Object and adds it to tour route
    private void addTourStop(TourRoute tr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = String.valueOf(jsonObject.getString("area"));
        TourStop tourStop = new FacultyBuilding(name, location);
        tr.addTourStop(tourStop);
    }
}

