package org.example.capstone;

import javafx.scene.layout.BorderPane;

public class PageManager {

    private BorderPane mainLayout;

    public PageManager(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
    }

    public void loadHomePage() {
        mainLayout.setCenter(new HomePage());
    }

    public void loadBMICalculatorPage() {
        mainLayout.setCenter(new BMICalculatorPage());
    }

    public void loadMuscleTrackerPage() {
        mainLayout.setCenter(new MuscleGroupTrackerPage());
    }

    public void loadProgressPage() {
        mainLayout.setCenter(new ProgressPage());
    }

    public void loadSettingsPage() {
        mainLayout.setCenter(new SettingsPage());
    }
}








