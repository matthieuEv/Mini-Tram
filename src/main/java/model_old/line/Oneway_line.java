package model_old.line;

import model_old.Station;
import model_old.tram.Tram;

public class Oneway_line extends Line{

    public Oneway_line () {
        super();
    }
    public Oneway_line (Line line) {
        super(line);
    }

    /**
     * Return the next station for the tram
     * @param tram The tram who seek is next station
     * @return The next station
     * @warning Not implemented yet !!!
     */
    @Override
    public Station next_station(Tram tram){
        //todo
        return null;
    }
}
