package UI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu_UI extends GridPane {

    private Interface_UI interface_ui;
    public Menu_UI(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;

        final Canvas canvas = new Canvas(interface_ui.getWIDTH(),interface_ui.getHEIGHT());

        System.out.println(interface_ui.getWIDTH()+" "+interface_ui.getHEIGHT());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        this.add(canvas,0,0);
    }
}
