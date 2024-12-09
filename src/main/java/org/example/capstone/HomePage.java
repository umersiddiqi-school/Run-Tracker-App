package org.example.capstone;

import javafx.geometry.Insets;
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
import org.example.capstone.dao.DbConnectivityClass;

import java.util.List;

public class HomePage extends VBox {

    private PageManager pageManager;
    private LineChart<Number, Number> lineChart;
    private PieChart pieChart;

    public HomePage(PageManager pageManager) {
        this.pageManager = pageManager;

        setPadding(new Insets(10));
        getStyleClass().add("home-page");

        // Create line chart and pie chart
        lineChart = createLineChart();
        lineChart.getStyleClass().add("line-chart");

        pieChart = createPieChart();
        pieChart.getStyleClass().add("pie-chart");

        // Welcome text
        Text welcomeText = new Text("Welcome back!");
        welcomeText.getStyleClass().add("welcome-text");

        // Create the VBox layout for the home page content
        VBox homeVbox = new VBox(10, welcomeText, lineChart, new Separator(), pieChart);
        homeVbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Add the home title at the top and the homeVbox below
        getChildren().add(homeVbox);

        // Initially load the BMI history
        loadBMIHistory();
    }

    public void refreshPage() {
        lineChart.getData().clear();
        loadBMIHistory();
    }

    private void loadBMIHistory() {
        int userId = pageManager.getUserSession().getUserId();
        List<Double> bmiHistory = DbConnectivityClass.getBMIHistory(userId);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("BMI Data");
        for (int i = 0; i < bmiHistory.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, bmiHistory.get(i)));
        }

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private LineChart<Number, Number> createLineChart() {
        // X and Y axes
        NumberAxis xAxis = new NumberAxis("Entry Number", 0, 14, 1);
        NumberAxis yAxis = new NumberAxis("BMI", 0, 100, 10);

        // Create line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("BMI Trends");

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
