package UI;

import UI.menu.Menu_UI;
import UI.music.Music;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Interface_UI {
    private static volatile Interface_UI instance = null;
    private Stage stage;
    private double WIDTH = 1000;
    private double HEIGHT = 700;
    private Game_UI game_ui;
    private End_UI end_ui;
    private Menu_UI menu_ui;
    private Music music;

    private Interface_UI(@org.jetbrains.annotations.NotNull Stage stage) {
        this.stage = stage;
        music = new Music("src/file/audio/music/Mini-Tram.mp3");
        music.play();

        Scene menuScene = new Scene(new Menu_UI(this), WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.setTitle("Mini Tram");
        //stage.setMaximized(true);
        Image image = new Image(new File("src/file/textures/ui/icon.png").toURI().toString());
        stage.getIcons().add(image);
        stage.show();


    }

    public Music getMusic() {
        return music;
    }

    public void startGame(){
        Scene gameScene = new Scene(new Game_UI(this), WIDTH, HEIGHT);
        stage.setScene(gameScene);
        stage.show();
    }

    public void startMenu(){
        Scene menuScene = new Scene(new Menu_UI(this), WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }

    public static Interface_UI getInstance(Stage stage) {
        if (instance == null) {
            instance = new Interface_UI(stage);
        }
        return instance;
    }

    public double getWIDTH() {
        //WIDTH = stage.getWidth();
        return WIDTH;
    }

    public double getHEIGHT() {
        //HEIGHT = stage.getHeight();
        return HEIGHT;
    }
}
