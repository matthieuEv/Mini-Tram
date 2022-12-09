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
    private Canvas background;


    private Music music = new Music();
    //private ArrayList<Station_UI> stations;

    /**
     * Generate the default element of the game and show it on the screen
     * Start the timer
     * @param interface_ui the interface ui
     */
    public Game_UI(Interface_UI interface_ui) {
        super();

        this.interface_ui = interface_ui;

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


        background = new Canvas(interface_ui.getWIDTH(), interface_ui.getHEIGHT());


        this.getChildren().addAll(background, clock, gamePane, btnStation);

        tempLine = new Line();
        tempLine.setStrokeWidth(5);
        gamePane.getChildren().add(tempLine);


    }

    /**
     * Add a station on the map
     */
    private void addStation() {
        Map<Integer, Pos> listStation = interface_ui.getListStations();

        for(Map.Entry<Integer, Pos> entry : listStation.entrySet()) {
            int id = setSingleId(entry.getValue());
            Shape shape = interface_ui.getShapeStation(entry.getKey());
            stations.put(id, new Station_UI(this, entry.getValue(), entry.getKey(), shape));
        }
    }

    /**
     * Add a station on the map
     * @param id_st the id of the station
     * @param pos the position of the station
     */
    public void addStation(int id_st, Pos pos) {
        int id = setSingleId(new Pos(pos.x*cellSize, pos.y*cellSize));
        Shape shape = interface_ui.getShapeStation(id_st);
        stations.put(id, new Station_UI(this, new Pos(pos.x*cellSize, pos.y*cellSize), id_st, shape));
    }

    /**
     * Get the width of the window
     * @return the width of the window
     */
    public double getWIDTH() {
        return interface_ui.getWIDTH();
    }

    /**
     * Get the height of the window
     * @return the height of the window
     */
    public double getHEIGHT() {
        return interface_ui.getHEIGHT();
    }

    /**
     * Is used for the definition of the different fonctionnement of the mouse like the drag and drop
     * Used for draw line or expand the line
     * @param interface_ui the interface_ui to set
     */
    public void setInterface_ui(Interface_UI interface_ui) {
        this.interface_ui = interface_ui;
        background = renderCanvas(background);
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


                                        lastSelectedStation = selectedStation;
                                        tempLine.setStartX(lastSelectedStation.getPos().x + cellSize / 2);
                                        tempLine.setStartY(lastSelectedStation.getPos().y + cellSize / 2);
                                    }
                                }
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


                                lastSelectedStation = selectedStation;
                                tempLine.setStartX(lastSelectedStation.getPos().x + cellSize / 2);
                                tempLine.setStartY(lastSelectedStation.getPos().y + cellSize / 2);
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

    /**
     * Arround the value to the nearest cell
     * @param value the value to arround
     * @return the value arround
     */
    public int arroundValue(int value) {
        return ((value/cellSize)*cellSize);
    }

    /**
     * Generate a single id for a position
     * @param pos the position
     * @return the single id
     */
    public int setSingleId(Pos pos){
        int cmx = (int)interface_ui.getWIDTH()/cellSize;
        int cmy = (int)interface_ui.getHEIGHT()/cellSize;
        return (pos.x/cellSize) + (pos.y/cellSize)*cmx;
    }

    /**
     * Get the cellule size for the ui
     * @return the cellule size
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Generate 4 buttons to select the line color
     */
    private void drawDebugMenu(){
        addBtnStation(Color.RED);
        addBtnStation(Color.BLUE);
        addBtnStation(Color.GREEN);
        addBtnStation(Color.VIOLET);
    }

    /**
     * Generate a new button for a new line
     * @param color The color of the line
     */
    public void addBtnStation(Color color){
        Circle btn = new Circle(10, color);
        listBtnLines.put(color, btn);
        btn.setOnMouseClicked(e -> {
            listBtnLines.get(color).setTranslateX(-20);
            selectedLine = color;
        });
        btnStation.getChildren().add(btn);
    }

    /**
     * Send the next step to the tram and update the position of the tram
     * @param idTram the id of the tram
     * @param idStation the id of the station
     * @param idLine the id of the line
     * @param time the time for the tram animation
     */
    public void SEND_tram_next_step(int idTram, int idStation, int idLine, int time) {
        idStation = convertId(idStation);
        trams.get(idTram).nextStep(stations.get(idStation), time);
    }

    /**
     * Convert the id of station in model to the id of the station in the interface
     * @param idStation the id of the station
     * @return the new id for the ui
     */
    private int convertId(int idStation){
        for (Map.Entry<Integer, Station_UI> entry : stations.entrySet()) {
            if (entry.getValue().getId() == idStation) {
                idStation = entry.getKey();
            }
        }
        return idStation;
    }

    /**
     * Add a new tram on the line
     * @param idStation the id of the station
     * @param idLine the id of the line
     */
    public void SEND_add_tram(int idStation, int idLine) {
        int idTram = trams.size();
        Tram_UI tram = new Tram_UI();
        trams.put(idTram, tram);
        idStation = convertId(idStation);
        trams.get(idTram).setLine(lines.get(listIdLines.get(idLine)), stations.get(idStation), this);
        lines.get(listIdLines.get(idLine)).addTram(tram);
    }

    /**
     * Use for the object to get the game pane
     * @return the pane of the game
     */
    public Pane getGamePane() {
        return gamePane;
    }

    /**
     * Get the list of people in the tram an send it to the tram UI
     * @param id the id of the tram
     * @return the list of people in the tram
     */
    public ArrayList<Shape> getPeople(int id) {
        return interface_ui.getPeople(id);
    }

    /**
     * Get the list of people and send the list to the right station
     * @param idStation The id of the station
     * @param shape a list of shape to show the people
     */
    public void GET_add_people_station(int idStation, ArrayList<Shape> shape) {
        idStation = convertId(idStation);
        stations.get(idStation).setPeople(shape);
    }

    /**
     * Generate a canvas with the background draw on it
     * @param canvas The canvas to draw on
     * @return The canvas with the grid drawn on it
     */
    public Canvas renderCanvas(Canvas canvas){
        Color groundColor = Color.web("#2F2F2F");
        Color waterColor = Color.web("#527B8F");

        Layout map = interface_ui.getMap();

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
