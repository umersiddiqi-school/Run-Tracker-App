package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SettingsPage extends VBox {

    public SettingsPage(BodyMetricApp app) {
        setPadding(new Insets(10));
        setSpacing(15);
        Label settingsTitle = new Label("Settings");
        settingsTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Label themeLabel = new Label("Theme Selection:");
        ToggleGroup themeToggleGroup = new ToggleGroup();
        RadioButton lightModeButton = new RadioButton("Light Mode");
        RadioButton darkModeButton = new RadioButton("Dark Mode");
        lightModeButton.setToggleGroup(themeToggleGroup);
        darkModeButton.setToggleGroup(themeToggleGroup);
        lightModeButton.setSelected(true);

        lightModeButton.setOnAction(e->app.setTheme("light"));
        darkModeButton.setOnAction(e->app.setTheme("dark"));


        getChildren().addAll(settingsTitle, themeLabel, lightModeButton, darkModeButton);

    }
}





