package model_old.people;

import model_old.Station;
import utils.Shape;

public class Transit extends People{
    private Station current_station;

    public Transit(Shape destination, Station current_station){
        super(destination);
        this.current_station = current_station;
    }


}
