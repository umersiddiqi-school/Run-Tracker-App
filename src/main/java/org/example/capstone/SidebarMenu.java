package org.example.capstone;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SidebarMenu {

    private VBox sidebar;
    private Label clockLabel;

    public SidebarMenu(BodyMetricApp app) {
        sidebar = new VBox(); // No spacing for flush buttons
        sidebar.setStyle("-fx-background-color: #555;");
        sidebar.setPrefWidth(200);

        // Sidebar Buttons
        Button homeButton = createSidebarButton("Home", app);
        Button bmiButton = createSidebarButton("BMI Calculator", app);
        Button muscleButton = createSidebarButton("Muscle Group Tracker", app);
        Button progressButton = createSidebarButton("Progress", app);
        Button settingsButton = createSidebarButton("Settings", app);

        sidebar.getChildren().addAll(homeButton, new Separator(), bmiButton, new Separator(), muscleButton, new Separator(), progressButton, new Separator(), settingsButton, new Separator());
        setupClock();
    }

    // Helper method to create a styled sidebar button with hover effect
    private Button createSidebarButton(String text, BodyMetricApp app) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);  // Set button to full width of sidebar

        // Set default and hover styles with rectangular shape
        button.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #666; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));

        // Assign button actions based on text
        switch (text) {
            case "Home":
                button.setOnAction(e -> app.getPageManager().loadHomePage());
                break;
            case "BMI Calculator":
                button.setOnAction(e -> app.getPageManager().loadBMICalculatorPage());
                break;
            case "Muscle Group Tracker":
                button.setOnAction(e -> app.getPageManager().loadMuscleTrackerPage());
                break;
            case "Progress":
                button.setOnAction(e -> app.getPageManager().loadProgressPage());
                break;
            case "Settings":
                button.setOnAction(e -> app.getPageManager().loadSettingsPage());
                break;
        }

        return button;
    }

    public void setupClock(){
        clockLabel = new Label();
        clockLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-background-color: rgba(0,0,0,0.5); -fx-padding: 5px");
        clockLabel.setMaxWidth(Double.MAX_VALUE);


        VBox.setVgrow(clockLabel, Priority.ALWAYS);

        sidebar.getChildren().add(clockLabel);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e-> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void updateClock(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        String currentTime = sdf.format(new Date());
        clockLabel.setText(currentTime);
    }

    public VBox getSidebar() {
        return sidebar;
    }
}
