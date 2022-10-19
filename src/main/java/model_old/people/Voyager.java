package model_old.people;

import model_old.tram.Tram;
import utils.Shape;

public class Voyager extends People{
    private Tram vehicle;
    private boolean prov_line;

    public Voyager(Shape destination, Tram vehicle){
        super(destination);
        this.vehicle = vehicle;
    }

    public void set_prov_line(boolean prov_line){
        this.prov_line = prov_line;
    }

    public boolean isProv_line() {
        return prov_line;
    }
}
