package model.mediator;

import model.data.format.Line;
import model.data.format.Tram;

import java.util.HashMap;
import java.util.Map;

public class LineTram {
    static private LineTram instance = null;
    Map<Tram, Line> tram_on_line;

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

    /**
     * Get the line of the tram
     * @param tram the tram
     * @return the line of the tram
     */
    public Line tram_get_line(Tram tram) {
        return tram_on_line.get(tram);
    }
}
