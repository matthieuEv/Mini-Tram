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
     * @return the list of station of the line
     */
    public int get_next_station(int line_id, int station_id) {
        List<Integer> stations = line_has_station.get(line_id);
        int index = stations.indexOf(Data.get_stations(station_id));
        if(index == stations.size() - 1) {
            return stations.get(0);
        }
        return stations.get(index + 1);
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

    public void line_append_station(int line_id, int station) {
        if(!line_has_station.containsKey(line_id)) {
            line_has_station.put(line_id, new ArrayList<Integer>());
        }
        line_has_station.get(line_id).add(station);
    }
}
