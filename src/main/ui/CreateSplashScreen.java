package ui;

import javax.swing.*;
import java.awt.*;

//Splash Screen creator
//REFERENCE: Java Oracle tutorial how to create splash screen
public class CreateSplashScreen extends JWindow {
    Image splashScreen;
    ImageIcon imageIcon;

    // TODO
    //EFFECTS: create a splash screen
    public CreateSplashScreen() {
        splashScreen = Toolkit.getDefaultToolkit().getImage("./data/tobs.jpg");
        imageIcon = new ImageIcon(splashScreen);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getSize().width);
        int y = (screenSize.height - getSize().height);

        setLocation(x,y);
        setVisible(true);
    }

    //EFFECTS: paints image on the JWindow
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(splashScreen, 0, 0, this);
    }
}
