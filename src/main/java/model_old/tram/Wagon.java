package model_old.tram;

public class Wagon extends Tram {
    //Container for interactions
    private Locomotive locomotive;

    //Constructor
    /**
     * Create a new wagon
     */
    public Wagon() {
        super();
    }

    /* === Activator === */
    /**
     * Activate the wagon
     * @param locomotive The locomotive on which the wagon is added
     */
    public void activate(int capacity, Locomotive locomotive) {
        super.activate(capacity, locomotive.getLine());
        this.locomotive = locomotive;
    }
    /**
     * Deactivate the wagon
     */
    public void deactivate() {
        super.deactivate();
        this.locomotive = null;
    }
}
