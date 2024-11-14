package org.example.capstone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TopSection {

    private HBox topSection;

    public TopSection(String username, int age) {
        topSection = new HBox();
        topSection.setPadding(new Insets(10));
        topSection.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
        topSection.setAlignment(Pos.CENTER);

        // Personal Info
        Label personalInfoLabel = new Label("Logged in as: " + username + " | Age: " + age);
        personalInfoLabel.setStyle("-fx-text-fill: white;");
        topSection.getChildren().add(personalInfoLabel);
    }

    public HBox getTopSection() {
        return topSection;
    }
}


