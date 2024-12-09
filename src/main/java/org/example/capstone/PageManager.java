package org.example.capstone;

import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class PageManager {

    private BorderPane mainLayout;
    private List<Double> bmiHistory;
    private BodyMetricApp app;

    public PageManager(BorderPane mainLayout, BodyMetricApp app) {
        this.mainLayout = mainLayout;
        this.bmiHistory = new ArrayList<>();
        this.app=app;
    }
    public List<Double> getBmiHistory(){
        return bmiHistory;
    }
    public void addBmiEntry(double bmi){
        bmiHistory.add(bmi);
    }

    public void loadHomePage() {
        mainLayout.setCenter(new HomePage(this));
    }

    public void loadBMICalculatorPage() {
        mainLayout.setCenter(new BMICalculatorPage(this));
    }

    public void loadMuscleTrackerPage() {
        mainLayout.setCenter(new MusclePage());
    }

    public void loadProgressPage() {
        mainLayout.setCenter(new ProgressPage());
    }

    public void loadSettingsPage() {
        mainLayout.setCenter(new SettingsPage(app));
    }
}








