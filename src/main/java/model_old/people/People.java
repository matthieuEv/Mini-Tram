package model_old.people;

import model_old.Station;
import utils.Shape;

public abstract class People {
    //Ids for identification
    private static int count_id;
    private final int id;
    //Attributes
    private Shape destination;
    public People(Shape destination){
        this.destination = destination;
        this.id = count_id++;
    }

    /* === Static methods === */
    public static int getCountId(){
        return count_id;
    }
    public static People create_new(Station station){
        return new Transit(Shape.random_shape(),station) {
        };
    }

    /* === GETTERS === */
    public int get_id(){
        return this.id;
    }
    public Shape get_destination(){
        return this.destination;
    }
}
