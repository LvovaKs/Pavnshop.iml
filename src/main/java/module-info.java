module com.example.pavnshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.pavnshop to javafx.fxml;
    exports com.example.pavnshop;
}