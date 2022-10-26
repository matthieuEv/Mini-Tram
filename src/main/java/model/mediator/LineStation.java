package model.mediator;

import model.data.Data;
import model.data.format.Line;
import model.data.format.Station;
import utils.Shape;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LineStation {
    private static LineStation instance = null;
    private Map<Integer, List<Station>> line_has_station;

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
     * @param station the station to add
     * @return the list of station of the line
     */
    public Station get_next_station(int line_id, Station station) {
        List<Station> stations = line_has_station.get(line_id);
        int index = stations.indexOf(station);
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
        for (Station station : line_has_station.get(line_id)) {
            if(!output.contains(station.getShape())) {
                output.add(station.getShape());
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
        for (Map.Entry<Integer, List<Station>> entry : line_has_station.entrySet()) {
            for (Station station : entry.getValue()) {
                if(station.get_id() == station_id) {
                    output.add(Data.get_lines().get(entry.getKey()));
                }
            }
        }
        return output;
    }
}
