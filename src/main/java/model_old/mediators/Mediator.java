package model_old.mediators;

import model_old.line.*;
import model_old.Station;
import model_old.tram.*;
import model_old.people.*;

import java.util.HashMap;
import java.util.Map;

public class Mediator {
    //Singleton
    private static Mediator instance;

    //To contain the presenter
    //private Presenter presenter;

    //Map containing all the different objects
    private Map<Integer, Tram> list_tram = new HashMap<>();
    private Map<Integer, Station> list_station = new HashMap<>();
    private Map<Integer, Line> list_line = new HashMap<>();
    private Map<Integer, People> list_people = new HashMap<>();

    //Constructor
    private Mediator() {
        //Create the presenter
        //this.presenter = new Presenter();
    }
    public static Mediator getInstance() {
        if(instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    /* === Interaction with the Presenter === */

    //NEW LINE
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
        //Activate the line
        line.activate(station1, station2, tram);
        //Activate the tram
        tram.activate(4, line);
    }
    /**
     * Handle demand from the presenter to create activate a line that the user has in inventory
     * (Happen is the user select two stations without selecting a line)
     *
     * @param station1 The first station
     * @param station2 The second station
     * @throws Exception If there is no Line or tram available
     */
    public void activate_a_new_line (Station station1, Station station2) throws Exception {
        //Get an available line
        Line line = get_available_line();
        this.activate_a_new_line(station1,station2,line);
    }

    //INTERACTION BETWEEN STATION AND LINE
    /**
     * Handle demand from the presenter to add a station to a line
     * @param station The station to add
     * @param line The line to add the station to
     * @throws Exception If the station is already on the line
     * @warning The station cannot be loop yet !!!
     */
    public void add_a_station(Station station, Line line) throws Exception {
        if (station == null || line == null) {
            throw new Exception((station == null) ? "Station is null" : "Line is null");
        }else if(!line.is_activated()){
            throw new Exception("The line is not activated");
        }else if (!line.is_station_in(station)) {
            //If the station isn't already on the line add it
            line.append(station);
        }else{
            change_line_type(line,station);
        }
    }

    /**
     * Handle demand from the presenter to remove a station from a line
     * @param station The station to remove
     * @param line The line to remove the station from
     * @throws Exception If the station is not on the line
     * @warning Not implemented yet !!!
     */
    public void remove_a_station(Station station, Line line) throws Exception {
        line.remove(station);
    }

    //Interaction between the Peoples and the Station

    public void tram_arrive_at_station(Station station, Tram tram) {
        //Check the people on the tram
        for (Voyager p : tram.get_list_people().values()) {
            if (p.get_destination().equals(station.get_shape())) {
                //If the people is at the destination he want to
                //Remove him from the tram
                //todo : remove from tram
            }else if (p.isProv_line()){
                // If the person is on a prov_line
                //check if is goal is on one line of the station
                for (Line l : station.get_list_line().values()) {
                    if (l.as_shape(p.get_destination())) {
                        //if yes remove him from the tram and the line
                        //todo : remove from tram
                    }
                }
            }
        }
        //Check the people on the station
        for (Transit p : station.get_list_people().values()) {

        }
    }









    /* === Private methods === */

    /**
     * Get an unused tram from the inventory
     * @return a tram that is not used
     * @throws Exception   If there is no available tram
     */
    private Tram get_available_tram() throws Exception {
        for (Tram tram : this.list_tram.values()) {
            if (!tram.is_activated()) {
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
            if (!line.is_activated()) {
                return line;
            }
        }
        throw new Exception("No available line");
    }

    /**
     * Change the type of line from one_way to loop or the opposite if the station given is at one end
     * @param line The line to switch type
     * @param station The station to check
     * @warning Not possible yet !!!!
     */
    private void change_line_type(Line line, Station station) throws Exception {
        if(station == list_station.get(0) || station == list_station.get(list_line.size()-1)){
            //If the station is one of the end station, change your one_way into a loop
            try {
                if (line instanceof Oneway_line){
                    list_line.replace(line.getId(), new Loop_line(line));
                }else{
                    list_line.replace(line.getId(), new Oneway_line(line));
                }
            } catch (Exception e) {
                throw new Exception("Can't change the line to loop : "+e.getMessage());
            }
        } else {
            //Else throw an exception
            throw new Exception("The station is already on the line");
        }
    }

    /* === Setter === */

    public void add_inventory(Locomotive loc) {
        this.list_tram.put(loc.getId(), loc);
    }
    public void add_inventory(Wagon wag) {
        this.list_tram.put(wag.getId(), wag);
    }
    public void add_inventory(Station station) {
        this.list_station.put(station.getId(), station);
    }
    public void add_inventory(Line line) {
        this.list_line.put(line.getId(), line);
    }


}
