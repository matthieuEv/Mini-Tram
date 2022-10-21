package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Game_UI extends GridPane {
    private Interface_UI interface_ui;
    public Game_UI(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;
        Label title = new Label("This is the game");
        Button btn = new Button("Get screen size");
        Button menuBtn = new Button("Go to menu");
        btn.setOnAction(e -> {
            System.out.println(interface_ui.getWIDTH()+" - "+ interface_ui.getHEIGHT());
        });
        menuBtn.setOnAction(e -> {
            interface_ui.startMenu();
        });
        this.add(title, 0, 0);
        this.add(btn, 0, 1);
        this.add(menuBtn, 0, 2);
    }
}
