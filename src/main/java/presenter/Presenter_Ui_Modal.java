package presenter;

public class Presenter_Ui_Modal {
    private static Presenter_Ui_Modal instance = null;

    private Presenter_Ui_Modal() {
    }

    public static Presenter_Ui_Modal getInstance() {
        if (instance == null) {
            instance = new Presenter_Ui_Modal();
        }
        return instance;
    }
}
