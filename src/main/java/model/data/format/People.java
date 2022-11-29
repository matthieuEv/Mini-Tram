package model.data.format;

import utils.Shape;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class People {
    private static int id_counter = 0;
    private int id;

    private Shape destination;
    private List<Integer> path;

    public People(Shape shape){
        this.path = new LinkedList<>();
        this.id = id_counter;
        id_counter++;
        this.destination = shape;
    }

    public static int getId_counter() {
        return id_counter;
    }

    public List<Integer> getPath() {
        return path;
    }
    public void resetPath(){
        this.path = new LinkedList<>();
    }
    public void remove_first_path(){
        this.path.remove(this.path.size()-1);
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }
    public int get_id() {
        return id;
    }

    public Shape getDestination() {
        return destination;
    }
}
