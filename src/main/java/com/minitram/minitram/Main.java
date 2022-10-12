package com.minitram.minitram;

import UI.Interface_UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new Interface_UI(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}