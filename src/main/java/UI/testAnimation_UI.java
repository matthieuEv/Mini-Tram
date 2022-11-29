package UI;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

public class testAnimation_UI extends FlowPane {
    private Interface_UI interface_ui;
    private int x;
    private int y;
    private int angle;
    private TranslateTransition menue_translatetransition;
    private RotateTransition rotateTransition;
    public testAnimation_UI(Interface_UI interface_ui) {
        this.interface_ui = interface_ui;
        this.setPrefSize(interface_ui.getWIDTH(), interface_ui.getHEIGHT());
        x=100;
        y=100;
        angle=50;
        //this.setStyle("-fx-background-color: #424040;");

        //FlowPane tram = new FlowPane(new ImageView(new Image("file:src/textures/ui/train.png")));
        ImageView tram = new ImageView(new Image("file:src/textures/ui/train.png"));
        tram.setTranslateX(100+x);
        tram.setTranslateY(100+y);

        rotateTransition = new RotateTransition(Duration.seconds(1), tram);
        rotateTransition.setFromAngle(angle);
        rotateTransition.setToAngle(angle+180);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setOnFinished(event -> {
            this.angle+=180;
            rotateTransition.setFromAngle(angle);
            rotateTransition.setToAngle(angle+180);
            menue_translatetransition.play();
        });

        menue_translatetransition = new TranslateTransition(Duration.seconds(1), tram);
        menue_translatetransition.setFromY(100+x);
        menue_translatetransition.setFromX(100+x);
        menue_translatetransition.setToY(100-x);
        menue_translatetransition.setToX(100-x);
        menue_translatetransition.setInterpolator(Interpolator.LINEAR);
        menue_translatetransition.setOnFinished(event -> {
            this.x=-x;
            this.y=-y;
            menue_translatetransition.setFromY(100+x);
            menue_translatetransition.setFromX(100+x);
            menue_translatetransition.setToY(100-x);
            menue_translatetransition.setToX(100-x);
            rotateTransition.play();
        });

        rotateTransition.play();

        //menue_translatetransition.play();


        this.getChildren().add(tram);

    }
}
