module org.example.capstone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;


    opens org.example.capstone to javafx.fxml;
    exports org.example.capstone;
}