package model.data.format;

import model.compute.Irigo;
import utils.Pos;

public class Tram extends Thread{
    private static int id_counter = 0;
    private int id;
    private boolean active;
    private boolean direction;
    private int place;

    public Tram(){
        this.id = id_counter;
        id_counter++;
        this.place = 4;
    }

    public static int getId_counter() {
        return id_counter;
    }

    public int get_id() {
        return id;
    }

    public int getPlace() {
        return place;
    }

    public void set_active(boolean active) {
        this.active = active;
    }
    public boolean is_active() {
        return active;
    }

    public void change_direction() {
        this.direction = !this.direction;
    }

    /**
     * Set the direction of the tram
     * 0 = normal
     * 1 = inverse
     * @param direction the direction of the tram
     */
    public void change_direction(boolean direction) {
        this.direction = direction;
    }
    public boolean get_direction() {
        return direction;
    }

    //Routine
    /**
     * The routine of the thread tram
     * It wait for the time of travel between two station, them call the function when it arrive
     */
    @Override
    public void run(){
        int time = 1000;
        while(this.active){
            try {
                Thread.sleep(time);
                time = Irigo.trigger_tram(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public void reset_id_counter() {
        id_counter = 0;
    }


}
