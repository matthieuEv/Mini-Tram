package model.mediators;

import model.line.*;
import model.Station;
import model.tram.*;
import model.people.*;

import java.util.Map;

public class Mediator {
    //Singleton
    private static boolean exist = false;

    //To contain the presenter
    //private Presenter presenter;

    //Map containing all the different objects
    private Map<Integer, Tram> list_tram;
    private Map<Integer, Station> list_station;
    private Map<Integer, Line> list_line;
    private Map<Integer, People> list_people;

    //Constructor

    /**
     * Create a new mediator
     * @throws Exception If the mediator already exist
     */
    public Mediator() throws Exception {
        if(exist) {
            throw new Exception("Mediator already exist");
        }
        exist = true;
        //todo : For now init the inventory in the Model Mediator
        Tram loc  = new Locomotive();
        list_tram.put(loc.getId(), loc);
        Line oneway = new Oneway_line();
        list_line.put(oneway.getId(), oneway);

    }
    /*public void add_presenter(Presenter presenter) {
        this.presenter = presenter;
    }*/

    /* === Interaction with the Presenter === */


    //NEW LINE
    /**
     * Handle demand from the presenter to create activate a line that the user has in inventory
     * (Happen is the user select two stations without selecting a line)
     *
     * @param station1 The first station
     * @param station2 The second station
     * @throws Exception If there is no Line or tram available
     */
    public void activate_a_new_line (Station station1, Station station2) throws Exception {
        //Get an available tram
        Tram tram = get_available_tram();
        //Get an available line
        Line line = get_available_line();
        //Activate the tram
        tram.activate(4, line);
        //Create the line
        line.activate(station1, station2, tram);
    }
    /**
     * Handle demand from the presenter to create a new line between two existing stations
     * (Happen is the user select two stations and a line)
     *
     * @param station1 The first station
     * @param station2 The second station
     * @param line The line that will be used
     * @throws Exception If there is no tram available
     */
    public void activate_a_new_line (Station station1, Station station2,Line line) throws Exception {
        //Get an available tram
        Tram tram = get_available_tram();
        //Activate the tram
        tram.activate(4, line);
        //Create the line
        line.activate(station1, station2, tram);
    }

    //INTERACTION BETWEEN STATION AND LINE
    /**
     * Handle demand from the presenter to add a station to a line
     * @param station The station to add
     * @param line The line to add the station to
     * @throws Exception If the station is already on the line
     */
    public void add_a_station(Station station, Line line) throws Exception {
        if (!line.is_station_in(station)) {
            //If the station isn't already on the line add it
            line.append(station);
        } else if(station == list_station.get(0) || station == list_station.get(list_line.size()-1)){
            //If the station is one of the end station, change your one_way into a loop
            change_line_type(line);
        } else {
            //Else throw an exception
            throw new Exception("The station is already on the line");
        }
    }

    /**
     * Handle demand from the presenter to remove a station from a line
     * @param station The station to remove
     * @param line The line to remove the station from
     * @throws Exception If the station is not on the line
     */
    public void remove_a_station(Station station, Line line) throws Exception {
        line.remove(station);
    }







    /* === Private methods === */

    /**
     * Get an unused tram from the inventory
     * @return a tram that is not used
     * @throws Exception   If there is no available tram
     */
    private Tram get_available_tram() throws Exception {
        for (Tram tram : this.list_tram.values()) {
            if (!tram.isActivated()) {
                return tram;
            }
        }
        throw new Exception("No available tram");
    }

    /**
     * Get an unused line from the inventory
     * @return a line that is not used
     * @throws Exception  If there is no available line
     */
    private Line get_available_line() throws Exception {
        for (Line line : this.list_line.values()) {
            if (!line.isActivated()) {
                return line;
            }
        }
        throw new Exception("No available line");
    }

    /**
     * Change the type of line from one_way to loop or the opposite
     * @param line The line to switch type
     */
    private void change_line_type(Line line) {
        if (line instanceof Oneway_line){
            list_line.replace(line.getId(), new Loop_line(line));
        }else{
            list_line.replace(line.getId(), new Oneway_line(line));
        }
    }

}
