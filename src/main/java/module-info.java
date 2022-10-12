module com.example.projdontwork {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.minitram.minitram to javafx.fxml;
    exports com.minitram.minitram;
}