package UI;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Type;
import java.net.URL;

public class Menu_UI extends GridPane {

    private Interface_UI interface_ui;

    private int widthBtn = 200;
    private int heightBtn = 50;

    private useJson json = new useJson();
    public Menu_UI(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;

        double width = interface_ui.getWIDTH();
        double height = interface_ui.getHEIGHT();

        final Canvas canvas = new Canvas(width,height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(new Image("file:src/textures/ui/backround_menu.png"),0,0,width,height);

        gc.setStroke(Color.WHITE);
        gc.strokeText("Mini Tram V0.0.1", width-100, height-10);

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
        System.out.println(">>> Start");
    }

    private void settings() {
        System.out.println(">>> Settings");

        HBox settings = new HBox();
        settings.setMaxHeight(interface_ui.getHEIGHT()/2);
        settings.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);");
        settings.setAlignment(Pos.CENTER);

        VBox holder = new VBox();
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(20);
        holder.setPadding(new Insets(0,50,0,50));
        holder.setMaxWidth(150);
        holder.setStyle("-fx-background-color: rgba(255,0,0,1);");

        Button quitSettings = new Button("Quit");
        quitSettings.setPadding(new Insets(5,30,5,30));
        quitSettings.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-size: 15px;");
        quitSettings.setOnAction(e -> {
            this.getChildren().remove(settings);
        });

        /*=== Music ===*/
        HBox musicContainer = new HBox();
        musicContainer.setAlignment(Pos.CENTER);
        musicContainer.setSpacing(20);
        FlowPane titleMusicPane = new FlowPane();
        Label titleMusic = new Label("Music");
        titleMusic.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        titleMusicPane.getChildren().add(titleMusic);
        titleMusicPane.setAlignment(Pos.CENTER_LEFT);

        FlowPane musicPane = new FlowPane();
        Slider music = new Slider(0,100,json.readJson("music"));
        music.setOnMouseReleased(e -> {
            registerValue("music",music.getValue());
        });
        musicPane.getChildren().add(music);
        musicPane.setAlignment(Pos.CENTER_RIGHT);
        musicContainer.getChildren().addAll(titleMusicPane,musicPane);

        /*=== SFX ===*/
        HBox sfxContainer = new HBox();
        sfxContainer.setAlignment(Pos.CENTER);
        sfxContainer.setSpacing(20);
        FlowPane titleSFXPane = new FlowPane();
        Label titleSfx = new Label("SFX");
        titleSfx.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        titleSFXPane.getChildren().add(titleSfx);
        titleSFXPane.setAlignment(Pos.CENTER_LEFT);

        FlowPane sfxPane = new FlowPane();
        Slider sfx = new Slider(0,100,json.readJson("sfx"));
        sfx.setOnMouseReleased(e -> {
            registerValue("sfx",sfx.getValue());
        });
        sfxPane.getChildren().add(sfx);
        sfxPane.setAlignment(Pos.CENTER_RIGHT);
        sfxContainer.getChildren().addAll(titleSFXPane,sfxPane);

        holder.getChildren().addAll(musicContainer,sfxContainer,quitSettings);
        settings.getChildren().addAll(holder);

        this.add(settings,0,0);
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
                btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#000000")));
                super.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle("-fx-background-color: #005eff; -fx-text-fill: white; -fx-font-size: 20px;");
                btn.setEffect(null);
                super.setCursor(javafx.scene.Cursor.DEFAULT);
            }});
    }

    private void registerValue(String type,Number value) {
        System.out.println(">>> Slider "+type+": " + value.intValue());
        json.writeJson(type,value.intValue());
    }
}
