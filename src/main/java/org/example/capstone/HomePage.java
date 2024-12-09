package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
public class HomePage extends VBox {

    private PageManager pageManager;

    public HomePage(PageManager pageManager) {
        this.pageManager = pageManager;
        setPadding(new Insets(10));
        getStyleClass().add("home-page");
        LineChart<Number, Number> lineChart = createLineChart();
        lineChart.getStyleClass().add("line-chart");
        PieChart pieChart = createPieChart();
        pieChart.getStyleClass().add("pie-chart");
        Text welcomeText = new Text("Welcome back!");
        welcomeText.getStyleClass().add("welcome-text");
        VBox homeVbox = new VBox(10, welcomeText, lineChart, new Separator(), pieChart);
        homeVbox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        getChildren().add(homeVbox);
    }

    private LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis("Entry Number", 0, 14, 1);
        NumberAxis yAxis = new NumberAxis("BMI", 0, 100, 10);
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("BMI Trends");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("BMI Data");

        for(int i = 0; i < pageManager.getBmiHistory().size(); i++){
            series.getData().add(new XYChart.Data<>(i+1, pageManager.getBmiHistory().get(i)));
        }
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);

        return lineChart;
    }

    private PieChart createPieChart() {
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







