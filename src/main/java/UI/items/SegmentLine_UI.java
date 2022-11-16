package UI.items;

import UI.Game_UI;
import javafx.scene.paint.Color;

public class SegmentLine_UI {
    private Game_UI game_ui;
    private Station_UI station1;
    private Station_UI station2;
    private Color color;

    public SegmentLine_UI(Game_UI game_ui, Station_UI station1, Station_UI station2, Color color) {
        this.game_ui = game_ui;
        this.station1 = station1;
        this.station2 = station2;
        this.color = color;

    }

    public void draw(){
        game_ui.getGc().setStroke(color);
        game_ui.getGc().strokeLine(station1.getPos().x+(game_ui.getCellSize()/2), station1.getPos().y+(game_ui.getCellSize()/2), station2.getPos().x+(game_ui.getCellSize()/2), station2.getPos().y+(game_ui.getCellSize()/2));
    }

    public Station_UI getStation1() {
        return station1;
    }

    public Station_UI getStation2() {
        return station2;
    }
}
