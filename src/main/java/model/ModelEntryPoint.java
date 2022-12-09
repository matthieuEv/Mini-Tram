package model;

import model.compute.Irigo;
import model.compute.Layout;
import model.compute.progression.ProgressionHandler;
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
    private static Irigo irigo;
    private static Main_Presenter presenter;

    private static ProgressionHandler progressionHandler;

    private ModelEntryPoint() {
        irigo = Irigo.getInstance();
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
        ModelEntryPoint.presenter = presenter;
    }

    /* === GIVE === */

    /**
     * Give the list of the station
     * @return the list of the station formated
     */
    static public Map<Integer,Pos> SEND_get_all_station(){
        Map<Integer,Pos> list = new HashMap<>();
        for (Station station : Data.get_stations_list()){
            list.put(station.get_id(),station.get_pos());
        }
        return list;
    }

    /**
     * Give the list of the people
     * @return the list of the people formated
     */
    static public List<Integer> SEND_all_line_id(){
        return Data.get_lines().keySet().stream().toList();
    }

    /**
     * Start the game
     */
    static public void StarGame(){
        progressionHandler = new ProgressionHandler();
    }

    /**
     * Stop the game and reset the progression
     */
    static public void stopGame(){
        progressionHandler.StopGame();
        presenter.stopGame();
    }

    static public void closeGame(){
        progressionHandler.StopGame();
    }


    /**
     * Give the list of the people in a tram
     * @param tram_id the id of the tram
     * @return the list of the people formated
     */
    static public ArrayList<Shape> SEND_people_in_tram(int tram_id){
        ArrayList<Shape> list = new ArrayList<>();
        for (People people : TramPeople.getInstance().people_in_tram(tram_id)){
            list.add(people.getDestination());
        }
        return list;
    }

    /* === DEMAND === */

    /**
     * Uptate the people in a station
     * @param station_id the id of the station to update
     */
    static public void DEMAND_update_station(int station_id){
        ArrayList<Shape> list = new ArrayList<>();
        for (People people : StationPeople.getInstance().people_at_station(station_id)){
            list.add(people.getDestination());
        }
        presenter.SEND_add_people_station(station_id, list);
    }

    /**
     * Uptate the position of a tram
     * @param tram_id the id of the tram to update
     * @param station_id the id of the station where the tram is
     * @param line_id the id of the line of the tram
     * @param time the time of the travel to the next station
     */
    static public void DEMAND_update_tram(int tram_id, int station_id, int line_id , int time) {
        presenter.SEND_tram_next_step(tram_id, station_id, line_id, time);
    }

    /**
     * Add a tram to the UI
     * @param station_id the id of the station where the tram is
     * @param line_id
     */
    static public void DEMAND_create_UI_tram(int station_id, int line_id) {
        presenter.SEND_add_tram(station_id, line_id);
    }

    /* === GET === */

    /**
     * Activate a line of tram from input of the user
     * @param line_id the id of the line to activate
     * @param station1_id the id of the first station on the line
     * @param station2_id the id of the second station on the line
     */
    static public void GET_activate_line(int line_id, int station1_id, int station2_id) {
        irigo.activate_line(line_id, station1_id, station2_id);
    }

    /**
     * Add a station to a line input user
     * @param line_id the id of the line to add the station
     * @param station1_id the id of the origin station
     * @param station2_id the id of the destination station
     */
    static public void GET_add_station(int line_id, int station1_id, int station2_id) {
        irigo.add_station_to_line(line_id, station1_id, station2_id);
    }

    public static void ADD_station(Station station) {
        presenter.DEMAND_add_station(station.get_id(), station.get_pos());
    }

    public Shape SEND_get_shape_station(int id) {
        return Data.get_stations_list().get(id).getShape();
    }

    public Layout SEND_get_layout() {
        return Data.get_map();
    }

    public int get_score() {
        return Data.get_score();
    }
}
