package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MuscleGroupTrackerPage extends VBox {

    public MuscleGroupTrackerPage() {
        setPadding(new Insets(10));
        Label muscleTitle = new Label("Muscle Group Tracker");
        getChildren().add(muscleTitle);
        // Add muscle group tracking components here
    }
}


