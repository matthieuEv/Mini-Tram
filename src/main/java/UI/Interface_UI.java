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

        GridPane root = new GridPane();

        Menu_UI menu_ui = new Menu_UI(root);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Mini Tram");
        stage.setScene(scene);
        stage.show();

    }
}
