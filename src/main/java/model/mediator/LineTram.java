package model.mediator;

import model.data.Data;
import model.data.format.Line;
import model.data.format.Tram;

import java.util.HashMap;
import java.util.Map;

public class LineTram {
    static private LineTram instance = null;
    Map<Integer, Integer> tram_on_line; // Tram_id -> Line_id

    private LineTram() {
        this.tram_on_line = new HashMap<>();
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static LineTram getInstance() {
        if(instance == null) {
            instance = new LineTram();
        }
        return instance;
    }

    /* === Setter === */
    public void set_tram_on_line(Integer tram_id, Integer line_id) {
        tram_on_line.put(tram_id, line_id);
    }

    /**
     * Get the line of the tram
     * @param tram the tram
     * @return the line of the tram
     */
    public int tram_get_line(Tram tram) {
        return tram_on_line.get(tram.get_id());
    }

    /**
     * get an id from a tram that is not used yet
     * @return an id from a tram that is not used
     */
    public int get_available_tram() {
        int output = -1;
        for(int id : Data.get_tram().keySet()){
            if(!tram_on_line.containsKey(id)){
                output = id;
                break;
            }
        }
        return output;
    }
}
