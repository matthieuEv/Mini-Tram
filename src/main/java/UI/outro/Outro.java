package UI.outro;

import UI.Interface_UI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Outro extends GridPane {
    private Interface_UI interface_ui;
    private Label label;
    private int widthBtn = 200;
    private int heightBtn = 50;

    /**
     * Create the outro screen
     * @param interface_ui
     */
    public Outro(Interface_UI interface_ui) {
        super();
        this.interface_ui = interface_ui;
        this.label = new Label();

        VBox container = new VBox();

        container.setAlignment(Pos.CENTER);
        double width = interface_ui.getWIDTH();
        double height = interface_ui.getHEIGHT();
        container.setMinWidth(width);
        container.setMinHeight(height);
        container.setStyle("-fx-background-color: #312f31;");

        Button menu = new Button("Return to menu");
        initBtn(menu);
        btnHover(menu);
        menu.setOnAction(e -> {
            System.out.println("Return to menu");
            interface_ui.startMenu();
        });

        Label gameOver = new Label("Game Over");
        gameOver.setStyle("-fx-font-size: 60px; -fx-text-fill: #ffffff;-fx-padding: 0 0 50px 0;-fx-font-weight: bold;");
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff;-fx-padding: 0 0 50px 0;");

        container.getChildren().addAll(gameOver,label, menu);

        this.add(container, 0, 0);

    }

    /**
     * Set the text of the label with the score
     * @param people
     * @param day
     */
    public void setEndLabel(int people, int day) {
        this.label.setText("You survived " + day + " days, and " + people + " people traveled with you.");
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
}
