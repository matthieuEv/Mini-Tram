package UI.items;

import UI.Game_UI;
import javafx.scene.paint.Color;
import model.data.format.Tram;
import utils.Shape;

import java.util.ArrayList;

public class Line_UI {
    private Game_UI game_ui;
    private ArrayList<SegmentLine_UI> segments;
    private Color color;
    private ArrayList<Tram_UI> trams;
    private static int idCounter = 0;
    private int id;

    /**
     * Instanciate a new line
     * @param game_ui the game ui
     * @param color the color of the line
     */
    public Line_UI(Game_UI game_ui, Color color) {
        this.game_ui = game_ui;
        this.color = color;
        segments = new ArrayList<SegmentLine_UI>();
        trams = new ArrayList<Tram_UI>();
        id = idCounter;
        idCounter++;
    }

    /**
     * When the line is created or extended, we add a new segment between the two stations
     * @param station1 the first station of the segment
     * @param station2 the second station of the segment
     */
    public void addSegment(Station_UI station1, Station_UI station2){
        segments.add(new SegmentLine_UI(game_ui, station1, station2, color));
    }

    /**
     * Add a tram to the line
     * @param tram the tram to add
     */
    public void addTram(Tram_UI tram) {
        trams.add(tram);
    }

    /**
     * Get the id of the line
     * @return the id of the line
     */
    public int getId() {
        return id;
    }

    /**
     * Get the color of the line
     * @return the color of the line
     */
    public Color getColor() {
        return color;
    }

    /**
     * Test if the line contains a station
     * @param station the id of the tram
     * @return true if the line contains the station else false
     */
    public boolean containsStation(Station_UI station){
        for (SegmentLine_UI segment : segments) {
            if(segment.getStation1() == station || segment.getStation2() == station){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the list of people in the tram
     * @param id the id of the tram
     * @return the list of shape of the people
     */
    public ArrayList<Shape> getPeople(int id) {
        return game_ui.getPeople(id);
    }
}
