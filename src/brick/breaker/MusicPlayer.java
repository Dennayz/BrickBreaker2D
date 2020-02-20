package brick.breaker;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicPlayer {

    public MusicPlayer(String file) {
        try {
            //jLayer does not support continuous play, so add boolean statement to keep playing
            while (true) {
                FileInputStream fileInputStream = new FileInputStream(file);
                Player player = new Player(fileInputStream);
                System.out.println("song is playing");
                player.play();
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("song file not found");
        }
        catch (JavaLayerException e) {
            e.printStackTrace();
            System.out.println("song can't be played");
        }
    }
}
