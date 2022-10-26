package model.compute;

import model.data.Data;
import model.data.format.*;
import model.mediator.*;

import java.util.ArrayList;
import java.util.List;

public class Irigo {
    private static Irigo instance = null;
    private Data db;

    private Irigo() {
        //The database is also the builder !
        this.db = Data.getInstance();
    }
    /**
     * Get the instance of the singleton
     * @return the instance of the class
     * @warning this if call for the first time activate the builder
     */
    public static Irigo getInstance() {
        if(instance == null) {
            instance = new Irigo();
        }
        return instance;
    }

    /* === Compute modules === */

    /**
     * Activated each time a tram arrive at a station
     * lock the data them do all the actions and unlock the data
     * @param tram the tram that activate trigger the event
     * @warning This portion of code is written here but the Only place your allow to execute it is from a tram routine
     */
    public static void trigger_tram(Tram tram) {
        synchronized (Data.getInstance()) {
            //ask the line of the tram :
            Line line = LineTram.getInstance().tram_get_line(tram);
            //ask the station where the tram is :
            Station station = TramStation.getInstance().tram_is_at_station(tram.get_id());
            //Ask the list of people at the station
            List<People> people_at_station = StationPeople.getInstance().people_at_station(station.get_id());
            //ask the list of people in the tram
            List<People> people_in_tram = TramPeople.getInstance().people_in_tram(tram.get_id());

            //Compute the list of person to get out the tram and change the state of the people
            List<People> people_out_of_tram = need_to_get_out(people_in_tram, station, line,tram.get_id());
            TramPeople.getInstance().people_out_of_tram(people_out_of_tram, tram.get_id());
            StationPeople.getInstance().people_get_in_station(people_out_of_tram, station.get_id());

            //Compute the list of person to get in the tram and change the state of the people
            List<People> people_get_in_tram = need_to_get_in(people_at_station, station, line, tram.get_id());
            StationPeople.getInstance().people_out_of_station(people_get_in_tram, station.get_id());
            TramPeople.getInstance().people_get_in_tram(people_get_in_tram, tram.get_id());

            //ask the next station
            Station next_station = LineStation.getInstance().get_next_station(line.get_id(), station);
            //Put the tram at the next station
            TramStation.getInstance().put_tram_at_station(tram, next_station);
            //Get back to sleep until the next trigger
        }
    }

    /**
     * Compute the list of people that need to get out of the tram
     * @param lst_people the list of people in the tram
     * @param station   the station where the tram is
     * @param line    the line of the tram
     * @param tram_id   the id of the tram
     * @return  the list of people that need to get out of the tram
     */
    private static List<People> need_to_get_out(List<People> lst_people, Station station, Line line, int tram_id){
        List<People> output = new ArrayList<>();
        //See if they need to get out
        for (People person : lst_people){
            if(person.getDestination().equals(station.getShape())){ //if the person is at his destination
                people_disappear(person, tram_id , station.get_id());
            }else if (!LineStation.getInstance().line_has_shape(line.get_id(),person.getDestination())){ //else if the person is don't have their destination on this line
                List<Line> lines_passing_here = LineStation.getInstance().line_passing_at_station(station.get_id());
                for (Line l : lines_passing_here){ //for each line passing at station
                    if(LineStation.getInstance().line_has_shape(l.get_id(),person.getDestination())){
                        output.add(person);
                    }
                }
            }// Else it means will be able to get out at a station on this line
        }
        return output;
    }

    /**
     * Compute the list of people that need to get in the tram
     * @param lst_people the list of people at the station
     * @param station  the station where the tram is
     * @param line  the line of the tram
     * @param tram_id the id of the tram
     * @return the list of people that need to get in the tram
     */
    private static List<People> need_to_get_in(List<People> lst_people, Station station, Line line, int tram_id){
        List<People> output = new ArrayList<>();
        //See if they need to get in
        for (People person : lst_people){
            if(LineStation.getInstance().line_has_shape(line.get_id(),person.getDestination())){ //if the destination of the person is on the line of the tram
                output.add(person);
            }else{
                List<Line> lines_passing_here = LineStation.getInstance().line_passing_at_station(station.get_id());
                boolean waiting = false;
                for (Line l : lines_passing_here){ //for each line passing at station
                    if(LineStation.getInstance().line_has_shape(l.get_id(),person.getDestination())){
                        waiting = true;
                    }
                }
                if (!waiting){
                    output.add(person);
                }
            }
        }
        return output;
    }

    /**
     * Remove someone when their at the good station
     * @param person    the person to remove
     */
    private static void people_disappear(People person, int tram_id, int station_id){
        TramPeople.getInstance().people_out_of_tram(person, tram_id);
        StationPeople.getInstance().people_out_of_station(person, station_id);
        Data.people_disappear(person);
    }

        private void launch_tram(Tram tram){
        tram.start();
    }

}
