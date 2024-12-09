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
        //title of page
        Label settingsTitle = new Label("Settings");
        settingsTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        //create the swapper and buttons
        Label themeLabel = new Label("Theme Selection:");
        ToggleGroup themeToggleGroup = new ToggleGroup();
        RadioButton lightModeButton = new RadioButton("Light Mode");
        RadioButton darkModeButton = new RadioButton("Dark Mode");
        //connecting the buttons to the swapper
        lightModeButton.setToggleGroup(themeToggleGroup);
        darkModeButton.setToggleGroup(themeToggleGroup);
        //setting light theme to default
        lightModeButton.setSelected(true);

        lightModeButton.setOnAction(e->app.setTheme("light"));
        darkModeButton.setOnAction(e->app.setTheme("dark"));


        getChildren().addAll(settingsTitle, themeLabel, lightModeButton, darkModeButton);

    }
}





