package ui;

import model.*;

import java.io.IOException;
import java.util.Scanner;

public class CampusTourConsole {
    // function that displays information about my tour

    private Scanner input;
    private final TourRoute tourRoute = new TourRoute();

    public CampusTourConsole() throws IOException {
        runCampusTour();
    }

    private void runCampusTour() throws IOException {
        boolean keepGoing = true;
        String command = null;
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


    private void displayMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\ta -> add a tour stop");
        System.out.println("\tv -> visit tour stop");
        System.out.println("\tn -> view next stops");
        System.out.println("\tv -> view Visited stops");
        System.out.println("\tc -> allow customization according to faculty");
        System.out.println("\tq -> quit");

    }

    private void processCommand(String command) throws IOException {
        if (command.equals("a")) {
            addTourStop();
        }
        if (command.equals("v")) {
            visit();
        }
        if (command.equals("n")) {
            viewNextStops();
        }
        if (command.equals("v")) {
            viewVisitedStops();
        }
        if (command.equals("c")) {
            allowCustomization();
//        } else {
//            System.out.println("Not a valid selection...");
//        }
        }
    }

    public void allowCustomization() {
        System.out.println("Select your faculty of interest: ");
        System.out.println("\tl -> LFS");
        System.out.println("\tf -> Forestry");
        System.out.println("\te -> Engineering");
        System.out.println("\tk -> Kinesiology");
        System.out.println("\te -> education");
        System.out.println("\ts -> Science");
        System.out.println("\tb -> Business");
        System.out.println("\ta -> Arts");
        String chosenFaculty = input.next();
        if (chosenFaculty == "l" || chosenFaculty == "f" || chosenFaculty == "e"
                || chosenFaculty == "k") {
            tourRoute.setFacultyOfInterest("LFS");
            tourRoute.recommendArea();
        } else if (chosenFaculty == "s" || chosenFaculty == "b" || chosenFaculty == "e") {
            tourRoute.setFacultyOfInterest("Sciences");
            tourRoute.recommendArea();
        } else {
            tourRoute.setFacultyOfInterest("Arts");
            tourRoute.recommendArea();
        }
        System.out.println("Done! We will customize your added stops based on your interest!");
        displayMenu();
    }


    private void viewVisitedStops() {
        System.out.println("Stops you visited are: \n");
        tourRoute.displayVisitedStops();
    }

    private void viewNextStops() {
        System.out.println("Stops you are planning on visiting: \n");
        tourRoute.displayNextStops();
    }

    private void visit() {
        System.out.println("Enter name of stop to visit: ");
        String name = input.next();
        TourStop tourStop = tourRoute.getTourStopByName(name);
        tourRoute.markAsVisited(tourStop);
    }

    public void addTourStop() {
        if (tourRoute.maxSize > tourRoute.tourLength()) {
            System.out.println("Enter Name to add");
            String name = input.next();
            System.out.println("Choose the type of your stop: ");
            System.out.println("\tL for Library\tG for Garden\tM for Museum\tFB for Faculty Building");
            String chosenType = input.next();
            if (chosenType.equals("L")) {
                Library library = new Library(name, tourRoute.recommendArea());
                tourRoute.addTourStop(library);
            } else if (chosenType.equals("G")) {
                Garden garden = new Garden(name, tourRoute.recommendArea());
                tourRoute.addTourStop(garden);
            } else if (chosenType.equals("M")) {
                Museum museum = new Museum(name, tourRoute.recommendArea());
                tourRoute.addTourStop(museum);
            } else if (chosenType.equals("FB")) {
                FacultyBuilding facultyBuilding = new FacultyBuilding(name, tourRoute.recommendArea());
                tourRoute.addTourStop(facultyBuilding);
            }
            displayTourLength();
        } else {
            System.out.println("Exceeded the maximum tour length");
        }
        displayMenu();
    }

    public void displayTourLength() {
        System.out.println("You have " + tourRoute.tourLength() + " stops to visit");
    }


}

