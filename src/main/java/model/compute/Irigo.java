package model.compute;

import model.ModelEntryPoint;
import model.compute.progression.ProgressionHandler;
import model.data.Data;
import model.data.format.*;
import model.mediator.*;
import utils.Pos;

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
     * Activate a line
     * @param line_id the id of the line
     * @param station1_id the id of the first station
     * @param station2_id the id of the second station
     */
    public void activate_line(int line_id, int station1_id, int station2_id) {
        LineStation.getInstance().line_append_station(line_id, station1_id);
        LineStation.getInstance().line_append_station(line_id, station2_id);
        int tram_id = LineTram.getInstance().get_available_tram();
        LineTram.getInstance().set_tram_on_line(tram_id, line_id);
        TramStation.getInstance().put_tram_at_station(tram_id, station1_id);
        Data.get_tram(tram_id).start();
        Data.get_tram(tram_id).set_active(true);
        ModelEntryPoint.getInstance().DEMAND_create_UI_tram(station1_id, line_id);
    }

    /**
     * Add a station to a Line
     * @param line_id the id of the line to add the station
     * @param station1_id the id of the station already in the line
     * @param station2_id the id of the station to add
     */
    public void add_station_to_line(int line_id, int station1_id, int station2_id) {
        LineStation.getInstance().line_append_station(line_id, station1_id, station2_id);
    }


    /**
     * Activated each time a tram arrive at a station
     * lock the data them do all the actions and unlock the data
     * @param tram the tram that activate trigger the event
     * @warning This portion of code is written here but the Only place your allow to execute it is from a tram routine
     */
    public static int trigger_tram(Tram tram) {
        synchronized (Data.getInstance()) {
            //ask the line of the tram :
            Line line = Data.get_line(LineTram.getInstance().tram_get_line(tram));
            //ask the station where the tram is :
            int station = TramStation.getInstance().tram_is_at_station(tram.get_id());
            //Ask the list of people at the station
            List<People> people_at_station = StationPeople.getInstance().people_at_station(station);
            //ask the list of people in the tram
            List<People> people_in_tram = TramPeople.getInstance().people_in_tram(tram.get_id());

            //display the tram
            //System.out.println("Tram " + tram.get_id() + " is at station " + station + " shape : "+ Data.get_stations(station).getShape()+"\nwith " + people_in_tram.size() + " people in tram and " + people_at_station.size() + " people in the station");

            if (people_in_tram.size() != 0){
                //Compute the list of person to get out the tram and change the state of the people
                List<People> people_out_of_tram = need_to_get_out(people_in_tram, Data.get_stations(station), line,tram.get_id());
                TramPeople.getInstance().people_out_of_tram(people_out_of_tram, tram.get_id());
                //the algo to compute if people are at the good station is in 'people_get_in_station()' function
                StationPeople.getInstance().people_get_in_station(people_out_of_tram, station);
                //System.out.println(people_out_of_tram.size()+" got out of the tram => station");
            }

            if (people_at_station.size() != 0){
                //Compute the list of person to get in the tram and change the state of the people
                List<People> people_get_in_tram = need_to_get_in(people_at_station, Data.get_stations(station), line, tram.get_id());
                StationPeople.getInstance().people_out_of_station(people_get_in_tram, station);
                TramPeople.getInstance().people_get_in_tram(people_get_in_tram, tram.get_id());
                //System.out.println(people_get_in_tram.size()+" got out of the station => tram");
            }
            //System.out.println("----------------------------------------");

            //ask the next station
            int next_station = LineStation.getInstance().get_next_station(line.get_id(), station,tram.get_direction());
            if(next_station == -1) {
                //if the next station is -1 it means that the tram is at the end of the line
                //so we change the direction
                tram.change_direction();
                next_station = LineStation.getInstance().get_next_station(line.get_id(), station,tram.get_direction());
            }

            //Get pos from the station
            Pos oldPos = Data.get_stations(station).get_pos();
            Pos newPos = Data.get_stations(next_station).get_pos();

            int time = calcTime(oldPos, newPos);

            //Put the tram at the next station
            TramStation.getInstance().put_tram_at_station(tram.get_id(), next_station);

            //Ask the view to update the UI
            ModelEntryPoint.getInstance().DEMAND_update_tram(tram.get_id(), next_station, line.get_id(), time);
            ModelEntryPoint.getInstance().DEMAND_update_station(station);
            //Get back to sleep until the next trigger

            return time;

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
                output.add(person);
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
     * Compute the time needed to go from one station to another
     * @param newStation the station where the tram is going
     * @param oldStation the station where the tram is
     * @return the time needed to go from one station to another in ms
     */
    private static int calcTime(Pos newStation, Pos oldStation){
        return (int) Math.sqrt(Math.pow(newStation.x - oldStation.x, 2) + Math.pow(newStation.y - oldStation.y, 2)) * 100;
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

}

