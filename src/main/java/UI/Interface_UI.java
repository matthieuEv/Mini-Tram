package UI;

import UI.intro.Intro;
import UI.menu.Menu_UI;
import UI.music.Music;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import UI.intro.Intro;
import UI.menu.Menu_UI;
import UI.music.Music;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import presenter.Main_Presenter;
import utils.Pos;
import utils.Shape;

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
    private Intro intro;
    private Music music;

    private Interface_UI(Stage stage) {
        this.stage = stage;
        music = new Music();
        intro = new Intro(this);
        menu_ui = new Menu_UI(this);
        game_ui = new Game_UI(this);
        end_ui = new End_UI(this);
    }

    public static Interface_UI getInstance(Stage stage) {
        if (instance == null) {
            instance = new Interface_UI(stage);
        }
        return instance;
    }
    public void startIntro(){
        music.setMusic("src/file/audio/music/intro.mp3");
        music.play();
        stage.setScene(new Scene(intro, WIDTH, HEIGHT));
    }

    public void startMenu(){
        music.stop();
        music.setMusic("src/file/audio/music/Mini-Tram.mp3");
        music.play();
        stage.setScene(new Scene(menu_ui, WIDTH, HEIGHT));
    }
    public void startGame(){
        Scene gameScene = new Scene(game_ui, WIDTH, HEIGHT);
        stage.setScene(gameScene);
        stage.show();
    }

    public void showInterface(){
        startMenu();
        game_ui.setInterface_ui(this);
        stage.setTitle("Mini Tram");
        Image image = new Image(new File("src/file/textures/ui/icon.png").toURI().toString());
        stage.getIcons().add(image);
        stage.show();
    }

    public Music getMusic() {
        return music;
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

    public void modelAddLine(int idLine, int idStation1, int idStation2){
        presenter.modelAddLine(idLine, idStation1, idStation2);
    }

    public void SEND_tram_next_step(int idTram, int idStation, int idLine){
        game_ui.SEND_tram_next_step(idTram, idStation, idLine);
    }

    public void SEND_add_tram(int idStation, int idLine) {
        game_ui.SEND_add_tram(idStation, idLine);
    }

    public void modelAddStation(int line_id, int station1_id, int station2_id){
        presenter.modelAddStation(line_id, station1_id, station2_id);
    }

    public ArrayList<Shape> getPeople(int id) {
        return presenter.getPeople(id);
    }

    public void GET_add_people_station(int idStation, ArrayList<Shape> shape){
        game_ui.GET_add_people_station(idStation, shape);
    }

    public Shape getShapeStation(int id) {
        return presenter.getShapeStation(id);
    }
}
