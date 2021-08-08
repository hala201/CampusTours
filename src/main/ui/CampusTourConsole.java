package ui;

import model.*;
import presistence.JsonReader;
import presistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CampusTourConsole {
    // my UBC campus tour console application

    private static final String JSON_STORE = "./data/tourRoute.json";
    private Scanner input;
    private TourRoute tourRoute;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the campus tour application
    public CampusTourConsole() throws IOException {
        input = new Scanner(System.in);
        tourRoute = new TourRoute("My tour route");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCampusTour();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void runCampusTour() throws IOException {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        displayMenu();

        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you in an upcoming tour!");
    }


    //EFFECTS: displays options menu for user
    private void displayMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\ta -> Add a tour stop");
        System.out.println("\tt -> Start tour and visit stop");
        System.out.println("\tn -> view Next stops");
        System.out.println("\tv -> view Visited stops");
        System.out.println("\tc -> allow Customization according to faculty");
        System.out.println("\ts -> save tour route to file");
        System.out.println("\tl -> load tour route from file");
        System.out.println("\tq -> quit");

    }

    //MODIFIES: this
    //EFFECTS: processes user commands
    private void processCommand(String command) {
        if ("a".equals(command)) {
            addTourStop();
        } else if ("t".equals(command)) {
            visit();
        } else if ("n".equals(command)) {
            viewNextStops();
        } else if ("v".equals(command)) {
            viewVisitedStops();
        } else if ("s".equals(command)) {
            saveTourRoute();
        } else if ("l".equals(command)) {
            loadTourRoute();
        } else if ("c".equals(command)) {
            allowCustomization();
        } else {
            System.out.println("Not a valid selection... refer to menu above and select again");
        }

    }

    //MODIFIES: this
    //EFFECTS: specifies the area of stops based on faculty of interest
    public void allowCustomization() {
        System.out.println("Select your faculty of interest: ");
        System.out.println("\tl -> LFS");
        System.out.println("\tf -> Forestry");
        System.out.println("\te -> Engineering");
        System.out.println("\tk -> Kinesiology");
        System.out.println("\ted -> education");
        System.out.println("\ts -> Science");
        System.out.println("\tb -> Business");
        System.out.println("\ta -> Arts");
        String chosenFaculty = input.next();
        if (chosenFaculty == "l" || chosenFaculty == "f" || chosenFaculty == "e"
                || chosenFaculty == "k") {
            tourRoute.setFacultyOfInterest("LFS");
            tourRoute.recommendArea();
        } else if (chosenFaculty == "s" || chosenFaculty == "b" || chosenFaculty == "ed") {
            tourRoute.setFacultyOfInterest("Sciences");
            tourRoute.recommendArea();
        } else {
            tourRoute.setFacultyOfInterest("Arts");
            tourRoute.recommendArea();
        }
        System.out.println("Done! We will customize your added stops based on your indicated interest!");
        displayMenu();

    }

    //EFFECTS: displays the visited tour stops
    private void viewVisitedStops() {
        if (tourRoute.getVisitedRoute().size() == 0) {
            System.out.println("You haven't visited any stops yet");
        } else {
            System.out.println("Stops you visited are: \n");
            tourRoute.getVisitedRoute().forEach((key, value)
                    -> System.out.println(value.getName() + " " + value.getTourStopType()));
        }
    }

    //EFFECTS: displays the stops to be visited
    private void viewNextStops() {
        if (tourRoute.tourLength() == 0) {
            System.out.println("You haven't added any stops yet");
        } else {
            System.out.println("Stops you are planning on visiting: \n");
            tourRoute.getToBeVisitedRoute().forEach((key, value)
                    -> System.out.println(value.getName() + " " + value.getTourStopType()));
        }
    }

    //MODIFIES: this
    //EFFECTS: marks the entered stop as visited and gives the tour stop details
    private void visit() {
        System.out.println("Enter name of stop to visit: ");
        String name = input.next();
        TourStop tourStop = tourRoute.getTourStopByName(name);
        if (tourRoute.containsTourStop(tourStop)) {
            tourRoute.markAsVisited(tourStop);
            // more functionality will be added, needs external data
            System.out.println("You are currently visiting " + name + " "
                    +
                    tourStop.getTourStopType() + ".....");
            System.out.println("Press 'e' to end the tour in this stop");
            String isEnded = input.next();
            if (isEnded.equals("e")) {
                System.out.println("We hope you enjoyed exploring " + name + "!!");
                displayMenu();
            }
        } else {
            System.out.println("please add this stop first");
            displayMenu();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the entered tour stop if it wouldn't exceed maximum tour length
    public void addTourStop() {
        if (tourRoute.maxStopsCount > tourRoute.tourLength()) {
            System.out.println("Enter Name to add");
            String name = input.next();
            System.out.println("Choose the type of your stop: ");
            System.out.println("\tL for Library\tG for Garden\tM for Museum\tFB for Faculty Building");
            String chosenType = input.next();
            specifyStopType(chosenType, name);
            displayTourLength();
        } else {
            System.out.println("Exceeded the maximum tour length");
        }
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS: constructs the specific tour stop type, helper to addTourStop
    private void specifyStopType(String chosenType, String name) {
        switch (chosenType) {
            case "L":
                Library library = new Library(name, tourRoute.recommendArea());
                tourRoute.addTourStop(library);
                break;
            case "G":
                Garden garden = new Garden(name, tourRoute.recommendArea());
                tourRoute.addTourStop(garden);
                break;
            case "M":
                Museum museum = new Museum(name, tourRoute.recommendArea());
                tourRoute.addTourStop(museum);
                break;
            case "FB":
                FacultyBuilding facultyBuilding = new FacultyBuilding(name, tourRoute.recommendArea());
                tourRoute.addTourStop(facultyBuilding);
                break;
            default:
                System.out.println("Not a valid selection...");
                break;
        }
    }

    //EFFECTS: displays the current tour length
    public void displayTourLength() {
        System.out.println("You have " + tourRoute.tourLength() + " stops to visit");
    }

    // EFFECTS: saves tour route to file
    public void saveTourRoute() {
        try {
            jsonWriter.open();
            jsonWriter.write(tourRoute);
            jsonWriter.close();
            System.out.println("Saved " + tourRoute.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads tour route from file
    public void loadTourRoute() {
        try {
            tourRoute = jsonReader.read();
            System.out.println("Loaded " + tourRoute.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    public void setTourRoute(TourRoute tourRoute) {
//        this.tourRoute = tourRoute;
//    }
}

