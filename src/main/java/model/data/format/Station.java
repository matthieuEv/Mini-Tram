package model.data.format;

import utils.Pos;
import utils.Shape;

public class Station {
    static int id_counter = 0;
    private int id;
    private Pos pos;
    Shape shape;

    public Station(Shape shape, Pos pos) {
        this.id = id_counter;
        id_counter++;
        this.shape = shape;
        this.pos = pos;
    }

    public int get_id() {
        return id;
    }

    public Shape getShape() {
        return shape;
    }

    public Pos get_pos() {
        return pos;
    }
}
