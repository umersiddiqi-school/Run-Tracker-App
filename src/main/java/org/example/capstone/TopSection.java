package org.example.capstone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class TopSection {

    private HBox topSection;

    public TopSection(String username, int age) {
        topSection = new HBox();
        topSection.setPadding(new Insets(10));
        topSection.getStyleClass().add("top-section");
        topSection.setAlignment(Pos.CENTER);

        // Personal Info
        StackPane labelContainer = new StackPane();
        labelContainer.getStyleClass().add("label-container");
        labelContainer.setPadding(new Insets(5));
        labelContainer.setAlignment(Pos.CENTER);

        Label personalInfoLabel = new Label("Logged in as: " + username);
        personalInfoLabel.getStyleClass().add("personal-info-label");

        labelContainer.getChildren().add(personalInfoLabel);

        HBox.setHgrow(labelContainer, javafx.scene.layout.Priority.ALWAYS);
        topSection.getChildren().add(labelContainer);
    }

    public HBox getTopSection() {
        return topSection;
    }
}


