package UI.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import UI.menu.UseJson;
import javafx.util.Duration;

import java.io.File;

public class Music {
    private MediaPlayer mediaPlayer;

    /**
     * Chooses the music to play
     * @param url
     */
    public void setMusic(String url){
        File file = new File(url);
        Media media = new Media(file.toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    /**
     * Plays the music
     */
    public void play(){
        double volume = new UseJson().readJson("music");
        this.mediaPlayer.setVolume(volume/100);
        this.mediaPlayer.play();
        this.mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
    }

    /**
     * Changes the volume of the music
     * @param volume
     */
    public void changeVolume(double volume){
        this.mediaPlayer.setVolume(volume/100);
    }

    /**
     * Stops the music
     */
    public void stop(){
        if(this.mediaPlayer != null){
            this.mediaPlayer.stop();
        }
    }

    /**
     * Chooses the music file to play
     * @param url
     */
    private void playfromfile(String url){
        File file = new File(url);
        double volume = new UseJson().readJson("sfx");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume/100);
        mediaPlayer.play();
    }

    /**
     * Plays the sound Effects
     * @param sfx
     */
    public void playSound(String sfx){
        if(sfx == "test"){
            playfromfile("src/file/audio/sfx/test.wav");
        }
        if(sfx == "click"){
            playfromfile("src/file/audio/sfx/click.wav");
        }
        if(sfx == "select"){
            playfromfile("src/file/audio/sfx/select.wav");
        }
        if(sfx == "linkStation"){
            playfromfile("src/file/audio/sfx/linkStation.wav");
        }
        if(sfx == "spawnPeople"){
            playfromfile("src/file/audio/sfx/spawnPeople.wav");
        }
        if(sfx == "takePeople"){
            playfromfile("src/file/audio/sfx/takePeople.wav");
        }
    }
}
