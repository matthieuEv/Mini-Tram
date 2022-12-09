package model.compute.progression;

import model.data.Data;

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
        while(this.active) {
            try{
                Thread.sleep(3000);
                synchronized (Data.getInstance()){
                    Data.peopleAppear();
                }
                System.out.println("People added");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
