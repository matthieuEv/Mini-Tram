package model.mediator;

import model.data.Data;
import model.data.format.People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TramPeople {
    private static TramPeople instance = null;
    private Map<Integer, List<People>> tram_carry_people;

    private TramPeople() {
        this.tram_carry_people = new HashMap<>();
        for (int i = 0; i < Data.get_tram().size(); i++) {
            this.tram_carry_people.put(i, new ArrayList<>());
        }
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static TramPeople getInstance() {
        if(instance == null) {
            instance = new TramPeople();
        }
        return instance;
    }

    /* === Setter === */
    public void people_get_in_tram(People people, int tram_id) {
        Data.get_peoples().put(people.get_id(), people);
        this.tram_carry_people.get(tram_id).add(people);
    }
    public void people_get_in_tram(List<People> people, int tram_id) {
        for (People p : people) {
            Data.get_peoples().put(p.get_id(), p);
            this.tram_carry_people.get(tram_id).add(p);
        }
    }
    public void people_out_of_tram(People people, int tram_id) {
        Data.get_peoples().remove(people.get_id());
        this.tram_carry_people.get(tram_id).remove(people);
    }
    public void people_out_of_tram(List<People> people, int tram_id) {
        for (People p : people) {
            Data.get_peoples().remove(p.get_id());
            this.tram_carry_people.get(tram_id).remove(p);
        }
    }

    /* === Getter === */

    /**
     * Get the list of people carried by the tram
     * @param tram_id the id of the tram
     * @return the list of people carried by the tram
     */
    public List<People> people_in_tram(int tram_id) {
        return tram_carry_people.get(tram_id);
    }

    /**
     * Get the number of free place in the tram
     * @param tram_id the id of the tram
     * @return the number of you can add in the tram
     */
    public int free_place_in_tram(int tram_id) {
        return Data.get_tram(tram_id).getPlace() - tram_carry_people.get(tram_id).size();
    }

    public void resetAll(){
        this.tram_carry_people = new HashMap<>();
        for (int i = 0; i < Data.get_tram().size(); i++) {
            this.tram_carry_people.put(i, new ArrayList<>());
        }
    }
}
