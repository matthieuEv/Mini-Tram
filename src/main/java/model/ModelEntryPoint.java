package model;

import model.compute.Irigo;

public class ModelEntryPoint {
    private static ModelEntryPoint instance = null;
    private Irigo irigo;

    private ModelEntryPoint() {
        this.irigo = Irigo.getInstance();
    }

    /**
     * Get the instance of the singleton
     * @return the instance of the class
     * @warning this if call for the first time activate the builder
     */
    public static ModelEntryPoint getInstance() {
        if(instance == null) {
            instance = new ModelEntryPoint();
        }
        return instance;
    }



}
