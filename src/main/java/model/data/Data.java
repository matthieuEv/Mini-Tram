package model.data;

import model.ModelEntryPoint;
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

    //The constructor is the builder
    private Data() {
        //Init with 4 trams and 4 lines
        trams = new HashMap<>();
        lines = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Tram t = new Tram();
            Line l = new Line();
            trams.put(t.get_id(), t);
            lines.put(l.get_id(), l);
        }
        //Init with 4 stations
        stations = new HashMap<>();
        Station s1 = new Station(Shape.ROUND, new Pos(2,5));
        Station s2 = new Station(Shape.TRIANGLE,new Pos(8,2));
        Station s3 = new Station(Shape.SQUARE,new Pos(12,8));
        Station s4 = new Station(Shape.DIAMOND,new Pos(9,11));
        Station s5 = new Station(Shape.ROUND,new Pos(15,5));
        Station s6 = new Station(Shape.TRIANGLE,new Pos(2,11));
        Station s7 = new Station(Shape.SQUARE,new Pos(11,2));
        Station s8 = new Station(Shape.DIAMOND,new Pos(10,20));
        stations.put(s1.get_id(), s1);
        stations.put(s2.get_id(), s2);
        stations.put(s3.get_id(), s3);
        stations.put(s4.get_id(), s4);
        stations.put(s5.get_id(), s5);
        stations.put(s6.get_id(), s6);
        stations.put(s7.get_id(), s7);
        stations.put(s8.get_id(), s8);
        //Init with 10 Peoples
        peoples = new HashMap<>();
        for(int i = 0; i < 10; i++){
            People p = new People(Shape.random_shape());
            peoples.put(p.get_id(), p);
            int randomStation = new Random().nextInt(stations.size());
            StationPeople.getInstance().people_get_in_station(p, randomStation);
        }
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
     * make someone disapear and create a new person
     * @param people the people to make disapear
     */
    static public void people_disappear(People people) {
        Data.peoples.remove(people.get_id());
        //Create a new people with a random shape
        People person = new People(Shape.random_shape());
        Random r = new Random();
        int randomStation;
        //select a station to put them randomly if the station is the same shape as them shearch another one
        do {
            randomStation = r.nextInt(Data.stations.size()-1);
        }while (stations.get(randomStation).getShape().equals(person.getDestination()));

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

}
