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

import static UI.items.shape_UI.getShape;

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

    /**
     * Instanciate a new station and show it on the gamePane
     * @param game_ui The game UI to get the interface parametre
     * @param pos The position of the station
     * @param id The id of the station
     * @param stationShape The shape of the station
     */
    public Station_UI(Game_UI game_ui, Pos pos, int id, Shape stationShape) {
        super();
        this.game_ui = game_ui;
        this.gamePane = game_ui.getGamePane();
        this.pos = pos;
        this.id = id;
        this.stationShape = stationShape;

        endLines = new HashMap<Color, Boolean>();
        people = new ArrayList<>();

        javafx.scene.shape.Shape shape = getShape(stationShape);
        shape.setTranslateX(pos.x);
        shape.setTranslateY(pos.y);
        shape.setStyle("-fx-background-color: #424242;");
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);

        peopleContainer = new FlowPane();
        peopleContainer.setTranslateX(pos.x+game_ui.getCellSize());
        peopleContainer.setTranslateY(pos.y-game_ui.getCellSize());
        peopleContainer.setMaxWidth((game_ui.getCellSize()/2)*4);
        peopleContainer.setMaxHeight((game_ui.getCellSize()/2)*2);


        this.gamePane.getChildren().add(peopleContainer);
        this.gamePane.getChildren().add(shape);
    }

    /**
     * Give the position of the station
     * @return the pos of the station
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * Give the id of the station
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the line has an end line
     * @param color The color define the line
     * @return the stationShape
     */
    public boolean isEndLine(Color color) {
        if(endLines.containsKey(color)) {
            return endLines.get(color);
        }
        return false;
    }

    /**
     * Define if the station is an end of a line
     * @param color The color define the line
     * @param endLine The boolean to define if the station is an end of a line
     */
    public void setEndLine(Color color, boolean endLine) {
        endLines.put(color, endLine);
    }

    /**
     * Use this method to update the people in the station
     * @param people The list of people in the station
     */
    public void setPeople(ArrayList<Shape> people) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                peopleContainer.getChildren().clear();
                for(int i = 0; i < people.size(); i++){
                    javafx.scene.shape.Shape peopleShape = getShape(people.get(i), 0.5);
                    peopleShape.setFill(Color.BLACK);
                    peopleContainer.getChildren().add(peopleShape);
                }
            }
        });
    }
}
