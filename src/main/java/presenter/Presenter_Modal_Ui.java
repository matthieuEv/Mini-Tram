package presenter;

public class Presenter_Modal_Ui {
    private static Presenter_Modal_Ui instance = null;

    private Presenter_Modal_Ui() {
    }

    public static Presenter_Modal_Ui getInstance() {
        if (instance == null) {
            instance = new Presenter_Modal_Ui();
        }
        return instance;
    }
}
