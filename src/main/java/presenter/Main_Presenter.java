package presenter;

import UI.Interface_UI;
import javafx.stage.Stage;
import model.ModelEntryPoint;
import utils.Pos;

import java.util.ArrayList;
import java.util.Map;

public class Main_Presenter {
    ModelEntryPoint model;
    Interface_UI ui;
    private static Main_Presenter instance = null;

    private Main_Presenter(ModelEntryPoint model , Interface_UI ui) {
        this.model = model;
        this.ui = ui;
    }

    public static Main_Presenter getInstance(ModelEntryPoint model , Interface_UI ui) {
        if (instance == null) {
            instance = new Main_Presenter(model , ui);
        }
        return instance;
    }

    public void init() {
        ui.appendPresenter(this);
        model.append_presenter(this);
    }

    public Map<Integer, Pos> getListStations(){
        return model.SEND_get_all_station();
    }
}
