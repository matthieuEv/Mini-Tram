package model.data;

import model.data.format.*;
import utils.Shape;

import java.util.HashMap;
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
        Station s1 = new Station(Shape.ROUND);
        Station s2 = new Station(Shape.TRIANGLE);
        Station s3 = new Station(Shape.SQUARE);
        Station s4 = new Station(Shape.DIAMOND);
        stations.put(s1.get_id(), s1);
        stations.put(s2.get_id(), s2);
        stations.put(s3.get_id(), s3);
        stations.put(s4.get_id(), s4);
        //Init with 10 Peoples
        peoples = new HashMap<>();
        for(int i = 0; i < 10; i++){
            People p = new People(Shape.random_shape());
            peoples.put(p.get_id(), p);
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
        //Create a new people at a random station
        People person = new People(Shape.random_shape());
        Random r = new Random();
        int randomStation;
        do {
            randomStation = r.nextInt(Data.stations.size());
        }while (stations.get(randomStation).getShape().equals(person.getDestination()));
    }

    /* === Getter === */
    static public Map<Integer, Tram> get_tram() {
        return getInstance().trams;
    }
    static public Tram get_tram(int tram_id) {
        return getInstance().trams.get(tram_id);
    }
    static public Map<Integer, Station> get_stations() {
        return getInstance().stations;
    }
    static public Map<Integer, People> get_peoples() {
        return getInstance().peoples;
    }
    static public Map<Integer, Line> get_lines() {
        return getInstance().lines;
    }

}
