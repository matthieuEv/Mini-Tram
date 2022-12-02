package com.minitram.minitram;

import UI.Interface_UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ModelEntryPoint;
import model.compute.Layout;
import presenter.Main_Presenter;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        ModelEntryPoint model = ModelEntryPoint.getInstance();
        Interface_UI ui = Interface_UI.getInstance(stage);
        Main_Presenter presenter = Main_Presenter.getInstance(model, ui);
        presenter.init();



        //Interface_UI interface_ui = Interface_UI.getInstance(stage);

    }

    public static void main(String[] args) {
        launch();/*
        //A supprimer
        Layout layout = new Layout();
        System.out.println(layout.returnMap());*/

    }
}