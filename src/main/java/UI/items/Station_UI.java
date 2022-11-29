package UI.items;

import UI.Game_UI;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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
    private Map<Color, Boolean> endLines;
    private ArrayList<Shape> people;
    private Circle circle;
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
        circle.setTranslateX(pos.x+game_ui.getCellSize()/2);
        circle.setTranslateY(pos.y+game_ui.getCellSize()/2);
        circle.setFill(Color.RED);
        if(stationShape == Shape.ROUND){
            circle.setFill(Color.RED);
        } else if(stationShape == Shape.SQUARE){
            circle.setFill(Color.BLUE);
        } else if(stationShape == Shape.DIAMOND){
            circle.setFill(Color.GREEN);
        } else if (stationShape == Shape.TRIANGLE) {
            circle.setFill(Color.YELLOW);
        }

        peopleContainer = new FlowPane();
        peopleContainer.setTranslateX(pos.x+game_ui.getCellSize());
        peopleContainer.setTranslateY(pos.y-game_ui.getCellSize());
        peopleContainer.setMaxWidth((game_ui.getCellSize()/2)*4);
        peopleContainer.setMaxHeight((game_ui.getCellSize()/2)*2);
        //peopleContainer.setStyle("-fx-background-color: #FFFFFF;");

        //for(int i = 0; i < 7; i++){
        //    Rectangle rectangle = new Rectangle(0, 0, game_ui.getCellSize()/2-1, game_ui.getCellSize()/2-1);
        //    rectangle.setFill(Color.BLUE);
        //    peopleContainer.getChildren().add(rectangle);
        //}


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

    public void setPeople(ArrayList<Shape> people) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                peopleContainer.getChildren().clear();
                for(int i = 0; i < people.size(); i++){
                    Rectangle rectangle = new Rectangle(0, 0, game_ui.getCellSize()/2-1, game_ui.getCellSize()/2-1);
                    rectangle.setFill(Color.RED);
                    if(people.get(i) == Shape.ROUND){
                        rectangle.setFill(Color.RED);
                    } else if(people.get(i) == Shape.SQUARE){
                        rectangle.setFill(Color.BLUE);
                    } else if(people.get(i) == Shape.DIAMOND){
                        rectangle.setFill(Color.GREEN);
                    } else if (people.get(i) == Shape.TRIANGLE) {
                        rectangle.setFill(Color.YELLOW);
                    }
                    peopleContainer.getChildren().add(rectangle);
                }
            }
        });
    }
}
