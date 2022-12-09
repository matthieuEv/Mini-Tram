package model.data;

import model.ModelEntryPoint;
import model.compute.Layout;
import model.data.format.*;
import model.mediator.StationPeople;
import utils.Pos;
import utils.Shape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Data {
    private static Data instance = null;

    private static Map<Integer, Tram> trams;
    private static Map<Integer, Station> stations;
    private static Map<Integer, People> peoples;
    private static Map<Integer, Line> lines;

    private static Layout map;

    private static int score;

    //The constructor is the builder
    private Data() {
        this.score = 0;
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     * @warning this if call for the first time is the builder
     */
    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }
        return instance;
    }
    /* === Setter === */
    static public void set_Tram_On(int tram_id) {
        Data.trams.get(tram_id).set_active(true);
    }

    /**
     * Empty all the data at the end of a game
     */
    static public void empty_data() {
        trams = new HashMap<>();
        stations = new HashMap<>();
        peoples = new HashMap<>();
        lines = new HashMap<>();
    }

    /**
     * Add trams to the data
     * @param trams the Map of tram to add
     */
    static public void set_tram(Map<Integer, Tram> trams) {
        Data.trams = trams;
    }

    /**
     * Add stations to the data
     * @param stations the Map of station to add
     */
    static public void set_station(Map<Integer, Station> stations) {
        Data.stations = stations;
    }
    static public void set_station(Station station) {
        Data.stations.put(station.get_id(),station);
    }

    /**
     * Add peoples to the data
     * @param peoples the Map of people to add
     */
    static public void set_people(Map<Integer, People> peoples) {
        Data.peoples = peoples;
    }
    /**
     * Add a people to the data
     * @param Po the people to add
     */
    static public void set_people(People Po) {
        Data.peoples.put(Po.get_id(),Po);
    }

    /**
     * Add lines to the data
     * @param lines the Map of line to add
     */
    static public void set_line(Map<Integer, Line> lines) {
        Data.lines = lines;
    }


    /**
     * make someone disapear and create a new person
     * @param people the people to make disapear
     */
    static public void people_disappear(People people) {
        Data.peoples.remove(people.get_id());
        score += 1;
    }

    /**
     * Make a people apear with a ramdom shape at a random place
     */
    static public void peopleAppear() {
        //Create a new people with a random shape
        People person = new People(Shape.random_shape());
        Random r = new Random();
        int randomStation;
        //select a station to put them randomly if the station is the same shape as them shearch another one
        do {
            randomStation = r.nextInt(Data.stations.size());
        }while (stations.get(randomStation).getShape().equals(person.getDestination()));

        System.out.println("People added to station " + randomStation + " with destination " + person.getDestination());

        //put the person in the station
        Data.peoples.put(person.get_id(), person);
        StationPeople.getInstance().people_get_in_station(person, randomStation);
        ModelEntryPoint.getInstance().DEMAND_update_station(randomStation);
    }



    /* === Getter === */
    static public Map<Integer, Line> get_line() {
        return lines;
    }
    static public Line get_line(int line_id) {
        return lines.get(line_id);
    }
    static public Map<Integer, Tram> get_tram() {
        return trams;
    }
    static public Tram get_tram(int tram_id) {
        return trams.get(tram_id);
    }
    static public Map<Integer, Station> get_stations() {
        return stations;
    }
    static public Station get_stations(int station_id) {
        return stations.get(station_id);
    }
    static public List<Station> get_stations_list() {
        return stations.values().stream().toList();
    }
    static public Map<Integer, People> get_peoples() {
        return peoples;
    }
    static public Map<Integer, Line> get_lines() {
        return lines;
    }
    static public int get_score() {
        return score;
    }

    public static void set_map(Layout map) {
        Data.map = map;
    }

    public static Layout get_map() {
        return map;
    }
}
