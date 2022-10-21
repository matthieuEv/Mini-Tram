package UI;

import UI.items.Station_UI;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import utils.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Long.MAX_VALUE;

public class Game_UI extends AnchorPane {
    private Interface_UI interface_ui;
    private Canvas canvas;
    private GraphicsContext gc;
    private HashMap<Integer, Station_UI> stations;
    private final int cellSize = 20;
    private Station_UI lastSelectedStation;
    //private ArrayList<Station_UI> stations;
    public Game_UI(Interface_UI interface_ui) {
        super();

        this.interface_ui = interface_ui;

        stations = new HashMap<Integer, Station_UI>();

        canvas = new Canvas(interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        gc = canvas.getGraphicsContext2D();
        this.setStyle("-fx-background-color: #424040;");


        this.getChildren().add(canvas);

        canvas.setOnMouseClicked(event -> {
            Pos mousePos = new Pos(arroundValue((int)event.getX()), arroundValue((int)event.getY()));
            if(stations.containsKey(setSingleId(mousePos))) {
                Station_UI thisStation = stations.get(setSingleId(mousePos));
                if(lastSelectedStation == null) {
                    thisStation.setSelected(true);
                    lastSelectedStation = thisStation;
                }else{
                    gc.setStroke(Color.BLUE);
                    gc.strokeLine(lastSelectedStation.getPos().x+(cellSize/2), lastSelectedStation.getPos().y+(cellSize/2), thisStation.getPos().x+(cellSize/2), thisStation.getPos().y+(cellSize/2));
                    lastSelectedStation.setSelected(false);
                    lastSelectedStation.draw();
                    lastSelectedStation = null;
                }
                stations.get(setSingleId(mousePos)).clicked();
            }
        });

        Button addStationBtn = new Button("Add Station");
        addStationBtn.setOnAction(e -> {
            addStation();
        });

        VBox btnStation = new VBox();



        this.getChildren().addAll(addStationBtn, btnStation);
        this.setRightAnchor(addStationBtn, 20d);
        this.setTopAnchor(addStationBtn, 20d);
        this.setRightAnchor(btnStation, 20d);
        this.setTopAnchor(btnStation, 40d);


        for (int i = 0; i < 5; i++) {
            addStation();
        }
    }

    private void addStation() {
        Pos posStation = new Pos(arroundValue(new Random().nextInt((int) interface_ui.getWIDTH()-cellSize)), arroundValue(new Random().nextInt((int) interface_ui.getHEIGHT()-cellSize)));
        stations.put(setSingleId(posStation), new Station_UI(this, posStation));
        stations.get(setSingleId(posStation)).draw();
    }

    public double getWIDTH() {
        return interface_ui.getWIDTH();
    }

    public double getHEIGHT() {
        return interface_ui.getHEIGHT();
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public int arroundValue(int value) {
        return ((value/cellSize)*cellSize);
    }

    public int setSingleId(Pos pos){
        int cmx = (int)interface_ui.getWIDTH()/cellSize;
        int cmy = (int)interface_ui.getHEIGHT()/cellSize;
        return (pos.x/cellSize) + (pos.y/cellSize)*cmx;
    }

    public int getCellSize() {
        return cellSize;
    }

    public Pos getStationPos(int id){
        return stations.get(id).getPos();
    }
}
