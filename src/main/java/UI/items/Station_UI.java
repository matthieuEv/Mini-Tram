package UI.items;

import UI.Game_UI;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Station_UI {
    private Game_UI game_ui;
    private Pane gamePane;
    private Pos pos;
    private int id;
    private javafx.scene.shape.Shape stationShape;
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

        createStationShape(Shape.random_shape());

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
        this.gamePane.getChildren().add(stationShape);
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
/*
    public void personEnter(Shape person){
        if(person != stationShape) {
            people.add(person);
        }
    }*/

    public void personExit(Shape person){
        people.remove(person);
    }

    private javafx.scene.shape.Shape createStationShape(Shape shapeName){
        Color color = Color.BLACK;
        Color backgroundColor = Color.web("#2F2F2F");
        switch (shapeName) {
            case ROUND -> {
                stationShape = new Circle(game_ui.getCellSize()/2f);
                stationShape.setTranslateX(pos.x + game_ui.getCellSize() / 2f);
                stationShape.setTranslateY(pos.y + game_ui.getCellSize() / 2f);
                stationShape.setStrokeWidth(5);
                stationShape.setStroke(color);
                stationShape.setFill(backgroundColor);
            }
            case SQUARE -> {
                stationShape = new Rectangle(game_ui.getCellSize(), game_ui.getCellSize());
                stationShape.setTranslateX(pos.x + game_ui.getCellSize() / 2f);
                stationShape.setTranslateY(pos.y + game_ui.getCellSize() / 2f);
                stationShape.setStrokeWidth(5);
                stationShape.setStroke(color);
                stationShape.setFill(backgroundColor);
            }
            case TRIANGLE -> {
                stationShape = new Polygon();
                ((Polygon) stationShape).getPoints().addAll(
                        (double) (pos.x + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f),
                        (double) (pos.x + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f),
                        (double) (pos.x + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f));
                stationShape.setStrokeWidth(5);
                stationShape.setStroke(color);
                stationShape.setFill(backgroundColor);
            }
            case HEXAGON -> {
                stationShape = new Polygon();
                ((Polygon) stationShape).getPoints().addAll(
                        (double) (pos.x + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 2f),
                        (double) (pos.x + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 4f),
                        (double) (pos.x + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 4f),
                        (double) (pos.x + game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 2f),
                        (double) (pos.x + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f + game_ui.getCellSize() / 4f),
                        (double) (pos.x + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 2f), (double) (pos.y + game_ui.getCellSize() / 2f - game_ui.getCellSize() / 4f));
                stationShape.setStrokeWidth(5);
                stationShape.setStroke(color);
                stationShape.setFill(backgroundColor);
            }
            default -> {
                stationShape = new Circle(game_ui.getCellSize() / 2f);
                stationShape.setTranslateX(pos.x + game_ui.getCellSize() / 2f);
                stationShape.setTranslateY(pos.y + game_ui.getCellSize() / 2f);
                stationShape.setFill(Color.RED);
            }
        }
        return stationShape;
    }
}

