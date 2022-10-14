package model;

import model.line.Line;
import model.people.Transit;
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


    /* === Getters and setters === */

    /**
     * Get the id of the station
     * @return The id of the station
     */
    public int getId() {
        return id;
    }
}
