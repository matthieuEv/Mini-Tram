package UI.intro;

import UI.Interface_UI;
import UI.menu.UseJson;
import UI.music.Music;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Intro extends GridPane {
    private Interface_UI interface_ui;

    private int widthBtn = 200;
    private int heightBtn = 50;

    private UseJson json = new UseJson();

    public Intro(Interface_UI interface_ui) {
        super();

        this.interface_ui = interface_ui;

        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: #312f31;");

        double width = interface_ui.getWIDTH();
        double height = interface_ui.getHEIGHT();
        System.out.println(width + " " + height);
        container.setMinWidth(width);
        container.setMinHeight(height);

        MediaPlayer media_player;
        Media media = new Media(new File("src/file/textures/video/load2.mp4").toURI().toString());
        media_player = new MediaPlayer(media);
        MediaView viewer = new MediaView(media_player);

        // Ajuster automatiquement la vue vidéo à la taille de la scène :

        DoubleProperty widthV = viewer.fitWidthProperty();
        widthV.set(width);
        DoubleProperty heightV = viewer.fitHeightProperty();
        heightV.set(height);
        widthV.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        heightV.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));

        viewer.setPreserveRatio(true);
        Button play = new Button("Play");
        play.setOnAction(e -> {
            interface_ui.startMenu();
        });
        media_player.play();
        media_player.setOnEndOfMedia(() -> {
            interface_ui.startMenu();
        });
        container.getChildren().addAll(viewer);

        this.add(container, 0, 0);
    }
}
