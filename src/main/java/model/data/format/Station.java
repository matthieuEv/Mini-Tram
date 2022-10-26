package model.data.format;

import utils.Shape;

public class Station {
    static int id_counter = 0;
    private int id;
    Shape shape;

    public Station(Shape shape){
        this.id = id_counter;
        id_counter++;
        this.shape = shape;
    }

    public int get_id() {
        return id;
    }

    public Shape getShape() {
        return shape;
    }
}
