package UI.items;

import UI.Game_UI;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Station_UI {
    private Game_UI game_ui;
    private Pane gamePane;
    private Pos pos;
    private int id;
    private Shape stationShape;
    private Circle circle;
    private Map<Color, Boolean> endLines;
    private ArrayList<Shape> people;
    private FlowPane peopleContainer;


    public Station_UI(Game_UI game_ui, Pos pos, int id) {
        super();
        this.game_ui = game_ui;
        this.gamePane = game_ui.getGamePane();
        this.pos = pos;
        this.id = id;

        endLines = new HashMap<Color, Boolean>();
        people = new ArrayList<>();

        circle = new Circle(10);
        circle.setTranslateX(pos.x+game_ui.getCellSize()/2f);
        circle.setTranslateY(pos.y+game_ui.getCellSize()/2f);
        circle.setFill(Color.GREEN);

        peopleContainer = new FlowPane();
        peopleContainer.setTranslateX(pos.x+game_ui.getCellSize());
        peopleContainer.setTranslateY(pos.y-game_ui.getCellSize());
        peopleContainer.setMaxWidth((game_ui.getCellSize()/2f)*7);
        peopleContainer.setMaxHeight((game_ui.getCellSize()/2f)*2);

        for(int i = 0; i < 8; i++){
            VBox vBoxCircle = new VBox();
            vBoxCircle.setStyle("-fx-padding: 2;");
            Circle circle = new Circle(game_ui.getCellSize()/4f+1);
            circle.setFill(Color.BLUE);
            vBoxCircle.getChildren().add(circle);
            peopleContainer.getChildren().add(vBoxCircle);
        }


        this.gamePane.getChildren().add(peopleContainer);
        this.gamePane.getChildren().add(circle);
    }

    public void draw() {

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
