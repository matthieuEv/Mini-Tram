package presenter;

import UI.Interface_UI;
import UI.items.Station_UI;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ModelEntryPoint;
import utils.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_Presenter {
    ModelEntryPoint model;
    Interface_UI ui;
    private static Main_Presenter instance = null;
    private Map<Integer, Color> listLines;

    private Main_Presenter(ModelEntryPoint model , Interface_UI ui) {
        this.model = model;
        this.ui = ui;

        listLines = new HashMap<Integer, Color>();
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

        ui.showInterface();
    }

    public Map<Integer, Pos> getListStations(){
        Map<Integer, Pos> output = new HashMap<>();
        for(Map.Entry<Integer, Pos> entry : model.SEND_get_all_station().entrySet()){
            output.put(entry.getKey(), new Pos(entry.getValue().x*20, entry.getValue().y*20));
        }
        return output;
    }

    public void syncLine(Color color){
        for (Integer ids : model.SEND_all_line_id()){
            if(!listLines.containsKey(ids)){
                listLines.put(ids, color);
            }
        }
    }

    public void modelAddLine(int idLine, int idStation1, int idStation2){
        model.GET_activate_line(idLine, idStation1, idStation2);
    }

    public void SEND_tram_next_step(int idTram, int idStation, int idLine){
        ui.SEND_tram_next_step(idTram, idStation, idLine);
    }
    public void SEND_add_tram(int idStation, int idLine){
        ui.SEND_add_tram(idStation, idLine);
    }
    
}
