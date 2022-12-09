package UI.menu;

import UI.Interface_UI;
import UI.music.Music;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Menu_UI extends GridPane {

    private Interface_UI interface_ui;

    private int widthBtn = 200;
    private int heightBtn = 50;

    private UseJson json = new UseJson();

    /**
     * Create the menu of the game
     * @param interface_ui
     */
    public Menu_UI(Interface_UI interface_ui) {
        super();

        this.interface_ui = interface_ui;

        double width = interface_ui.getWIDTH();
        double height = interface_ui.getHEIGHT();

        final Canvas canvas = new Canvas(width,height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(new Image("file:src/file/textures/ui/backround_menu_newUI.png"),0,0,width,height);

        gc.setStroke(Color.web("#7C99AC"));
        gc.strokeText("Mini Tram V0.1.0", width-100, height-10);

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

        Button mode = new Button("Modes");
        initBtn(mode);
        btnHover(mode);
        mode.setOnAction(e -> {
            //TODO
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

    /**
     * Start the game
     */
    private void start() {
        interface_ui.startGame();
    }

    /**
     * Create the settings of the game in HBOX
     */
    private void settings() {

        HBox settings = new HBox();
        settings.setMaxHeight(interface_ui.getHEIGHT()/2);
        settings.setStyle("-fx-background-color: rgba(146,169,189,0.8);");

        settings.setAlignment(Pos.CENTER);

        VBox holder = new VBox();
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(20);
        holder.setPadding(new Insets(0,50,0,50));
        holder.setMaxWidth(150);
        holder.setStyle("-fx-background-color: rgba(146,169,189,1);");

        Button quitSettings = new Button("Quit");
        settingsHover(quitSettings);
        quitSettings.setPadding(new Insets(5,30,5,30));
        quitSettings.setStyle("-fx-background-color: #d3dedc; -fx-text-fill: #7c99ac; -fx-font-size: 15px;");
        quitSettings.setOnAction(e -> {
            this.getChildren().remove(settings);
        });

        /*=== Music ===*/
        HBox musicContainer = new HBox();
        musicContainer.setAlignment(Pos.CENTER);
        musicContainer.setSpacing(20);
        FlowPane titleMusicPane = new FlowPane();
        Label titleMusic = new Label("Music");
        titleMusic.setStyle("-fx-font-size: 20px; -fx-text-fill: #d3dedc;");
        titleMusicPane.getChildren().add(titleMusic);
        titleMusicPane.setAlignment(Pos.CENTER_LEFT);

        FlowPane musicPane = new FlowPane();
        Slider musicSlider = new Slider(0,100,json.readJson("music"));
        settingsHover(musicSlider);
        musicSlider.setOnMouseReleased(e -> {
            registerValue("music",musicSlider.getValue());
            interface_ui.getMusic().changeVolume(musicSlider.getValue());
        });
        musicPane.getChildren().add(musicSlider);
        musicPane.setAlignment(Pos.CENTER_RIGHT);
        musicContainer.getChildren().addAll(titleMusicPane,musicPane);

        /*=== SFX ===*/
        HBox sfxContainer = new HBox();
        sfxContainer.setAlignment(Pos.CENTER);
        sfxContainer.setSpacing(20);
        FlowPane titleSFXPane = new FlowPane();
        Label titleSfx = new Label("SFX");
        titleSfx.setStyle("-fx-font-size: 20px; -fx-text-fill: #d3dedc;");
        titleSFXPane.getChildren().add(titleSfx);
        titleSFXPane.setAlignment(Pos.CENTER_LEFT);

        FlowPane sfxPane = new FlowPane();
        Slider sfx = new Slider(0,100,json.readJson("sfx"));
        settingsHover(sfx);
        sfx.setOnMouseReleased(e -> {
            registerValue("sfx",sfx.getValue());
        });
        sfxPane.getChildren().add(sfx);
        sfxPane.setAlignment(Pos.CENTER_RIGHT);
        sfxContainer.getChildren().addAll(titleSFXPane,sfxPane);

        Button resetSettings = new Button("Reset");
        settingsHover(resetSettings);
        resetSettings.setPadding(new Insets(5,30,5,30));
        resetSettings.setStyle("-fx-background-color: #d3dedc; -fx-text-fill: #7c99ac; -fx-font-size: 15px;");
        resetSettings.setOnAction(e -> {
            reset(musicSlider, sfx);
            interface_ui.getMusic().changeVolume(musicSlider.getValue());
        });

        holder.getChildren().addAll(musicContainer,sfxContainer,quitSettings,resetSettings);
        settings.getChildren().addAll(holder);

        this.add(settings,0,0);
    }

    /**
     * Exit the game when the window is closed
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Initialize the button
     * @param btn
     */
    private void initBtn(Button btn){
        btn.setStyle("-fx-background-color: #92a9bd; -fx-text-fill: #ffefef; -fx-font-size: 20px;");
        btn.setPrefSize(widthBtn, heightBtn);
    }

    /**
     * Hover effect on the button
     * @param btn
     */
    private void btnHover(Button btn) {
        btn.hoverProperty( ).addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btn.setStyle("-fx-background-color: #7c99ac; -fx-text-fill: #ffefef; -fx-font-size: 20px;");
                super.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle("-fx-background-color: #92a9bd; -fx-text-fill: #ffefef; -fx-font-size: 20px;");
                super.setCursor(javafx.scene.Cursor.DEFAULT);
            }});
    }

    /**
     * Hover effect on the slider
     * @param btn
     */
    private void settingsHover(Control btn){
        btn.hoverProperty( ).addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                super.setCursor(javafx.scene.Cursor.HAND);
            } else {
                super.setCursor(javafx.scene.Cursor.DEFAULT);}});
    }

    /**
     * register value in the json file
     * @param type
     * @param value
     */
    private void registerValue(String type,Number value) {
        json.writeJson(type,value.intValue());
    }

    /**
     * Reset the settings
     * @param slider1
     * @param slider2
     */
    private void reset(Slider slider1, Slider slider2) {
        json.reset();
        slider1.setValue(75);
        slider2.setValue(75);
    }
}
