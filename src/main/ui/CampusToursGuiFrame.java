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
        ImageIcon image = new ImageIcon("./data/logo.jpg");
        this.setIconImage(image.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(tourRouteGui);
        audio.play("./data/welcome.wav");

        setPreferredSize(new Dimension(900, 700));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        revalidate();
    }

}
