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

        stage.setTitle("Mini Tram");
        stage.setMaximized(true);
        stage.show();
        Scene scene = new Scene(new Menu_UI(this), WIDTH, HEIGHT);
        stage.setScene(scene);
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
