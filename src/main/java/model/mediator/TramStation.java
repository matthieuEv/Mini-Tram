package model.mediator;

import model.data.format.Station;
import model.data.format.Tram;

import java.util.HashMap;
import java.util.Map;

public class TramStation {
    private static TramStation instance = null;
    private Map<Integer, Integer> tram_is_at_station;

    private TramStation() {
        this.tram_is_at_station = new HashMap<>();
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static TramStation getInstance() {
        if(instance == null) {
            instance = new TramStation();
        }
        return instance;
    }

    /* === Setter === */
    public void put_tram_at_station(int tram_id, int station_id) {
        tram_is_at_station.put(tram_id, station_id);
    }

    /* === Getter === */

    /**
     * Get the station where the tram is
     * @param tram_id the id of the tram
     * @return the station where the tram is
     */
    public int tram_is_at_station(int tram_id) {
        return tram_is_at_station.get(tram_id);
    }

    public void resetAll(){
        this.tram_is_at_station = new HashMap<>();
    }

}
