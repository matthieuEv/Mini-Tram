package UI.items;

import UI.Game_UI;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.concurrent.Flow;

public class SegmentLine_UI {
    private Game_UI game_ui;
    private Pane flowPane;
    private Station_UI station1;
    private Station_UI station2;
    private Color color;
    private Pane gamePane;
    private Line line;

    /**
     * Instanciate a new segment of a line between two stations and show it on the gamePane
     * @param game_ui the game_ui
     * @param station1 the first station
     * @param station2 the second station
     * @param color the color of the line
     */
    public SegmentLine_UI(Game_UI game_ui, Station_UI station1, Station_UI station2, Color color) {
        this.game_ui = game_ui;
        this.station1 = station1;
        this.station2 = station2;
        this.gamePane= game_ui.getGamePane();
        this.color = color;

        line = new Line(station1.getPos().x+game_ui.getCellSize()/2, station1.getPos().y+game_ui.getCellSize()/2, station2.getPos().x+game_ui.getCellSize()/2, station2.getPos().y+game_ui.getCellSize()/2);
        line.setStroke(color);
        line.setStrokeWidth(5);
        gamePane.getChildren().add(line);
    }

    /**
     * Return the first station of the segment of line
     * @return the first station of the segment of line
     */
    public Station_UI getStation1() {
        return station1;
    }

    /**
     * Return the second station of the segment of line
     * @return the second station of the segment of line
     */
    public Station_UI getStation2() {
        return station2;
    }
}
