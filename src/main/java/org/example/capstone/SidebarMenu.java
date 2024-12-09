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
        // Assign button actions based on text
        switch (text) {
            case "Home":
                button.setOnAction(e -> app.getPageManager().loadHomePage());
                button.setStyle("-fx-background-color: rgb(204,204,204); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgb(200,200,200); -fx-text-fill: #66ccff; -fx-font-size: 16px; -fx-background-radius: 0;"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(196,196,196); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
                break;
            case "BMI Calculator":
                button.setOnAction(e -> app.getPageManager().loadBMICalculatorPage());
                button.setStyle("-fx-background-color: rgb(179,179,179); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgb(175,175,175); -fx-text-fill: #66ccff; -fx-font-size: 16px; -fx-background-radius: 0;"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(183,183,183); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
                break;
            case "Muscle Group Tracker":
                button.setOnAction(e -> app.getPageManager().loadMuscleTrackerPage());
                button.setStyle("-fx-background-color: rgb(153,153,153); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgb(149,149,149); -fx-text-fill: #66ccff; -fx-font-size: 16px; -fx-background-radius: 0;"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(157,157,157); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
                break;
            case "Progress":
                button.setOnAction(e -> app.getPageManager().loadProgressPage());
                button.setStyle("-fx-background-color: rgb(128,128,128); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgb(124,124,124); -fx-text-fill: #66ccff; -fx-font-size: 16px; -fx-background-radius: 0;"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(132,132,132); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
                break;
            case "Settings":
                button.setOnAction(e -> app.getPageManager().loadSettingsPage());
                button.setStyle("-fx-background-color: rgb(102,102,102); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgb(98,98,98); -fx-text-fill: #66ccff; -fx-font-size: 16px; -fx-background-radius: 0;"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(106,106,106); -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
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
