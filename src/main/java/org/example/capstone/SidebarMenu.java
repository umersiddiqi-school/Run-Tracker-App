package org.example.capstone;


import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SidebarMenu {

    private VBox sidebar;

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

        sidebar.getChildren().addAll(homeButton, bmiButton, muscleButton, progressButton, settingsButton);
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

    public VBox getSidebar() {
        return sidebar;
    }
}
