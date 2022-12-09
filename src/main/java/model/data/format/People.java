package model.data.format;

import utils.Shape;

public class People {
    private static int id_counter = 0;
    private int id;

    private Shape destination;

    public People(Shape shape){
        this.id = id_counter;
        id_counter++;
        this.destination = shape;
    }

    public static int getId_counter() {
        return id_counter;
    }

    public int get_id() {
        return id;
    }

    public Shape getDestination() {
        return destination;
    }

    static public void reset_id_counter() {
        id_counter = 0;
    }
}
