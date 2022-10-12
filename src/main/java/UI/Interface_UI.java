package UI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Interface_UI {
    protected Stage stage;
    protected int WIDTH = 800;
    protected int HEIGHT = 600;
    public Interface_UI(Stage stage) {
        this.stage = stage;


        Scene scene = new Scene(new Menu_UI(), WIDTH, HEIGHT);
        stage.setTitle("Mini Tram");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}
