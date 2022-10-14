package UI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Interface_UI {
    protected Stage stage;
    protected double WIDTH = 1000;
    protected double HEIGHT = 700;
    public Interface_UI(Stage stage) {
        this.stage = stage;


        Scene scene = new Scene(new Game_UI(this), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Mini Tram");
        //stage.setMaximized(true);
        stage.show();
    }

    public double getWIDTH() {
        //WIDTH = stage.getWidth();
        return WIDTH;
    }

    public double getHEIGHT() {
        //HEIGHT = stage.getHeight();
        return HEIGHT;
    }
}
