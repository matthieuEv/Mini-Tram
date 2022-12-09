package UI.items;

import UI.Game_UI;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class shape_UI {
    private static shape_UI instance = null;
    private static Game_UI game_ui;

    /**
     * Init the shape UI
     * @param game_ui the game_ui
     */
    private shape_UI(Game_UI game_ui) {
        this.game_ui = game_ui;
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static shape_UI getInstance(Game_UI game_ui) {
        if (instance == null) {
            instance = new shape_UI(game_ui);
        }
        return instance;
    }

    /**
     * Contains all the shapes and return the object to show
     * @param shape the shape to show
     * @return the shape object
     */
    public static Shape getShape(utils.Shape shape){
        return getShape(shape, 1);
    }

    /**
     * Contains all the shapes and return the object to show
     * @param shape the shape to show
     * @param size the size of the shape
     * @return the shape object
     */
    public static Shape getShape(utils.Shape shape, double size){
        switch (shape){
            case ROUND:
                Circle circle = new Circle((game_ui.getCellSize()/2)*size);
                circle.setCenterX((game_ui.getCellSize()/2)*size);
                circle.setCenterY((game_ui.getCellSize()/2)*size);
                return circle;
            case SQUARE:
                return new Rectangle(0, 0, (game_ui.getCellSize()*size), (game_ui.getCellSize()*size));
            case TRIANGLE:
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(
                        (double) ((game_ui.getCellSize()*size) / 2f), (double) (0),
                        (double) ((game_ui.getCellSize()*size)), (double) ((game_ui.getCellSize()*size)),
                        (double) (0), (double) ((game_ui.getCellSize()*size))
                );
                return triangle;
            case DIAMOND:
                Polygon diamond = new Polygon();
                diamond.getPoints().addAll(
                        (double) ((game_ui.getCellSize()*size) / 2f), (double) (0),
                        (double) ((game_ui.getCellSize()*size)), (double) ((game_ui.getCellSize()*size) / 4f),
                        (double) ((game_ui.getCellSize()*size)), (double) ((game_ui.getCellSize()*size)*3 /4f),
                        (double) ((game_ui.getCellSize()*size) / 2f), (double) ((game_ui.getCellSize()*size)),
                        (double) (0), (double) ((game_ui.getCellSize()*size)*3/4f),
                        (double) (0), (double) ((game_ui.getCellSize()*size) / 4f));

                return diamond;
            default:
                return null;
        }
    }
}


