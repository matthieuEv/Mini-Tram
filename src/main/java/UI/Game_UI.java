package UI;

import UI.items.Line_UI;
import UI.items.Station_UI;
import UI.items.Tram_UI;
import UI.items.shape_UI;
import UI.music.Music;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.compute.Layout;
import utils.Pos;
import utils.Shape;

import java.util.*;

public class Game_UI extends StackPane {
    private Interface_UI interface_ui;
    private Pane gamePane;
    private VBox btnStation;
    private Map<Integer, Station_UI> stations;
    private final int cellSize = 20;
    private Station_UI lastSelectedStation;
    private Map<Color, Line_UI> lines;
    private Map<Integer, Color> listIdLines;
    private Color selectedLine;
    private Map<Integer, Tram_UI> trams;
    private Line tempLine;
    private Station_UI selectedStation;
    private Map<Color, Circle> listBtnLines;
    private Pane clockLine;
    private RotateTransition clockAnim;
    private int nbDays;
    private Text textNbDays;


    private Music music = new Music();
    //private ArrayList<Station_UI> stations;
    public Game_UI(Interface_UI interface_ui) {
        super();

        //this.interface_ui = interface_ui;
        shape_UI shape_ui = shape_UI.getInstance(this);
        stations = new HashMap<Integer, Station_UI>();
        lines = new HashMap<Color, Line_UI>();
        selectedLine = null;
        listIdLines = new HashMap<Integer, Color>();
        trams = new HashMap<Integer, Tram_UI>();
        listBtnLines = new HashMap<Color, Circle>();

        //this.setStyle("-fx-background-color: #424040;");


        gamePane = new Pane();
        gamePane.setPrefSize(interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        //gamePane.setStyle("-fx-background-color: #424242;");

        btnStation = new VBox();
        btnStation.setPadding(new Insets(0, 20, 0, 0));
        btnStation.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        btnStation.setSpacing(10);

        Pane clock = new Pane();
        clock.setPrefSize(75, 75);
        clock.setTranslateX(interface_ui.getWIDTH()-100);
        clock.setTranslateY(0);
        ImageView clockImg = new ImageView(new Image("file:src/file/textures/ui/clock.png"));
        clockImg.setFitWidth(75);
        clockImg.setFitHeight(75);
        clockLine = new Pane();
        clockLine.setPrefSize(75, 75);
        Line clockLineline = new Line(37, 37, 37, 15);
        clockLineline.setStrokeWidth(2);
        clockLineline.setStroke(Color.BLACK);
        clockLine.getChildren().add(clockLineline);
        textNbDays = new Text("Day 0");
        textNbDays.setFill(Color.WHITE);
        textNbDays.setTranslateY(90);
        textNbDays.setTranslateX(10);
        textNbDays.setFont(javafx.scene.text.Font.font("Arial", 20));

        nbDays = 0;
        clockAnim = new RotateTransition(Duration.seconds(30), clockLine);
        clockAnim.setFromAngle(0);
        clockAnim.setToAngle(360);
        clockAnim.setInterpolator(Interpolator.LINEAR);
        clockAnim.setOnFinished(event -> {
            clockAnim.play();
            nbDays++;
            textNbDays.setText("Day " + nbDays);
        });
        clockAnim.play();

        clock.getChildren().addAll(clockImg, clockLine, textNbDays);


        Canvas canvas = new Canvas(interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        Canvas background = renderCanvas(canvas);

        this.getChildren().addAll(background, clock, gamePane, btnStation);

        tempLine = new Line();
        tempLine.setStrokeWidth(5);
        gamePane.getChildren().add(tempLine);


    }

    private void addStation() {
        Map<Integer, Pos> listStation = interface_ui.getListStations();

        for(Map.Entry<Integer, Pos> entry : listStation.entrySet()) {
            int id = setSingleId(entry.getValue());
            Shape shape = interface_ui.getShapeStation(entry.getKey());
            stations.put(id, new Station_UI(this, entry.getValue(), entry.getKey(), shape));
            stations.get(id).draw();
        }
    }

    public double getWIDTH() {
        return interface_ui.getWIDTH();
    }

    public double getHEIGHT() {
        return interface_ui.getHEIGHT();
    }

    public void setInterface_ui(Interface_UI interface_ui) {
        this.interface_ui = interface_ui;
        this.setOnMousePressed(event -> {
            music.playSound("click");
            if(selectedLine != null) {
                Pos mousePos = new Pos(arroundValue((int) event.getX()), arroundValue((int) event.getY()));
                if (stations.containsKey(setSingleId(mousePos))) {
                    lastSelectedStation = stations.get(setSingleId(mousePos));
                    tempLine.setStroke(selectedLine);
                    tempLine.setStartX(lastSelectedStation.getPos().x + cellSize / 2);
                    tempLine.setStartY(lastSelectedStation.getPos().y + cellSize / 2);
                    tempLine.setEndX(mousePos.x);
                    tempLine.setEndY(mousePos.y);
                    //lastSelectedStation.setSelected(true);
                    selectedStation = stations.get(setSingleId(mousePos));
                    System.out.println("first station");
                }
            }
        });
        this.setOnMouseDragged(event -> {
            if(selectedLine != null) {
                if(lastSelectedStation != null) {
                    Pos mousePos = new Pos(arroundValue((int) event.getX()), arroundValue((int) event.getY()));


                    if (stations.containsKey(setSingleId(mousePos))) {
                        selectedStation = stations.get(setSingleId(mousePos));
                        if (lines.containsKey(selectedLine)) {
                            if (lastSelectedStation.isEndLine(selectedLine)) {
                                if (!selectedStation.isEndLine(selectedLine)) {
                                    if(!lines.get(selectedLine).containsStation(selectedStation)) {
                                        lines.get(selectedLine).addSegment(lastSelectedStation, selectedStation);
                                        interface_ui.modelAddStation(lines.get(selectedLine).getId(), lastSelectedStation.getId(), selectedStation.getId());
                                        lastSelectedStation.setEndLine(selectedLine, false);
                                        selectedStation.setEndLine(selectedLine, true);
                                        System.out.println("expend line");


                                        lastSelectedStation = selectedStation;
                                        tempLine.setStartX(lastSelectedStation.getPos().x + cellSize / 2);
                                        tempLine.setStartY(lastSelectedStation.getPos().y + cellSize / 2);
                                    }else{
                                        System.out.println("station already in line");
                                    }
                                }
                            } else {
                                System.out.println("Station is not an end of the line");
                            }
                        } else {
                            if (lastSelectedStation != selectedStation) {
                                lines.put(selectedLine, new Line_UI(this, selectedLine));
                                listIdLines.put(lines.get(selectedLine).getId(), selectedLine);
                                interface_ui.syncLine(selectedLine);
                                interface_ui.modelAddLine(lines.get(selectedLine).getId(), lastSelectedStation.getId(), selectedStation.getId());
                                lines.get(selectedLine).addSegment(lastSelectedStation, selectedStation);
                                lastSelectedStation.setEndLine(selectedLine, true);
                                selectedStation.setEndLine(selectedLine, true);
                                System.out.println("new line");


                                lastSelectedStation = selectedStation;
                                tempLine.setStartX(lastSelectedStation.getPos().x + cellSize / 2);
                                tempLine.setStartY(lastSelectedStation.getPos().y + cellSize / 2);
                            } else {
                                System.out.println("You can't create a line with only one station");
                            }
                        }
                    }



                    tempLine.setEndX(event.getX());
                    tempLine.setEndY(event.getY());
                }
            }
        });
        this.setOnMouseReleased(event -> {
            if(listBtnLines.containsKey(selectedLine)) {
                listBtnLines.get(selectedLine).setTranslateX(0);
            }
            selectedLine = null;
            selectedStation = null;
            lastSelectedStation = null;

            tempLine.setStartX(0);
            tempLine.setStartY(0);
            tempLine.setEndX(0);
            tempLine.setEndY(0);
        });

        drawDebugMenu();

        addStation();
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
        for (Station_UI station : stations.values()) {
            station.draw();
        }
        for (Line_UI line : lines.values()) {
            line.draw();
        }
    }

    private void drawDebugMenu(){
        addBtnStation(Color.RED);
        addBtnStation(Color.BLUE);
        addBtnStation(Color.GREEN);
        addBtnStation(Color.VIOLET);
    }

    public void addBtnStation(Color color){
        Circle btn = new Circle(10, color);
        listBtnLines.put(color, btn);
        btn.setOnMouseClicked(e -> {
            listBtnLines.get(color).setTranslateX(-20);
            selectedLine = color;
        });
        btnStation.getChildren().add(btn);
    }

    public void SEND_tram_next_step(int idTram, int idStation, int idLine) {
        idStation = convertId(idStation);
        trams.get(idTram).nextStep(stations.get(idStation));
        drawGame();
    }

    private int convertId(int idStation){
        for (Map.Entry<Integer, Station_UI> entry : stations.entrySet()) {
            if (entry.getValue().getId() == idStation) {
                idStation = entry.getKey();
            }
        }
        return idStation;
    }

    public void SEND_add_tram(int idStation, int idLine) {
        int idTram = trams.size();
        Tram_UI tram = new Tram_UI();
        trams.put(idTram, tram);
        idStation = convertId(idStation);
        trams.get(idTram).setLine(lines.get(listIdLines.get(idLine)), stations.get(idStation), this);
        trams.get(idTram).draw();
        lines.get(listIdLines.get(idLine)).addTram(tram);
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public ArrayList<Shape> getPeople(int id, int idStation) {
        return interface_ui.getPeople(id);
    }

    public void GET_add_people_station(int idStation, ArrayList<Shape> shape) {
        idStation = convertId(idStation);
        stations.get(idStation).setPeople(shape);
    }

    public Canvas renderCanvas(Canvas canvas){
        Color groundColor = Color.web("#2F2F2F");
        Color waterColor = Color.web("#527B8F");

        Layout map = new Layout();

        List<List<Integer>> backgroundMap = map.returnMap();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasWidth = canvas.getWidth(); //1000 -> 20
        double canvasHeight = canvas.getHeight(); //700 -> 14

        int row = (int)(canvasWidth/50);
        int column = (int)(canvasHeight/50);

        int cellSize = 50;

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if(backgroundMap.get(i).get(j) == 0){
                    gc.setFill(groundColor);
                    gc.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
                }else if(backgroundMap.get(i).get(j) == 1){
                    gc.setFill(waterColor);
                    gc.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
                }
            }
        }


        return canvas;
    }
}
