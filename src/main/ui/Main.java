package ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
      //  CampusTourConsole campusTourConsole = new CampusTourConsole();

        CreateSplashScreen splashScreen = new CreateSplashScreen();
        Thread.sleep(1000);
        splashScreen.dispose();
        new CampusToursGuiFrame();

    }
}
