package UI;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu_UI extends GridPane {

    private Interface_UI interface_ui;

    private int widthBtn = 200;
    private int heightBtn = 50;
    public Menu_UI(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;

        double width = interface_ui.getWIDTH();
        double height = interface_ui.getHEIGHT();

        final Canvas canvas = new Canvas(width,height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Stop[] stops = new Stop[] { new Stop(0, Color.web("#005eff")), new Stop(1, Color.web("#44006e"))};
        LinearGradient background = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(background);
        gc.fillRect(0,0,width,height);

        VBox middle = new VBox();
        middle.setAlignment(Pos.CENTER);
        middle.setSpacing(50);

        /*=== Buttons ===*/
        Button start = new Button("Start");
        initBtn(start);
        btnHover(start);
        start.setOnAction(e -> {
            start();
        });

        Button settings = new Button("Settings");
        initBtn(settings);
        btnHover(settings);
        settings.setOnAction(e -> {
            settings();
        });

        Button exit = new Button("Exit");
        initBtn(exit);
        btnHover(exit);
        exit.setOnAction(e -> {
            exit();
        });

        middle.getChildren().addAll(start,settings,exit);
        middle.setAlignment(Pos.CENTER);

        this.add(canvas,0,0);
        this.add(middle,0,0);
    }

    private void start() {
    }

    private void settings() {
    }

    private void exit() {
        System.exit(0);
    }



    private void initBtn(Button btn){
        btn.setStyle("-fx-background-color: #005eff; -fx-text-fill: white; -fx-font-size: 20px;");
        btn.setPrefSize(widthBtn, heightBtn);
    }

    private void btnHover(Button btn) {
        btn.hoverProperty( ).addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btn.setStyle("-fx-background-color: #003a9e; -fx-text-fill: white; -fx-font-size: 20px;");
                btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#44006e")));
                super.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle("-fx-background-color: #005eff; -fx-text-fill: white; -fx-font-size: 20px;");
                btn.setEffect(null);
                super.setCursor(javafx.scene.Cursor.DEFAULT);
            }});
    }
}
