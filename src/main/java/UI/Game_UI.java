package UI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Game_UI extends GridPane {
    private Interface_UI interface_ui;
    public Game_UI(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;
        Button btn = new Button("Start");
        btn.setOnAction(e -> {
            System.out.println(interface_ui.getWIDTH()+" - "+ interface_ui.getHEIGHT());
        });
        this.add(btn, 0, 0);
    }
}
