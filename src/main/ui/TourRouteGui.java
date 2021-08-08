package ui;

import model.*;
import presistence.JsonReader;
import presistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//GUI for TourRoute
public class TourRouteGui extends JPanel {
    // Adapted from Oracle's list swing tutorials
    // provided on the course' edx webpage

    private TourRoute tourRoute = new TourRoute("My tour Route");
    private String printVisitedStops = " ";

    protected JsonReader jsonReader = new JsonReader(JSON_STORE);
    protected JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private static final String JSON_STORE = "./data/tourRoute.json";


    JButton addTourStopButton;
    JButton loadTourStopButton;
    JButton saveTourStopButton;
    JButton visitTourStopButton;
    JButton viewNextButton;
    JButton viewVisitedButton;

    protected JTextField tourStopName;
    protected JTextField tourStopType;
    protected JTextField tourStopLocation;

    protected JLabel topTitle;
    protected JLabel tourStopTitle;
    protected JLabel tourStopTypeTitle;
    protected JLabel tourStopLocationTitle;
    protected JLabel imageLabel;
    protected JLabel tourRouteTitle;

    protected JPanel title;
    protected JPanel tourStopSpecification;
    protected JPanel interactionButtons;
    protected JPanel tourRouteViewer;

    private static final Color background1 = new Color(12, 35, 68);
    private static final Color textPanelColor = new Color(255, 255, 255);
    private JTextArea textArea;

    //EFFECTS: creates and initializes task Manager Panel and Functionality

    TourRouteGui() {
        setPreferredSize(new Dimension(500, 600));
        setBorder(new EmptyBorder(13, 13, 13, 13));
        setBackground(background1);
        setLayout(new GridLayout(4, 1));
        GridBagConstraints c = new GridBagConstraints();
        buildTitle();
        buildTourStopSpecification();
        buildTourRouteViewer();
        buildInteractionButtons();
        buttonCalls();
        add(title);
        add(tourStopSpecification);
        add(tourRouteViewer);
        add(interactionButtons);
        setVisible(true);
    }

    private void buildInteractionButtons() {
        initializeInteractionFields();
        Font font = new Font("Whitney", Font.BOLD, 14);
        this.visitTourStopButton.setFont(font);
        this.loadTourStopButton.setFont(font);
        this.saveTourStopButton.setFont(font);
        this.viewNextButton.setFont(font);
        this.viewVisitedButton.setFont(font);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.interactionButtons.add(visitTourStopButton, c);

        c.gridx = 1;
        c.gridy = 0;
        this.interactionButtons.add(saveTourStopButton, c);

        c.gridx = 0;
        c.gridy = 1;
        this.interactionButtons.add(loadTourStopButton, c);


        c.gridx = 1;
        c.gridy = 1;
        this.interactionButtons.add(viewNextButton, c);

        c.gridx = 2;
        c.gridy = 0;
        this.interactionButtons.add(viewVisitedButton, c);
    }

    private void initializeInteractionFields() {
        this.interactionButtons = new JPanel();
        interactionButtons.setBackground(background1);
        this.interactionButtons.setLayout(new GridBagLayout());
        this.visitTourStopButton = new JButton("Visit Tour Stop");
        this.loadTourStopButton = new JButton("Load Tour Route");
        this.saveTourStopButton = new JButton("Save Tour Route");
        this.viewVisitedButton = new JButton("View Upcoming Tour Route");
        this.viewNextButton = new JButton("View Visited Tour Route");
    }

    private void buildTourRouteViewer() {
        this.tourRouteViewer = new JPanel();
        tourRouteViewer.setBackground(textPanelColor);
        Font font = new Font("Century Schoolbook", Font.BOLD, 15);
        Font font2 = new Font("Times New Roman", Font.BOLD, 15);
        tourRouteViewer.setLayout(new GridLayout(2, 1, 0, 10));
        this.tourStopTitle = new JLabel("My Upcoming Tour Route:");
        textArea = new JTextArea();
        this.tourRouteTitle.setFont(font);
        textArea.setFont(font2);
        textArea.append(tourRoute.printNextStops());
        JScrollPane scroll = new JScrollPane(textArea);
        textArea.setBackground(Color.WHITE);
        tourRouteTitle.setHorizontalAlignment(JLabel.CENTER);
        tourRouteViewer.add(tourRouteTitle);
        tourRouteViewer.add(scroll);
    }

    // EFFECTS: creates a layout for the buttons
    private void buildTourStopSpecification() {
        placeButtons();
        tourStopSpecification.setBackground(background1);
        placeSpecificationElements();
    }

    private void placeSpecificationElements() {
        GridBagConstraints g = new GridBagConstraints();

        g.gridx = 0;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopTitle, g);

        g.gridx = 1;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopTypeTitle, g);

        g.gridx = 2;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopLocationTitle, g);

        g.gridx = 0;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopName, g);

        g.gridx = 1;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopType, g);

        g.gridx = 2;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopLocation, g);

        g.gridwidth = 2;
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 2;
        this.tourStopSpecification.add(this.addTourStopButton, g);

    }

    private void placeButtons() {
        this.tourStopSpecification = new JPanel();
        this.tourStopSpecification.setLayout(new GridBagLayout());
        this.tourStopName = new JTextField(10);
        this.tourStopType = new JTextField(10);
        this.tourStopLocation = new JTextField(10);
        this.addTourStopButton = new JButton("Add Task");
        this.tourStopTitle = new JLabel("Task Name");
        this.tourStopTypeTitle = new JLabel("Task Type");
        this.tourStopLocationTitle = new JLabel("Tour Stop Type");

        Font font1 = new Font("arial", Font.BOLD, 15);
        Font font2 = new Font("arial", Font.ITALIC, 15);

        this.tourStopTitle.setFont(font1);
        this.tourStopTypeTitle.setFont(font1);
        this.tourStopLocationTitle.setFont(font1);

        this.addTourStopButton.setFont(font2);
    }


    private void buildTitle() {
        this.title = new JPanel();
        this.title.setBackground(background1);
        this.title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel("UBC Campus Tour!");
        Font font = new Font("arial", Font.BOLD, 60);
        this.topTitle.setFont(font);
        placeTitleElements();
    }

    private void placeTitleElements() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;

        title.add(topTitle, gridBagConstraints);
        gridBagConstraints.gridy = 1;

        try {
            drawIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        title.add(imageLabel, gridBagConstraints);
    }

    // EFFECTS: draw image on the tour route TODO
    private void drawIcon() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(" "));
        Image newImage = myPicture.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        this.imageLabel = new JLabel(new ImageIcon(newImage));
    }

    //MODIFIES: this
    //EFFECTS: helper method for creating new buttons
    private void buttonCalls() {
        this.addTourStopButton.addActionListener(new AddStopListener());
        this.tourStopName.addActionListener(new AddStopListener());
        this.tourStopType.addActionListener(new AddStopListener());
        this.tourStopLocation.addActionListener(new AddStopListener());
        this.visitTourStopButton.addActionListener(new VisitStopListener());
        this.saveTourStopButton.addActionListener(new SaveStopListener());
        this.loadTourStopButton.addActionListener(new LoadStopListener());
        this.viewNextButton.addActionListener(new NextStopListener());
        this.viewVisitedButton.addActionListener(new VisitedStopListener());
    }

    // visit tour stop
    private class VisitStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tourStopName.getText();
            tourRoute.markAsVisited(tourRoute.getTourStopByName(name));

            printVisitedStops = printVisitedStops + "   " + name;
        }
    }

    //Save the tour route
    private class SaveStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(tourRoute);
                jsonWriter.close();
                System.out.println("Saved " + tourRoute.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException i) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // Load the tour Route
    private class LoadStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                tourRoute = jsonReader.read();
                System.out.println("Loaded " + tourRoute.getName() + " from " + JSON_STORE);
            } catch (IOException i) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            System.out.println(tourRoute.printNextStops());
        }
    }

    private class NextStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("   Next Stops:   "
                    + tourRoute.printNextStops());
            updateUI();
        }
    }

    private class VisitedStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("   Visited Stops:   "
                    + printVisitedStops);
            updateUI();
        }
    }


    private class AddStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tourStopName.getText();
            String choice = tourStopType.getText();
            String location = tourStopLocation.getText();

            if (tourRoute.tourLength() < 5) {
                if (choice.equals("L")) {
                    Library library = new Library(name, location);
                    tourRoute.addTourStop(library);
                } else if (choice.equals("G")) {
                    Garden garden = new Garden(name, location);
                    tourRoute.addTourStop(garden);
                } else if (choice.equals("FB")) {
                    FacultyBuilding facultyBuilding = new FacultyBuilding(name, location);
                    tourRoute.addTourStop(facultyBuilding);
                } else {
                    Museum museum = new Museum(name, location);
                    tourRoute.addTourStop(museum);
                }
                textArea.append("   New Tour Route with Added Tour Stop:   " + tourRoute.printNextStops());
                updateUI();

            } else {
                textArea.append("   Maximum Tour Route Length Exceeded   ");
                updateUI();
                System.out.println("Reached Max Tour Length");
            }
            System.out.println(tourRoute.printNextStops());
        }
    }


}
