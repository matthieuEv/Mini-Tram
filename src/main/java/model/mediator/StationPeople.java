package model.mediator;

import model.data.Data;
import model.data.format.People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationPeople {
    private static StationPeople instance = null;
    private Map<Integer, List<People>> station_waiting_people;

    private StationPeople() {
        this.station_waiting_people = new HashMap<>();
        for (int i = 0; i < Data.get_stations_list().size(); i++) {
            this.add_station(i);
        }
    }



    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static StationPeople getInstance() {
        if(instance == null) {
            instance = new StationPeople();
        }
        return instance;
    }

    /* === Setter === */

    /**
     * Add the people into the station
     * @param people the people to add
     * @param station_id the id of the station
     */
    public void people_get_in_station(People people, int station_id) {
        Data.get_peoples().put(people.get_id(), people);
        this.station_waiting_people.get(station_id).add(people);
    }

    /**
     * Add the people into the station
     * @param people the list of people to add
     * @param station_id the id of the station
     */
    public void people_get_in_station(List<People> people, int station_id) {
        for (int i = 0 ; i < people.size() ; i++) {
            if (people.get(i).getDestination().equals(Data.get_stations(station_id).getShape())){
                Data.people_disappear(people.get(i));
            }else {
                Data.get_peoples().put(people.get(i).get_id(), people.get(i));
                this.station_waiting_people.get(station_id).add(people.get(i));
            }
        }
    }

    /**
     * Check each station and return a list of station with more than 8 people in
     */
    public List<Integer> get_station_with_too_much_people() {
        List<Integer> station_with_too_much_people = new ArrayList<>();
        for (int i = 0 ; i < Data.get_stations_list().size() ; i++) {
            if (this.station_waiting_people.get(i).size() > 7) {
                station_with_too_much_people.add(i);
            }
        }
        return station_with_too_much_people;
    }

    /**
     * Remove a people from the station
     * @param people the people to remove
     * @param station_id the id of the station
     */
    public void people_out_of_station(People people, int station_id) {
        Data.get_peoples().remove(people.get_id());
        this.station_waiting_people.get(station_id).remove(people);
    }
    /**
     * remove a list of people from the station
     * @param people the list of people to remove
     * @param station_id the id of the station
     */
    public void people_out_of_station(List<People> people, int station_id) {
        for (People p : people) {
            Data.get_peoples().remove(p.get_id());
            this.station_waiting_people.get(station_id).remove(p);
        }
    }

    /**
     * Add a station to the list
     * @param station_id the id of the station to add
     */
    public void add_station(int station_id) {
        this.station_waiting_people.put(station_id, new ArrayList<>());
    }

    /* === Getter === */

    /**
     * Get the list of people waiting at the station
     * @param station_id the id of the station
     * @return the list of people waiting at the station
     */
    public List<People> people_at_station(int station_id) {
        return station_waiting_people.get(station_id);
    }

    public void resetAll(){
        this.station_waiting_people = new HashMap<>();

        for (int i = 0; i < Data.get_stations_list().size(); i++) {
            this.add_station(i);
        }
    }
}
