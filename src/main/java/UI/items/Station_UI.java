package UI.items;

import UI.Game_UI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Station_UI {
    private Game_UI game_ui;
    private Pos pos;
    private int id;
    private Shape stationShape;
    private boolean selected;
    private Map<Color, Boolean> endLines;
    private ArrayList<Shape> people;


    public Station_UI(Game_UI game_ui, Pos pos, int id) {
        super();
        this.game_ui = game_ui;
        this.pos = pos;
        this.id = id;

        endLines = new HashMap<Color, Boolean>();
        people = new ArrayList<>();

        //System.out.println(pos.x + " " + pos.y);
    }

    public void draw() {
        if (selected) {
            game_ui.getGc().setFill(Color.YELLOW);
        } else {
            game_ui.getGc().setFill(Color.RED);
        }
        game_ui.getGc().fillOval(pos.x, pos.y, game_ui.getCellSize(), game_ui.getCellSize());
    }

    public void clicked() {
        draw();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Pos getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public boolean isEndLine(Color color) {
        if(endLines.containsKey(color)) {
            return endLines.get(color);
        }
        return false;
    }

    public void setEndLine(Color color, boolean endLine) {
        endLines.put(color, endLine);
    }

    public void personEnter(Shape person){
        if(person != stationShape) {
            people.add(person);
        }
    }

    public void personExit(Shape person){
        people.remove(person);
    }
}
