module com.example.projdontwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires javafx.media;


    opens com.minitram.minitram to javafx.fxml;
    exports com.minitram.minitram;
    exports UI;
    exports model;
    exports model.compute;
    exports model.compute.progression;
    exports model.data;

}