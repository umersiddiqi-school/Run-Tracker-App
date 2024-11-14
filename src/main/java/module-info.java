module org.example.capstone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.capstone to javafx.fxml;
    exports org.example.capstone;
}