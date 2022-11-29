module com.example.projdontwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.jetbrains.annotations;


    opens com.minitram.minitram to javafx.fxml;
    exports com.minitram.minitram;
    
    exports model;
    exports model.data;
    exports model.data.format;
    exports model.mediator;
    exports model.compute;

    exports presenter;

    exports UI;
    exports UI.items;

    exports utils;
}