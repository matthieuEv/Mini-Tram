package model.compute.progression;

import model.ModelEntryPoint;
import model.compute.Layout;
import model.data.Data;
import model.data.format.Line;
import model.data.format.People;
import model.data.format.Station;
import model.data.format.Tram;
import model.mediator.StationPeople;
import utils.Pos;
import utils.Shape;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProgressionHandler extends Thread {

    private PeopleGenerator peopleGenerator;
    private StationGenerator stationGenerator;

    /**
     * Constructor
     * It creates the base stations and people for the game to run
     */
    public ProgressionHandler() {
        //Create the variable to old the created data set
        Map<Integer, Tram> trams;
        Map<Integer, Station> stations;
        Map<Integer, Line> lines;

        //Init with 4 trams and 4 lines
        trams = new HashMap<>();
        lines = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Tram t = new Tram();
            Line l = new Line();
            trams.put(t.get_id(), t);
            lines.put(l.get_id(), l);
        }
        //Set in data
        Data.set_tram(trams);
        Data.set_line(lines);

        //Init with 4 stations
        int number_of_station_start = 4;
        //Max pos for a station will be x : 45 and y : 35
        stations = new HashMap<>();
        Station s1 = new Station(Shape.ROUND, new Pos(2,5));
        Station s2 = new Station(Shape.TRIANGLE,new Pos(15,8));
        Station s3 = new Station(Shape.SQUARE,new Pos(6,13));
        Station s4 = new Station(Shape.DIAMOND,new Pos(8,5));
        stations.put(s1.get_id(), s1);
        stations.put(s2.get_id(), s2);
        stations.put(s3.get_id(), s3);
        stations.put(s4.get_id(), s4);
        //Set in data
        Data.set_station(stations);

        //Init with 3 Peoples places at random stations
        Data.set_people(new HashMap<>());

        Layout map = new Layout();
        Data.set_map(map);

        //Start both thread
        peopleGenerator = new PeopleGenerator();
        stationGenerator = new StationGenerator(number_of_station_start);
        peopleGenerator.start();
        stationGenerator.start();
        this.start();
    }

    @Override
    public void run(){
        boolean game = true;
        while(game){
            game = endGame();
        }
    }

    /**
     * Check if there's a station that overloaded
     * If yes, the game is over
     */
    private boolean endGame(){
        //Check if a station as more than 8 people
        if (StationPeople.getInstance().get_station_with_too_much_people().size()>=1){
            ModelEntryPoint.stopGame();
            return false;
        }
        return true;
    }
    /**
     * Stop the game by stopping each tram and progression thread, then resting all the data
     */
    public void StopGame() {
        Data.get_tram().forEach((id, tram) -> {
            tram.set_active(false);
            System.out.println("Tram " + id + " is now inactive");
            try {
                tram.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("All trams are now inactive");
        //Stop the all threads
        peopleGenerator.kill();
        try {
            peopleGenerator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stationGenerator.kill();
        try {
            stationGenerator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All thread are now stopped");
        Data.empty_data();
    }

}
