package UI.items;

import UI.Game_UI;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utils.Shape;

import java.util.ArrayList;

import static UI.items.shape_UI.getShape;

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
    private TranslateTransition animTranslate;
    private RotateTransition animRotate;
    private double angle;

    /**
     * Instanciate a new tram
     */
    public Tram_UI() {
        people = new ArrayList<>();
    }

    /**
     * Add the tram to a line and draw it with the animation
     * @param game_ui the game ui
     * @param station the station where the tram is at the start
     * @param line the line of the tram
     */
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
        mainTram.setPrefSize(game_ui.getCellSize()*2, game_ui.getCellSize()+game_ui.getCellSize()/2);

        tramObject = new Path();
        MoveTo moveTo = new MoveTo(game_ui.getCellSize()*2, game_ui.getCellSize()/2);
        LineTo line1 = new LineTo(game_ui.getCellSize()/0.7, game_ui.getCellSize());
        LineTo line2 = new LineTo(0, game_ui.getCellSize());
        LineTo line3 = new LineTo(0, 0);
        LineTo line4 = new LineTo(game_ui.getCellSize()/0.7, 0);
        LineTo line5 = new LineTo(game_ui.getCellSize()*2, game_ui.getCellSize()/2);

        tramObject.setStroke(line.getColor());
        tramObject.setStrokeWidth(3);

        tramObject.getElements().add(moveTo);
        tramObject.getElements().addAll(line1, line2, line3, line4, line5);
        tramObject.setFill(Color.WHITE);
        mainTram.getChildren().add(tramObject);

        peopleContainer = new FlowPane();
        peopleContainer.setMaxWidth(game_ui.getCellSize()*2);
        peopleContainer.setMaxHeight(game_ui.getCellSize()/2);
        mainTram.getChildren().add(peopleContainer);

        animRotate = new RotateTransition(Duration.seconds(0.05), mainTram);
        animRotate.setFromAngle(angle);
        animRotate.setInterpolator(Interpolator.LINEAR);
        animRotate.setOnFinished(event -> {
            animRotate.setFromAngle(angle);
            animTranslate.play();
        });

        animTranslate = new TranslateTransition(Duration.seconds(0.9), mainTram);
        animTranslate.setFromY(y);
        animTranslate.setFromX(x);
        animTranslate.setInterpolator(Interpolator.LINEAR);
        animTranslate.setOnFinished(event -> {
            animTranslate.setFromY(y);
            animTranslate.setFromX(x);
        });

        gamePane.getChildren().add(mainTram);
    }

    /**
     * Show the people in the tram
     */
    public void editPeople(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                people = line.getPeople(id);
                peopleContainer.getChildren().clear();
                for(int i = 0; i < people.size(); i++){
                    javafx.scene.shape.Shape shape = getShape(people.get(i), 0.47);
                    shape.setFill(Color.BLACK);
                    peopleContainer.getChildren().add(shape);
                }
            }
        });
    }

    /**
     * Difine the next station to go
     * @param station The station to move to
     */
    public void nextStep(Station_UI station, int time) {
        int newX = station.getPos().x;
        int newY = station.getPos().y;
        //System.out.println(angle);
        angle = Math.toDegrees(Math.atan2(newY - y, newX - x));

        if(angle < 0){
            angle += 360;
        }
        //System.out.println(angle);
        x = station.getPos().x;
        y = station.getPos().y;
        animRotate.setToAngle(angle);
        animTranslate.setToY(y);
        animTranslate.setToX(x);
        animTranslate.setDuration(Duration.seconds((time/1000f)-0.1));

        editPeople();
        animRotate.play();
    }

    /**
     * Give the tram id
     * @return id of the tram
     */
    public int getId() {
        return id;
    }
}
