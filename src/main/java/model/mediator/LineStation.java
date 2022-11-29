package model.mediator;

import model.data.Data;
import model.data.format.Line;
import model.data.format.Station;
import utils.Shape;

import java.util.*;

public class LineStation {
    private static LineStation instance = null;
    private Map<Integer, List<Integer>> line_has_station;

    private LineStation() {
        this.line_has_station = new HashMap<>();
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     */
    public static LineStation getInstance() {
        if(instance == null) {
            instance = new LineStation();
        }
        return instance;
    }

    /* === Getter === */

    /**
     * Get the list of station of a line
     * @param line_id the id of the line
     * @param station_id the station to add
     * @return the list of station of the line or -1 if you need to change direction
     */
    public int get_next_station(int line_id, int station_id, boolean direction) {
        int index = line_has_station.get(line_id).indexOf(station_id);
        //check if the station is the last of the line
        if (!direction) //Sens normal
        {
            if (index == line_has_station.get(line_id).size() - 1) {
                return -1;
            }else {
                return line_has_station.get(line_id).get(index + 1);
            }
        }
        else //Sens inverse
        {
            if (index == 0) {
                return -1;
            }else {
                return line_has_station.get(line_id).get(index - 1);
            }
        }

    }

    /**
     * Get the list of station of a line
     * @param line_id the id of the line
     * @return the list of station of the line
     */
    public List<Shape> get_shape_on_line(int line_id) {
        List<Shape> output = new LinkedList<>();
        for (int station_id : line_has_station.get(line_id)) {
            if(!output.contains(Data.get_stations(station_id).getShape())) {
                output.add(Data.get_stations(station_id).getShape());
            }
        }
        return output;
    }

    /**
     * Get if the line as a station of this shape
     * @param line_id the id of the line
     * @param shape the shape to check
     * @return True if the line has a station of this shape
     */
    public boolean line_has_shape(int line_id,Shape shape) {
        return (get_shape_on_line(line_id).contains(shape));
    }

    public boolean line_has_station(int line_id, int station_id) {
        return (line_has_station.get(line_id).contains(station_id));
    }

    /**
     * Get the list of line passing at a given station
     * @param station_id the id of the station
     * @return the list of line passing at the station
     */
    public List<Line> line_passing_at_station (int station_id){
        List<Line> output = new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> entry : line_has_station.entrySet()) {
            for (int station_id_l : entry.getValue()) {
                if(Data.get_stations(station_id_l).get_id() == station_id) {
                    output.add(Data.get_lines().get(entry.getKey()));
                }
            }
        }
        return output;
    }
    public List<Station> stations_on_line(int line_id){
        List<Station> output = new LinkedList<>();
        try {
            for (int station_id : line_has_station.get(line_id)) {
                output.add(Data.get_stations(station_id));
            }
        }catch (Exception e){

        }
        return output;
    }

    public void line_append_station(int line_id, int station) {
        if(!line_has_station.containsKey(line_id)) {
            line_has_station.put(line_id, new ArrayList<Integer>());
        }
        line_has_station.get(line_id).add(station);
    }
    public void line_append_station(int line_id, int stationFrom ,int stationTo) {
        int indexFrom = get_station_index(line_id, stationFrom);
        //if index is the last station
        if (indexFrom == line_has_station.get(line_id).size() - 1) {
            this.line_has_station.get(line_id).add(stationTo);
        } else { // if index is the first station
            this.line_has_station.get(line_id).add(0,stationTo);
        }
    }


    private int get_station_index(int line_id, int station_id) {
        int index = -1;
        for (Integer sta : this.line_has_station.get(line_id)){
            if (sta == station_id){
                index = this.line_has_station.get(line_id).indexOf(sta);
                return index;
            }
        }
        return index;
    }
}
