package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import org.example.capstone.dao.DbConnectivityClass;

import java.util.ArrayList;
import java.util.List;

public class BMICalculatorPage extends VBox {

    private TextField weightField;
    private TextField heightField;
    private Label bmiResultLabel;
    private ProgressBar bmiProgressBar;
    private LineChart<Number, Number> bmiHistoryChart;
    private XYChart.Series<Number, Number> bmiSeries;
    private List<Double> bmiHistory = new ArrayList<>();
    private PageManager pageManager;

    public BMICalculatorPage(PageManager pageManager) {
        this.pageManager = pageManager;

        getStyleClass().add("bmi-calculator-page");

        setPadding(new Insets(10));
        setSpacing(15);

        bmiHistoryChart = createBMIHistoryChart();
        bmiHistoryChart.getStyleClass().add("bmi-history-chart");
        bmiHistoryChart.minWidth(Double.MAX_VALUE);
        Label bmiTitle = new Label("BMI Calculator");
        bmiTitle.getStyleClass().add("bmi-title");
        weightField = new TextField();
        weightField.setPromptText("Enter weight (kg)");
        weightField.getStyleClass().add("text-field");
        heightField = new TextField();
        heightField.setPromptText("Enter height (m)");
        heightField.getStyleClass().add("text-field");
        Button calculateButton = new Button("Calculate BMI");
        calculateButton.setOnAction(e -> calculateBMI());
        calculateButton.getStyleClass().add("calculate-button");
        bmiResultLabel = new Label("Your BMI will be displayed here.");
        bmiResultLabel.getStyleClass().add("bmi-result-label");
        bmiProgressBar = new ProgressBar(0);
        bmiProgressBar.setPrefWidth(300);
        bmiProgressBar.getStyleClass().add("bmi-progress-bar");

        StackPane bmiChartWrapper = new StackPane();
        bmiChartWrapper.setStyle("-fx-background-color: rgba(255,255,255,0.0);");
        bmiChartWrapper.getChildren().add(bmiHistoryChart);
        bmiChartWrapper.setMaxWidth(Double.MAX_VALUE);
        bmiChartWrapper.setPrefWidth(Double.MAX_VALUE);
        bmiChartWrapper.getStyleClass().add("stack-pane");

        HBox inputBox = new HBox(10, new Label("Weight (kg):"), weightField, new Label("Height (m):"), heightField);
        inputBox.setSpacing(10);
        inputBox.getStyleClass().add("weight-height-input-box");
        getChildren().addAll(bmiTitle, inputBox, calculateButton, bmiResultLabel, bmiProgressBar, bmiChartWrapper);
        loadBMIHistory();
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            double bmi = weight / (height * height);
            int userId = pageManager.getUserSession().getUserId(); // Get the current user ID
            DbConnectivityClass.insertBMI(userId, height, weight, bmi); // Call the insertBMI method to save it in the database
            bmiHistory.add(bmi);
            bmiResultLabel.setText(String.format("Your BMI is: %.2f", bmi));
            updateProgressBar(bmi);
            updateBMIHistoryChart();
            loadBMIHistory();
        } catch (NumberFormatException e) {
            bmiResultLabel.setText("Please enter valid numbers for weight and height.");
        }
    }


    private void updateProgressBar(double bmi) {
        double progress;
        Color color;
        if (bmi < 18.5) {
            progress = 0.25;
            color = Color.BLUE;
        } else if (bmi < 24.9) {
            progress = 0.5;
            color = Color.GREEN;
        } else if (bmi < 29.9) {
            progress = 0.75;
            color = Color.ORANGE;
        } else {
            progress = 1.0;
            color = Color.RED;
        }
        bmiProgressBar.setProgress(progress);
        bmiProgressBar.setStyle("-fx-accent: " + toHexString(color) + ";");
    }

    private void updateBMIHistoryChart() {
        bmiSeries.getData().clear();
        for (int i = 0; i < bmiHistory.size(); i++) {
            bmiSeries.getData().add(new XYChart.Data<>(i + 1, bmiHistory.get(i)));
        }
    }
    private void loadBMIHistory() {
        int userId = pageManager.getUserSession().getUserId();
        bmiHistory = DbConnectivityClass.getBMIHistory(userId);
        updateBMIHistoryChart();
    }
    private LineChart<Number, Number> createBMIHistoryChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Entry Number");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("BMI");

        bmiHistoryChart = new LineChart<>(xAxis, yAxis);
        bmiHistoryChart.setTitle("BMI History");

        bmiSeries = new XYChart.Series<>();
        bmiSeries.setName("BMI Over Time");

        bmiHistoryChart.getData().add(bmiSeries);
        return bmiHistoryChart;
    }
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}