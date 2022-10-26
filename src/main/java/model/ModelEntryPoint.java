package model;

import model.compute.Irigo;
import model.data.Data;
import model.data.format.Station;
import presenter.Main_Presenter;
import utils.Pos;

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



}
