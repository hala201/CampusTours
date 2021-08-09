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
    protected JComboBox tourStopType;
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
    private static final Color ubcGold = new Color(236, 224, 155);
    private static final Font font = new Font("arial", Font.BOLD, 15);
    private JTextArea textArea;

    //EFFECTS: creates and initializes task Manager Panel and Functionality
    TourRouteGui() {
        setPreferredSize(new Dimension(500, 600));
        setBorder(new EmptyBorder(13, 13, 13, 13));
        setBackground(background1);
        setLayout(new GridLayout(4, 1));
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

    //MODIFIES: this
    //EFFECTS: creates the title and UBC logo in the title panel
    private void buildTitle() {
        this.title = new JPanel();
        this.title.setBackground(background1);
        this.title.setLayout(new GridBagLayout());
        this.topTitle = new JLabel("UBC Campus Tour!");
        this.topTitle.setForeground(ubcGold);
        Font font = new Font("arial", Font.BOLD, 60);
        this.topTitle.setFont(font);
        placeTitleElements();
    }

    // EFFECTS: creates a layout for the buttons
    private void buildTourStopSpecification() {
        placeButtons();
        tourStopSpecification.setBackground(background1);
        placeSpecificationElements();
    }

    //EFFECTS: specifies the placement of fields and titles
    private void placeSpecificationElements() {
        GridBagConstraints g = new GridBagConstraints();
        placeSpecificationTitles(g);
        placeSpecificationFields(g);
    }

    //EFFECT: helper to specify the placing of fields
    private void placeSpecificationFields(GridBagConstraints g) {
        g.gridx = 0;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopName, g);

        g.gridx = 1;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopType, g);

        g.gridx = 2;
        g.gridy = 1;
        this.tourStopSpecification.add(this.tourStopLocation, g);


        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.tourStopSpecification.add(this.addTourStopButton, g);
    }

    //EFFECT: helper to specify the placing of titles
    private void placeSpecificationTitles(GridBagConstraints g) {
        g.insets = new Insets(5, 5, 5, 5);
        g.gridx = 0;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopTitle, g);

        g.gridx = 1;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopTypeTitle, g);

        g.gridx = 2;
        g.gridy = 0;
        this.tourStopSpecification.add(this.tourStopLocationTitle, g);
    }

    //EFFECT: makes the upcoming tour route lay out
    private void buildTourRouteViewer() {
        this.tourRouteViewer = new JPanel();
        tourRouteViewer.setBackground(textPanelColor);
        tourRouteViewer.setLayout(new GridLayout(2, 1, 0, 10));
        this.tourRouteTitle = new JLabel("My Tour Route");
        this.tourStopTitle = new JLabel("My Upcoming Tour Route:");

        textArea = new JTextArea();
        this.tourRouteTitle.setFont(font);
        textArea.setFont(font);
        textArea.append(printNextStops());

        JScrollPane scroll = new JScrollPane(textArea);
        textArea.setBackground(Color.WHITE);
        tourRouteTitle.setHorizontalAlignment(JLabel.CENTER);
        tourRouteViewer.add(tourRouteTitle);
        tourRouteViewer.add(scroll);
    }

    //EFFECT: specifies the placing and font of the following buttons:
    //          - View Upcoming
    //          - Visit
    //          - View Visited
    //          - Save
    //          - Load
    private void buildInteractionButtons() {
        initializeInteractionFields();
        this.visitTourStopButton.setFont(font);
        this.loadTourStopButton.setFont(font);
        this.saveTourStopButton.setFont(font);
        this.viewNextButton.setFont(font);
        this.viewVisitedButton.setFont(font);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        this.interactionButtons.add(viewNextButton, c);

        c.gridx = 1;
        c.gridy = 0;
        this.interactionButtons.add(visitTourStopButton, c);

        c.gridx = 2;
        c.gridy = 0;
        this.interactionButtons.add(viewVisitedButton, c);

        c.gridx = 3;
        c.gridy = 0;
        this.interactionButtons.add(saveTourStopButton, c);

        c.gridx = 4;
        c.gridy = 0;
        this.interactionButtons.add(loadTourStopButton, c);

    }

    //EFFECT: helper to build the interactive buttons
    private void initializeInteractionFields() {
        this.interactionButtons = new JPanel();
        interactionButtons.setBackground(background1);
        this.interactionButtons.setLayout(new GridBagLayout());
        this.visitTourStopButton = new JButton("Visit");
        this.loadTourStopButton = new JButton("Load");
        this.saveTourStopButton = new JButton("Save");
        this.viewVisitedButton = new JButton("View Visited");
        this.viewNextButton = new JButton("View Upcoming");
    }


    //EFFECT: specifies the placing of tour stop name, type and location fields
    private void placeButtons() {
        this.tourStopSpecification = new JPanel();
        this.tourStopSpecification.setLayout(new GridBagLayout());
        this.tourStopName = new JTextField(10);
        String[] tourStopTypes = {
                "Library",
                "Museum",
                "Faculty Building",
                "Garden"
        };
        this.tourStopType = new JComboBox(tourStopTypes);
        this.tourStopLocation = new JTextField(10);
        this.addTourStopButton = new JButton("Add Tour Stop");
        this.tourStopTitle = new JLabel("Name");
        this.tourStopTitle.setForeground(ubcGold);
        this.tourStopTypeTitle = new JLabel("Type");
        this.tourStopTypeTitle.setForeground(ubcGold);
        this.tourStopLocationTitle = new JLabel("Location");
        this.tourStopLocationTitle.setForeground(ubcGold);

        this.tourStopTitle.setFont(font);
        this.tourStopTypeTitle.setFont(font);
        this.tourStopLocationTitle.setFont(font);

        this.addTourStopButton.setFont(font);
    }

    //EFFECTS: prints the upcoming tour stops
    public String printNextStops() {
        return TourStopPrinter.printList(tourRoute.getToBeVisitedRoute());
    }


    //EFFECTS: places the title elements within the panel
    private void placeTitleElements() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;

        title.add(topTitle, gridBagConstraints);
        gridBagConstraints.gridy = 1;

        try {
            drawIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 1;
        title.add(imageLabel, gridBagConstraints);
    }

    // EFFECTS: draw image on the tour route
    private void drawIcon() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("./data/logo.jpg"));
        Image newImage = myPicture.getScaledInstance(myPicture.getWidth() / 2,
                myPicture.getHeight() / 2, Image.SCALE_SMOOTH);
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

    // Functionality to be further improved in the future
    // Such as pause and play audio
    //EFFECTS: marks the most recently added tour stop as visited
    //         and plays an audio of the tour\
    private class VisitStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tourStopName.getText();
            if (tourRoute.tourLength() > 0) {
                tourRoute.markAsVisited(tourRoute.getTourStopByName(name));
                textArea.append("Starting with your most recently added tour stop! Enjoy!");

                PlayAudio audio = new PlayAudio();
                audio.play("./data/visit.wav");

                printVisitedStops = printVisitedStops + "   " + name;
            } else {
                textArea.append("You don't have any sops yet");
            }
        }
    }

    //EFFECTS: saves the tour route
    private class SaveStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(tourRoute);
                jsonWriter.close();
                textArea.append("Saved " + tourRoute.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException i) {
                //System.out.println("Unable to write to file: " + JSON_STORE);
                textArea.append("Can't find file!");
            }
        }
    }

    //EFFECTS: loads the tour Route
    private class LoadStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                tourRoute = jsonReader.read();
                textArea.append("Loaded " + tourRoute.getName() + " from " + JSON_STORE + "\n");
            } catch (IOException i) {
                textArea.append("Can't find file!");
            }
            textArea.append(printNextStops());
        }
    }

    //EFFECTS: displays the upcoming tour stops
    private class NextStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("\n   Next Stops:   \n"
                    + printNextStops());
            updateUI();
        }
    }

    //EFFECTS: displays the visited tour stops
    private class VisitedStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("\n   Visited Stops:   \n"
                    + printVisitedStops);
            updateUI();
        }
    }

    //EFFECTS: adds the entered tour stop to the upcoming tour route
    private class AddStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tourStopName.getText();
            String choice = (String) tourStopType.getSelectedItem();
            String location = tourStopLocation.getText();

            if (tourRoute.tourLength() < 5) {
                makeSpecificTourStopType(name, choice, location);
                textArea.append("\n"
                        +
                        printNextStops() + "\n");
                updateUI();
            } else {
                textArea.append("   Maximum Tour Route Length Exceeded   ");
                updateUI();
                textArea.append("Reached Max Tour Length");
            }
        }

        //EFFECT: helper to addTourStop that constructs and adds a tour stop
        //        given the selected tour stop type
        private void makeSpecificTourStopType(String name, String choice, String location) {
            if (choice.equals("Library")) {
                Library library = new Library(name, location);
                tourRoute.addTourStop(library);
            } else if (choice.equals("Garden")) {
                Garden garden = new Garden(name, location);
                tourRoute.addTourStop(garden);
            } else if (choice.equals("Faculty Building")) {
                FacultyBuilding facultyBuilding = new FacultyBuilding(name, location);
                tourRoute.addTourStop(facultyBuilding);
            } else if (choice.equals("Museum")) {
                Museum museum = new Museum(name, location);
                tourRoute.addTourStop(museum);
            }
        }
    }


}
