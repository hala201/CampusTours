package ui;

import javax.swing.*;
import java.awt.*;

public class CampusToursGuiFrame extends JFrame {
    TourRouteGui tourRouteGui;
    PlayAudio audio = new PlayAudio();

    //MODIFIES: this
    //EFFECTS: makes the frame for the app
    public CampusToursGuiFrame() {
        super("UBC Campus Tour!");
        this.tourRouteGui = new TourRouteGui();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(tourRouteGui);
        audio.play("./data/welcome.wav"); //TODO
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        revalidate();
    }

}
