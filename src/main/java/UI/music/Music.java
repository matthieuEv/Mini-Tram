package UI.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import UI.menu.useJson;

import java.io.File;

public class Music {
    private final MediaPlayer mediaPlayer;
    public Music(String url){
        File file = new File(url);
        Media media = new Media(file.toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void play(){
        double volume = new useJson().readJson("music");
        this.mediaPlayer.setVolume(volume/100);
        this.mediaPlayer.play();
    }

    public void changeVolume(double volume){
        this.mediaPlayer.setVolume(volume/100);
    }

    public void stop(){
        this.mediaPlayer.stop();
    }
}
