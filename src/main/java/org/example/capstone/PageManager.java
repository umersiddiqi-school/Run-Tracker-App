package org.example.capstone;

import javafx.scene.layout.BorderPane;
import org.example.capstone.service.UserSession;

import java.util.ArrayList;
import java.util.List;

public class PageManager {

    private BorderPane mainLayout;
    private List<Double> bmiHistory;
    private BodyMetricApp app;
    private UserSession userSession;

    public PageManager(BorderPane mainLayout, BodyMetricApp app, UserSession userSession) {
        this.mainLayout = mainLayout;
        this.bmiHistory = new ArrayList<>();
        this.app = app;
        this.userSession = userSession;
    }
    public List<Double> getBmiHistory() {
        return bmiHistory;
    }
    public void addBmiEntry(double bmi) {
        bmiHistory.add(bmi);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void loadHomePage() {
        mainLayout.setCenter(new HomePage(this));
    }

    public void loadBMICalculatorPage() {
        mainLayout.setCenter(new BMICalculatorPage(this));
    }

    public void loadMuscleTrackerPage() {
        mainLayout.setCenter(new MuscleGroupTrackerPage());
    }

    public void loadProgressPage() {
        mainLayout.setCenter(new ProgressPage());
    }

    public void loadSettingsPage() {
        mainLayout.setCenter(new SettingsPage(app));
    }
}