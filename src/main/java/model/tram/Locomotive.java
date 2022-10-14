package model.tram;

import model.line.Line;
import model.Station;

import java.util.List;

public class Locomotive extends Tram {
    //Container for interactions
    private List<Wagon> list_wagon;
    private Station current_stop;
    private Station next_stop;

    //Constructor
    /**
     * Create a new locomotive
     */
    public Locomotive() {
        super();
    }

    /* === Activator === */
    /**
     * Activate the locomotive
     * (For now only start on the first station going to the second one)
     * @param capacity The capacity of the locomotive
     * @param line  The line on which the locomotive is
     */
    public void activate(int capacity, Line line) {
        super.activate(capacity, line);
        this.current_stop = line.get_station(0);
        this.next_stop = line.get_station(0);
    }

    /* === Methods === */

    /**
     * Add a wagon to the locomotive
     * @param wagon The wagon to add
     */
    public void addWagon(Wagon wagon) {
        this.list_wagon.add(wagon);
    }

    /* === Getters === */
    public Line getLine() {
        return this.line;
    }

    /* === Setters === */
}

