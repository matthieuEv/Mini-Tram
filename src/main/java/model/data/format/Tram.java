package model.data.format;

import model.compute.Irigo;

public class Tram extends Thread{
    private static int id_counter = 0;
    private int id;
    private boolean active;

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

    //Routine
    /**
     * The routine of the thread tram
     */
    @Override
    public void run(){
        while(this.active){
            try {
                Thread.sleep(10000);
                Irigo.trigger_tram(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
