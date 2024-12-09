package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage extends VBox {

    private PageManager pageManager;

    public HomePage(PageManager pageManager) {
        this.pageManager = pageManager;

        setPadding(new Insets(10));
        getStyleClass().add("home-page");

        // Create line chart and pie chart
        LineChart<Number, Number> lineChart = createLineChart();
        lineChart.getStyleClass().add("line-chart");

        PieChart pieChart = createPieChart();
        pieChart.getStyleClass().add("pie-chart");

        // Welcome text
        Text welcomeText = new Text("Welcome back!");
        welcomeText.getStyleClass().add("welcome-text");

        // Create the VBox layout for the home page content
        VBox homeVbox = new VBox(10, welcomeText, lineChart, new Separator(), pieChart);
        homeVbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Add the home title at the top and the homeVbox below
        getChildren().add(homeVbox);
    }

    private LineChart<Number, Number> createLineChart() {
        // X and Y axes
        NumberAxis xAxis = new NumberAxis("Entry Number", 0, 14, 1);
        NumberAxis yAxis = new NumberAxis("BMI", 0, 100, 10);

        // Create line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("BMI Trends");

        // Data series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("BMI Data");

        for(int i = 0; i < pageManager.getBmiHistory().size(); i++){
            series.getData().add(new XYChart.Data<>(i+1, pageManager.getBmiHistory().get(i)));
        }

        // Add series to chart
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);

        return lineChart;
    }

    private PieChart createPieChart() {
        // Create pie chart
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(
                new PieChart.Data("Mile Run!", 25),
                new PieChart.Data("Walking", 20),
                new PieChart.Data("Weight Training", 30),
                new PieChart.Data("Sprints", 25)
        );

        pieChart.setTitle("Activity Breakdown");
        pieChart.setLegendVisible(true);

        return pieChart;
    }

}







