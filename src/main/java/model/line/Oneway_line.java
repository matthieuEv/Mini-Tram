package model.line;

import model.Station;
import model.tram.Tram;

public class Oneway_line extends Line{

    public Oneway_line () {
        super();
    }
    public Oneway_line (Line line) {
        super(line);
    }

    @Override
    public Station next_station(Tram tram){
        //todo
        return null;
    }
}
