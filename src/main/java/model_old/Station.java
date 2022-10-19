package model_old;

import model_old.line.Line;
import model_old.people.Transit;
import utils.*;

import java.util.Map;

public class Station {
    //Ids for identification
    private static int count_id;
    private int id;

    //Attributes
    private Shape shape;
    private Pos pos;

    //Container for interactions
    private Map<Integer, Line> list_line;
    private Map<Integer, Transit> list_transit;


    /* === constructor === */

    /**
     * Create a new station (for now manually created)
     * @param shape The shape of the station to determinate which people can start and end here
     * @param pos   The position of the station in the grid
     */
    public Station(Shape shape, Pos pos) {
        this.id = count_id;
        count_id++;
        this.shape = shape;
        this.pos = pos;
    }

    /* === Public Methods === */

    /**
     * Add a
     */


    /* === Static methods === */

    /**
     * Get the number of stations created
     * @return The number of stations created
     */
    public static int getCountId(){
        return count_id;
    }

    /**
     * Create a new station with random shape and position
     * @return The new station
     */
    public static Station create_new(Pos pos){
        return new Station(Shape.random_shape(),pos) {
        };
    }


    /* === Getters and setters === */

    /**
     * Get the id of the station
     * @return The id of the station
     */
    public int getId() {
        return id;
    }

    public Shape get_shape() {
        return shape;
    }

    public Map<Integer,Transit> get_list_people(){
        return this.list_transit;
    }

    public Map<Integer, Line> get_list_line() {
        return list_line;
    }
}
