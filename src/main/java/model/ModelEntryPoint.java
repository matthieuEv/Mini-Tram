package model;

import model.compute.Irigo;
import model.data.Data;
import model.data.format.People;
import model.data.format.Station;
import model.mediator.StationPeople;
import model.mediator.TramPeople;
import presenter.Main_Presenter;
import utils.Pos;
import utils.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelEntryPoint {
    private static ModelEntryPoint instance = null;
    private Irigo irigo;
    private Main_Presenter presenter;

    private ModelEntryPoint() {
        this.irigo = Irigo.getInstance();
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     * @warning this if call for the first time activate the builder
     */
    public static ModelEntryPoint getInstance() {
        if(instance == null) {
            instance = new ModelEntryPoint();
        }
        return instance;
    }
    public void append_presenter(Main_Presenter presenter) {
        this.presenter = presenter;
    }

    /* === GIVE === */
    public Map<Integer,Pos> SEND_get_all_station(){
        Map<Integer,Pos> list = new HashMap<>();
        for (Station station : Data.get_stations_list()){
            list.put(station.get_id(),station.get_pos());
        }
        return list;
    }
    public List<Integer> SEND_all_line_id(){
        return Data.get_lines().keySet().stream().toList();
    }



    public ArrayList<Shape> SEND_people_in_tram(int tram_id){
        ArrayList<Shape> list = new ArrayList<>();
        for (People people : TramPeople.getInstance().people_in_tram(tram_id)){
            list.add(people.getDestination());
        }
        return list;
    }

    /* === DEMAND === */
    public void DEMAND_update_station(int station_id){
        ArrayList<Shape> list = new ArrayList<>();
        for (People people : StationPeople.getInstance().people_at_station(station_id)){
            list.add(people.getDestination());
        }
        presenter.SEND_add_people_station(station_id, list);
    }
    public void DEMAND_update_tram(int tram_id, int station_id, int line_id) {
        presenter.SEND_tram_next_step(tram_id, station_id, line_id);
    }
    public void DEMAND_create_UI_tram(int station_id, int line_id) {
        presenter.SEND_add_tram(station_id, line_id);
    }

    /* === GET === */
    public void GET_activate_line(int line_id, int station1_id, int station2_id) {
        irigo.activate_line(line_id, station1_id, station2_id);
    }
    public void GET_add_station(int line_id, int station1_id, int station2_id) {
        irigo.add_station_to_line(line_id, station1_id, station2_id);
    }






}
