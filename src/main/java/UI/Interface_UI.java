package UI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Interface_UI {
    protected Stage stage;
    protected double WIDTH = 800;
    protected double HEIGHT = 600;
    public Interface_UI(Stage stage) {
        this.stage = stage;


        Scene scene = new Scene(new Game_UI(this), WIDTH, HEIGHT);
        stage.setTitle("Mini Tram");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public double getWIDTH() {
        WIDTH = stage.getWidth();
        return WIDTH;
    }

    public double getHEIGHT() {
        HEIGHT = stage.getHeight();
        return HEIGHT;
    }
}
