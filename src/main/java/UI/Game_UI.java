package UI;

import UI.items.Line_UI;
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
    private Map<Integer, Station_UI> stations;
    private final int cellSize = 20;
    private Station_UI lastSelectedStation;
    private Map<Color, Line_UI> lines;
    private Color selectedLine;

    public Game_UI(Interface_UI interface_ui) {
        super();

        this.interface_ui = interface_ui;

        stations = new HashMap<Integer, Station_UI>();
        lines = new HashMap<Color, Line_UI>();
        selectedLine = null;

        canvas = new Canvas(interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        gc = canvas.getGraphicsContext2D();
        this.setStyle("-fx-background-color: #424040;");


        this.getChildren().add(canvas);

        canvas.setOnMouseClicked(event -> {
            if(selectedLine != null) {
                Pos mousePos = new Pos(arroundValue((int) event.getX()), arroundValue((int) event.getY()));
                if (stations.containsKey(setSingleId(mousePos))) {
                    Station_UI selectedStation = stations.get(setSingleId(mousePos));

                    if (lastSelectedStation != null) {
                        if (lines.containsKey(selectedLine)) {
                            if (lastSelectedStation.isEndLine(selectedLine)) {

                                if(!selectedStation.isEndLine(selectedLine)){
                                    lines.get(selectedLine).addSegment(lastSelectedStation, selectedStation);
                                    lastSelectedStation.setEndLine(selectedLine, false);
                                    System.out.println("expend line");
                                }

                                /*
                                // If we boucle the line
                                if(selectedStation.isEndLine(selectedLine)) {
                                    selectedStation.setEndLine(selectedLine, false);
                                }   else {
                                    selectedStation.setEndLine(selectedLine, true);
                                }
                                 */

                                //selectedStation.setEndLine(selectedLine, true);
                            } else {
                                System.out.println("Station is not an end of the line");
                            }
                        } else {
                            lines.put(selectedLine, new Line_UI(this, selectedLine));
                            lines.get(selectedLine).addSegment(lastSelectedStation, selectedStation);
                            lastSelectedStation.setEndLine(selectedLine, true);
                            selectedStation.setEndLine(selectedLine, true);
                            System.out.println("new line");
                        }
                        lastSelectedStation.setSelected(false);
                        lastSelectedStation = null;
                        selectedLine = null;
                    } else {
                        lastSelectedStation = selectedStation;
                        selectedStation.setSelected(true);
                        System.out.println("first station");
                    }
                    drawGame();
                }
            }
        });

        drawDebugMenu();

        addStation();
    }

    private void addStation() {
        Map<Integer, Pos> listStation = interface_ui.getListStations();

        for(Map.Entry<Integer, Pos> entry : listStation.entrySet()) {
            int id = setSingleId(entry.getValue());
            stations.put(id, new Station_UI(this, entry.getValue(), entry.getKey()));
            stations.get(id).draw();
        }
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

    public void drawGame(){
        gc.clearRect(0, 0, interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        for (Line_UI line : lines.values()) {
            line.draw();
        }
        for (Station_UI station : stations.values()) {
            station.draw();
        }
    }




    private void drawDebugMenu(){
        VBox btnStation = new VBox();

        Button btnRedLine = new Button("Red Line");
        btnRedLine.setOnAction(e -> {
            selectedLine = Color.RED;
        });
        Button btnBlueLine = new Button("Blue Line");
        btnBlueLine.setOnAction(e -> {
            selectedLine = Color.BLUE;
        });
        Button btnGreenLine = new Button("Green Line");
        btnGreenLine.setOnAction(e -> {
            selectedLine = Color.GREEN;
        });
        Button btnVioletLine = new Button("Violet Line");
        btnVioletLine.setOnAction(e -> {
            selectedLine = Color.VIOLET;
        });

        btnStation.getChildren().addAll(btnRedLine, btnBlueLine, btnGreenLine, btnVioletLine);

        this.getChildren().addAll(btnStation);
        setRightAnchor(btnStation, 20d);
        setTopAnchor(btnStation, 50d);
    }
}
