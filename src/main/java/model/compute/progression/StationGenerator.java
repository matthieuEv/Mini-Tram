package model.compute.progression;

import model.ModelEntryPoint;
import model.data.Data;
import model.data.format.Station;
import model.mediator.StationPeople;
import utils.Pos;
import utils.Shape;

import java.util.Random;

public class StationGenerator extends Thread {
    private boolean active;
    private int number_of_station = 0;
    private Random r = new Random();

    public StationGenerator(int number_of_station) {
        active = true;
        this.number_of_station = number_of_station;
    }

    /**
     * active the thread
     */
    public void setActive() {
        this.active = true;
    }

    /**
     * Kill the thread
     */
    public void kill() {
        this.active = false;
    }


    /**
     * Thead to generate station randomly
     */
    @Override
    public void run() {
        while(this.active && number_of_station < 10) {
            try{
                Thread.sleep(10000);
                Station s = new Station(Shape.random_shape(), calculate_newpos());
                synchronized (Data.getInstance()){
                    Data.set_station(s);
                    StationPeople.getInstance().add_station(s.get_id());
                    ModelEntryPoint.ADD_station(s);
                }
                System.out.println("Station added : " + s.get_pos().x + " " + s.get_pos().y);
                number_of_station++;
            } catch (InterruptedException e) {
                System.out.println("StationGenerator interrupted");
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculate a new position for the station to be generated
     * @return a position for the new station
     */
    private Pos calculate_newpos (){
        Pos p = new Pos(0, 0);
        boolean good = false;
        //Now check if the position is not already taken
        do {
            p.x = r.nextInt(45);
            p.y  = r.nextInt(35);
            for (Station st : Data.get_stations().values()) {
                if (st.get_pos().equals(p)) {
                    good = false;
                    break;
                } else {
                    good = true;
                }
            }
        } while (!good);
        return p;
    }
}

