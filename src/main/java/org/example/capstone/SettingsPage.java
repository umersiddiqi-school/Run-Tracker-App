package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsPage extends VBox {

    public SettingsPage() {
        setPadding(new Insets(10));
        Label settingsTitle = new Label("Settings");
        getChildren().add(settingsTitle);
        // Add settings components here
    }
}





