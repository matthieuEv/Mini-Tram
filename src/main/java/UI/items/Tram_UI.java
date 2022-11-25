package UI.items;

import UI.Game_UI;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
    private Path tramObject;
    private Pane mainTram;
    private FlowPane peopleContainer;

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

        mainTram = new Pane();
        mainTram.setTranslateX(x);
        mainTram.setTranslateY(y);
        //mainTram.setStyle("-fx-background-color: " + line.getColor().toString().substring(2, 8) + ";");
        mainTram.setPrefSize(game_ui.getCellSize()*2, game_ui.getCellSize()+game_ui.getCellSize()/2);

        tramObject = new Path();
        MoveTo moveTo = new MoveTo(0, game_ui.getCellSize()/2);
        LineTo line1 = new LineTo(game_ui.getCellSize()/2, game_ui.getCellSize());
        LineTo line2 = new LineTo(game_ui.getCellSize()*2, game_ui.getCellSize());
        LineTo line3 = new LineTo(game_ui.getCellSize()*2, 0);
        LineTo line4 = new LineTo(game_ui.getCellSize()/2, 0);
        LineTo line5 = new LineTo(0, game_ui.getCellSize()/2);

        tramObject.setStroke(line.getColor());
        tramObject.setStrokeWidth(3);

        tramObject.getElements().add(moveTo);
        tramObject.getElements().addAll(line1, line2, line3, line4, line5);
        mainTram.getChildren().add(tramObject);

        peopleContainer = new FlowPane();
        peopleContainer.setMaxWidth(game_ui.getCellSize()*2);
        peopleContainer.setMaxHeight(game_ui.getCellSize()/2);
        mainTram.getChildren().add(peopleContainer);

        for(int i = 0; i < 7; i++){
            Rectangle rectangle = new Rectangle(0, 0, game_ui.getCellSize()/2-1, game_ui.getCellSize()/2-1);
            rectangle.setFill(Color.RED);
            peopleContainer.getChildren().add(rectangle);
        }

        //tramObject = new Rectangle(x, y, game_ui.getCellSize(), game_ui.getCellSize());
        //tramObject.setFill(line.getColor());
        gamePane.getChildren().add(mainTram);
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
        mainTram.setTranslateX(x);
        mainTram.setTranslateY(y);
    }

    public void draw() {

    }

    public int getId() {
        return id;
    }
}
