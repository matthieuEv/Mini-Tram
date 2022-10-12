package UI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Menu_UI extends GridPane {
    public Menu_UI() {
        super();
        Button btn = new Button("Start");
        this.add(btn, 0, 0);
    }
}
