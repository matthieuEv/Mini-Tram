package model_old.line;

import model_old.Station;
import model_old.tram.Tram;

public class Loop_line extends Line{

    public Loop_line () {
        super();
    }
    public Loop_line (Line line) {
        super(line);
    }

    /**
     * Return the next station for the tram
     * @param tram The tram who seek is next station
     * @return The next station
     * @warning Not implemented yet !!!
     */@Override
    public Station next_station(Tram tram){
        //todo
        return null;
    }
}
