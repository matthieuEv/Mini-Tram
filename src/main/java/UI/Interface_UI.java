package UI;

import UI.intro.Intro;
import UI.menu.Menu_UI;
import UI.music.Music;
import javafx.application.Platform;
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
import model.compute.Layout;
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

    /**
     * Constructeur de l'interface
     * @param stage Stage de l'application
     */
    private Interface_UI(Stage stage) {
        this.stage = stage;
        music = new Music();
        intro = new Intro(this);
        menu_ui = new Menu_UI(this);
        game_ui = new Game_UI(this);
        end_ui = new End_UI(this);
    }

    /**
     * Instanciate the UI has a singleton
     * @param stage the stage of the application
     * @return the instance of the UI
     */
    public static Interface_UI getInstance(Stage stage) {
        if (instance == null) {
            instance = new Interface_UI(stage);
        }
        return instance;
    }

    /**
     * Show the intro
     */
    public void startIntro(){
        music.setMusic("src/file/audio/music/intro.mp3");
        music.play();
        stage.setScene(new Scene(intro, WIDTH, HEIGHT));
    }

    /**
     * Show the menu
     */
    public void startMenu(){
        music.stop();
        music.setMusic("src/file/audio/music/Mini-Tram.mp3");
        music.play();
        stage.setScene(new Scene(menu_ui, WIDTH, HEIGHT));
    }

    /**
     * Start the game screen and start the game
     */
    public void startGame(){
        presenter.StartGame();
        Scene gameScene = new Scene(game_ui, WIDTH, HEIGHT);
        game_ui.setInterface_ui(this);
        stage.setScene(gameScene);
        stage.show();
    }

    /**
     * Show the end screen
     */
    public void startEnd(){
        music.stop();
        music.setMusic("src/file/audio/music/Mini-Tram.mp3");
        music.play();
        stage.setScene(new Scene(end_ui, WIDTH, HEIGHT));
    }

    /**
     * Show the main interface
     */
    public void showInterface(){
        startIntro(); // uncomment to show intro
        stage.setTitle("Mini Tram");
        Image image = new Image(new File("src/file/textures/ui/icon.png").toURI().toString());
        stage.getIcons().add(image);
        stage.show();

        stage.setOnCloseRequest(event -> {
            presenter.closeGame();
        });
    }

    /**
     * Get the music object
     * @return the music object
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Get the width of the window
     * @return the width of the window
     */
    public double getWIDTH() {
        //WIDTH = stage.getWidth();
        return WIDTH;
    }

    /**
     * Get the height of the window
     * @return the height of the window
     */
    public double getHEIGHT() {
        //HEIGHT = stage.getHeight();
        return HEIGHT;
    }

    /**
     * Send the map to the game, for generate the background
     * @return the map
     */
    public Layout getMap(){
        return presenter.getMap();
    }

    /**
     * Define the presenter
     * @param presenter the presenter
     */
    public void appendPresenter(Main_Presenter presenter){
        this.presenter = presenter;
    }

    /**
     * When generate the first station, send the list of station to the game
     * @return the list of station
     */
    public Map<Integer, Pos> getListStations(){
        return presenter.getListStations();
    }

    /**
     * Send to presenter to syncronize the id of line in model and the color of line in UI
     * @param color The color of the line
     */
    public void syncLine(Color color){
        presenter.syncLine(color);
    }

    /**
     * Send to presenter to add a new line
     * @param idLine id of the line
     * @param idStation1 id of the first station
     * @param idStation2 id of the second station
     */
    public void modelAddLine(int idLine, int idStation1, int idStation2){
        presenter.modelAddLine(idLine, idStation1, idStation2);
    }

    /**
     * Prensent send to the tram the next step to do
     * @param idTram the id of the tram
     * @param idStation the id of the station
     * @param idLine the id of the line
     * @param time the time to wait
     */
    public void SEND_tram_next_step(int idTram, int idStation, int idLine , int time){
        game_ui.SEND_tram_next_step(idTram, idStation, idLine, time);
    }

    /**
     * Prensenter call this function to add a new tram to the game
     * @param idStation the id of the station where the tram is
     * @param idLine the id of the line where the tram is
     */
    public void SEND_add_tram(int idStation, int idLine) {
        game_ui.SEND_add_tram(idStation, idLine);
    }

    /**
     * Request to presenter to add a new station
     * @param line_id the line id
     * @param station1_id the first station id
     * @param station2_id the second station id
     */
    public void modelAddStation(int line_id, int station1_id, int station2_id){
        presenter.modelAddStation(line_id, station1_id, station2_id);
    }

    /**
     * Tram get the list of shape of people in it
     * @param id id of the tram
     * @return list of shape of people in the tram
     */
    public ArrayList<Shape> getPeople(int id) {
        return presenter.getPeople(id);
    }

    /**
     * Send list of people to the write station
     * @param idStation id of the station
     * @param shape Shape list of people
     */
    public void GET_add_people_station(int idStation, ArrayList<Shape> shape){
        game_ui.GET_add_people_station(idStation, shape);
    }

    /**
     * Add a new station to the map ui
     * @param idStation id of the station
     * @param pos position of the station
     */
    public void DEMAND_add_station(int idStation, Pos pos){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                game_ui.addStation(idStation, pos);

            }
        });

    }

    /**
     * Transfert shape to game_ui
     * @param id id of the station
     * @return the shape of the station
     */
    public Shape getShapeStation(int id) {
        return presenter.getShapeStation(id);
    }
}
