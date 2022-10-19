package model_old.line;

import model_old.Station;
import model_old.tram.*;
import utils.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Line {
    //Ids for identification
    private static int count_id;
    protected final int id;

    //Attributes
    protected boolean is_used;

    //Container for interactions
    protected List<Station> list_station = new ArrayList<>();
    protected Map<Integer, Tram> list_tram = new HashMap<>();


    //Constructor
    /**
     * Create a new line between two existing stations (need an available tram to do so)
     */
    public Line() {
        //Use an id and increment for the next one
        this.id = count_id;
        count_id++;
        is_used = false;
    }
    public Line(Line line) {
        this.id = line.id;
        this.is_used = line.is_used;
        this.list_station = line.list_station;
        this.list_tram = line.list_tram;
    }

    /* === Activator === */
    /**
     * Activate a new line between two existing stations (need an available tram to do so)
     * @param station1 The first station
     * @param station2  The second station
     * @param tram The first tram that will be on the line
     */
    public void activate(Station station1, Station station2,Tram tram) throws Exception {
        if (station1 == null || station2 == null || tram == null) {
            if (station1 == null) {
                throw new Exception("Station 1 is null");
            } else if (station2 == null) {
                throw new Exception("Station 2 is null");
            } else {
                throw new Exception("Tram is null");
            }
        } else if (station1.equals(station2)) {
            throw new Exception("The two stations are the same");
        } else if (tram.is_activated()) {
            throw new Exception("The tram is already used");
        } else {
            //Add the stations to the line
            this.list_station.add(station1);
            this.list_station.add(station2);
            //The activation of the tram is done in the mediator
            //Add the tram to the line
            this.list_tram.put(tram.getId(), tram);
            //Activate the line
            this.is_used = true;
        }
    }
    public void desactivate() {
        //Remove itself
        //Remove the stations from the line
        this.list_station.clear();
        //Remove the trams from the line
        this.list_tram.clear();
        //Desactivate the line
        this.is_used = false;
    }



    /* === Public Methods === */

    abstract public Station next_station(Tram tram);

    /* === Private Methods === */




    /* === Getters === */
    /**
     * Get the index of the station on the line
     * @return The Station on the line at the given index
     */
    public Station get_station(int index) throws IndexOutOfBoundsException {
        return this.list_station.get(index);
    }
    /**
     * Get the Station with the given id
     * @param id The id of the tram
     * @return The Station on the line with the given id
     * @throws Exception If the station with this id isn't in the line
     */
    public Station get_station_by_id(int id) throws Exception {
        for (Station station : this.list_station) {
            if (station.getId() == id) {
                return station;
            }
        }
        throw new Exception("Station does not exist");
    }
    public boolean is_activated() {
        return this.is_used;
    }
    public int getId() {
        return this.id;
    }
    /**
     * Check if the station is on the line
     * @param station The station to check
     * @return  True if the station is on the line, false otherwise
     */
    public boolean is_station_in(Station station){
        return this.list_station.contains(station);
    }

    public boolean as_shape(Shape shape){
        for (Station station : this.list_station) {
            if (station.get_shape().equals(shape)) {
                return true;
            }
        }
        return false;
    }

    /* === Getter === */


    /* === Setters === */
    /**
     * Add a tram to the line
     * @param tram The tram to add
     */
    public void append(Tram tram) {
        this.list_tram.put(tram.getId(), tram);
    }
    /**
     * Add a station to the line
     * @param station The station to add
     */
    public void append(Station station) {
        this.list_station.add(station);
    }
    /**
     * Remove the tram from the line
     * @param tram The tram to remove
     */
    public void remove(Tram tram) {
        this.list_tram.remove(tram.getId());
    }
    /**
     * Remove the station from the line
     * @param station The station to remove
     */
    public void remove(Station station) {
        this.list_station.remove(station);
    }

}
