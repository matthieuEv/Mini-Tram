package UI.items;

import UI.Game_UI;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;

public class Tram_UI {
    private Game_UI game_ui;
    private ArrayList<Shape> people;
    private Station_UI station;
    private Line_UI line;
    private double x;
    private double y;
    private int id;
    private static int idCounter = 0;
    private Pane gamePane;
    private Rectangle tramObject;

    public Tram_UI() {
        people = new ArrayList<>();
    }

    public void setLine(Line_UI line, Station_UI station, Game_UI game_ui) {
        this.line = line;
        this.station = station;
        this.game_ui = game_ui;
        this.gamePane = game_ui.getGamePane();
        x = station.getPos().x;
        y = station.getPos().y;
        id = idCounter;
        idCounter++;

        tramObject = new Rectangle(x, y, game_ui.getCellSize(), game_ui.getCellSize());
        tramObject.setFill(line.getColor());
        gamePane.getChildren().add(tramObject);
    }

    public void personEnter(Shape person){
        people.add(person);
    }

    public void personExit(Shape person){
        people.remove(person);
    }

    public void nextStep(Station_UI station) {
        x = station.getPos().x;
        y = station.getPos().y;
        tramObject.setX(x);
        tramObject.setY(y);
    }

    public void draw() {

    }

    public int getId() {
        return id;
    }
}
