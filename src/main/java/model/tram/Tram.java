package model.tram;

import model.line.Line;
import model.people.Voyager;

import java.util.List;

public abstract class Tram {
    //Ids for identification
    private static int count_id;
    private final int id;

    //Attributes
    protected boolean is_used;
    protected int capacity;

    //Container for interactions
    protected Line line;
    private List<Voyager> list_passages;

    //Constructor
    /**
     * Create a new tram and store it
     */
    public Tram() {
        this.id = count_id;
        count_id++;
        this.is_used = false;
    }

    /* === Activator === */
    /**
     * Activate the tram
     * @param capacity The capacity of the tram
     * @param line  The line on which the tram is
     */
    public void activate(int capacity, Line line) {
        this.is_used = true;
        this.capacity = capacity;
        this.line = line;
    }

    /**
     * Deactivate the tram
     */
    public void deactivate() {
        this.is_used = true;
        this.capacity = 0;
        this.line.remove(this);
        this.line = null;
    }

    /* === Methods === */

    /* === Getters === */
    public int getId() {
        return this.id;
    }
    public boolean isActivated() {
        return this.is_used;
    }


}
