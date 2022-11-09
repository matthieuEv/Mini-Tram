package UI;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import presenter.Main_Presenter;
import utils.Pos;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Interface_UI {
    private static volatile Interface_UI instance = null;
    private Stage stage;
    private double WIDTH = 1000;
    private double HEIGHT = 700;
    private Game_UI game_ui;
    private End_UI end_ui;
    private Menu_UI menu_ui;
    private Main_Presenter presenter;

    private Interface_UI(Stage stage) {
        this.stage = stage;
    }

    public static Interface_UI getInstance(Stage stage) {
        if (instance == null) {
            instance = new Interface_UI(stage);
        }
        return instance;
    }

    public void startGame(){
        Scene gameScene = new Scene(new Game_UI(this), WIDTH, HEIGHT);
        stage.setScene(gameScene);
        stage.show();
    }

    public void showInterface(){
        Scene menuScene = new Scene(new Game_UI(this), WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.setTitle("Mini Tram");
        //stage.setMaximized(true);
        Image image = new Image(new File("src/textures/ui/icon.png").toURI().toString());
        stage.getIcons().add(image);
        stage.show();
    }

    public void startMenu(){
        Scene menuScene = new Scene(new Menu_UI(this), WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }

    public double getWIDTH() {
        //WIDTH = stage.getWidth();
        return WIDTH;
    }

    public double getHEIGHT() {
        //HEIGHT = stage.getHeight();
        return HEIGHT;
    }
    
    public void appendPresenter(Main_Presenter presenter){
        this.presenter = presenter;
    }

    public Map<Integer, Pos> getListStations(){
        return presenter.getListStations();
    }

    public void syncLine(Color color){
        presenter.syncLine(color);
    }

}
