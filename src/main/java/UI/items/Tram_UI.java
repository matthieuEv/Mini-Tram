package UI.items;

import UI.Game_UI;
import javafx.scene.paint.Color;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;

public class Tram_UI {
    private Game_UI game_ui;
    private ArrayList<Shape> people;
    private Line_UI line;
    private double x;
    private double y;
    private int id;
    private static int idCounter = 0;

    public Tram_UI() {
        people = new ArrayList<>();
    }

    public void setLine(Line_UI line, Station_UI station, Game_UI game_ui) {
        this.line = line;
        this.game_ui = game_ui;
        id = idCounter;
        idCounter++;
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
    }

    public void draw() {
        game_ui.getGc().setFill(Color.WHITE);
        game_ui.getGc().fillRect(x, y, game_ui.getCellSize(), game_ui.getCellSize());
    }

    public int getId() {
        return id;
    }
}
