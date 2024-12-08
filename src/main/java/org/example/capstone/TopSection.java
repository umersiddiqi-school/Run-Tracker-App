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
        topSection.setStyle("-fx-background-image: url('TopSectionBG.jpg');"
                + " -fx-background-size: cover;"
                + " -fx-background-position: center;");
        topSection.setAlignment(Pos.CENTER);

        // Personal Info
        StackPane labelContainer = new StackPane();
        labelContainer.setStyle("-fx-background-color:rgba(255,255,255,0.7);"
        + "-fx-border-width: 2;"
        + "-fx-border-radius: 10;"
        + "-fx-background-radius: 10;");
        labelContainer.setPadding(new Insets(5));
        labelContainer.setAlignment(Pos.CENTER);

        Label personalInfoLabel = new Label("Logged in as: " + username + " | Age: " + age);
        personalInfoLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size:16px;");

        labelContainer.getChildren().add(personalInfoLabel);

        HBox.setHgrow(labelContainer, javafx.scene.layout.Priority.ALWAYS);
        topSection.getChildren().add(labelContainer);
    }

    public HBox getTopSection() {
        return topSection;
    }
}


