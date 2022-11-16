package UI.items;

import UI.Game_UI;
import javafx.scene.paint.Color;
import model.data.format.Tram;

import java.util.ArrayList;

public class Line_UI {
    private Game_UI game_ui;
    private ArrayList<SegmentLine_UI> segments;
    private Color color;
    private ArrayList<Tram_UI> trams;
    private static int idCounter = 0;
    private int id;

    public Line_UI(Game_UI game_ui, Color color) {
        this.game_ui = game_ui;
        this.color = color;
        segments = new ArrayList<SegmentLine_UI>();
        trams = new ArrayList<Tram_UI>();
        id = idCounter;
        idCounter++;
    }

    public void draw(){
        for (SegmentLine_UI segment : segments) {
            segment.draw();
        }
        for (Tram_UI tram : trams) {
            tram.draw();
        }
    }

    public void addSegment(Station_UI station1, Station_UI station2){
        segments.add(new SegmentLine_UI(game_ui, station1, station2, color));
    }

    public void addTram(Tram_UI tram) {
        trams.add(tram);
    }

    public int getId() {
        return id;
    }
}
