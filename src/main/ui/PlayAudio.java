package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayAudio {

    //EFFECTS: plays the starting sound of the application
    public static void play(String file) {
        File f = new File(file);

        if (f.canRead()) {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException e) {
                // all good
            } catch (UnsupportedAudioFileException e) {
                // all good
            } catch (IOException e) {
                // all good
            }
        }
    }
}
