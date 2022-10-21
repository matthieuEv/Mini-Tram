package UI.items;

import UI.Game_UI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import utils.Pos;

import java.util.Random;

public class Station_UI {
    private Game_UI game_ui;
    private Pos pos;
    private boolean selected;
    public Station_UI(Game_UI game_ui, Pos pos) {
        super();
        this.game_ui = game_ui;
        this.pos = pos;

        //System.out.println(pos.x + " " + pos.y);
    }

    public void draw() {
        if (selected) {
            game_ui.getGc().setFill(Color.YELLOW);
        } else {
            game_ui.getGc().setFill(Color.RED);
        }
        game_ui.getGc().fillOval(pos.x, pos.y, game_ui.getCellSize(), game_ui.getCellSize());
    }

    public void clicked() {
        draw();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Pos getPos() {
        return pos;
    }
}
