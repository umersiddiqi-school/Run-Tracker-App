package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomePage extends VBox {

    public HomePage() {
        setPadding(new Insets(10));
        Label homeTitle = new Label("Welcome to the Body Metric App");
        getChildren().add(homeTitle);
    }
}







