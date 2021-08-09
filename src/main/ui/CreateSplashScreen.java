package ui;

import javax.swing.*;
import java.awt.*;

//Splash Screen creator
//REFERENCE: Java Oracle tutorial how to create splash screen
public class CreateSplashScreen extends JWindow {
    Image splashScreen;
    ImageIcon imageIcon;

    //EFFECTS: create a splash screen at the starting of the program
    public CreateSplashScreen() {
        splashScreen = Toolkit.getDefaultToolkit().getImage("./data/splash.jpg");
        imageIcon = new ImageIcon(splashScreen);
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight()); //TODO

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getSize().width) / 2;
        int y = (screenSize.height - getSize().height) / 2;

        setLocation(x, y);
        setVisible(true);
    }

    //EFFECTS: paints image on the JWindow
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(splashScreen, 0, 0, this);
    }
}
