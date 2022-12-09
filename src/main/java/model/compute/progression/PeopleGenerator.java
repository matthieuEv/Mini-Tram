package model.compute.progression;

import model.ModelEntryPoint;
import model.data.Data;
import model.mediator.StationPeople;

public class PeopleGenerator extends Thread {
    private boolean active;

    public PeopleGenerator() {
        this.active = true;
    }

    public void setActive() {
        this.active = true;
    }
    public void kill() {
        this.active = false;
    }

    @Override
    public void run() {
        int time = 0;
        while(this.active) {
            try{
                time++;
                int timeSleep = 3000/(time%20+1);
                timeSleep = Math.max(timeSleep, 1000);
                Thread.sleep(timeSleep);
                endGame();
                synchronized (Data.getInstance()){
                    Data.peopleAppear();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void endGame(){
        //Check if a station as more than 8 people
        if (StationPeople.getInstance().get_station_with_too_much_people().size()>=1){
            ModelEntryPoint.stopGame();
        }
    }


}
