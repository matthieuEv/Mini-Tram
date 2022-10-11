module com.minitram.minitram {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.minitram.minitram to javafx.fxml;
    exports com.minitram.minitram;
}