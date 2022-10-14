package model.line;

import model.Station;
import model.tram.Tram;

public class Loop_line extends Line{

    public Loop_line () {
        super();
    }
    public Loop_line (Line line) {
        super(line);
    }

    @Override
    public Station next_station(Tram tram){
        //todo
        return null;
    }
}
