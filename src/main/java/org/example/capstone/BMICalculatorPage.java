package org.example.capstone;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BMICalculatorPage extends VBox {

    private TextField weightField;
    private TextField heightField;
    private Label bmiResultLabel;
    private ProgressBar bmiProgressBar;
    private LineChart<Number, Number> bmiHistoryChart;
    private XYChart.Series<Number, Number> bmiSeries;
    private List<Double> bmiHistory;

    public BMICalculatorPage() {
        setPadding(new Insets(10));
        setSpacing(15);

        bmiHistory = new ArrayList<>();

        // Title
        Label bmiTitle = new Label("BMI Calculator");
        bmiTitle.setFont(new Font("Arial", 20));

        // Input fields for weight and height
        weightField = new TextField();
        weightField.setPromptText("Enter weight (kg)");

        heightField = new TextField();
        heightField.setPromptText("Enter height (m)");

        // Button to calculate BMI
        Button calculateButton = new Button("Calculate BMI");
        calculateButton.setOnAction(e -> calculateBMI());

        // Label to show the BMI result
        bmiResultLabel = new Label("Your BMI will be displayed here.");

        // Progress bar to indicate BMI range
        bmiProgressBar = new ProgressBar(0);
        bmiProgressBar.setPrefWidth(300);

        // Chart to show BMI history
        bmiHistoryChart = createBMIHistoryChart();

        // Set up layout
        HBox inputBox = new HBox(10, new Label("Weight (kg):"), weightField, new Label("Height (m):"), heightField);
        inputBox.setSpacing(10);
        getChildren().addAll(bmiTitle, inputBox, calculateButton, bmiResultLabel, bmiProgressBar, bmiHistoryChart);
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            // BMI formula: weight (kg) / [height (m) * height (m)]
            double bmi = weight / (height * height);

            // Store BMI and update history graph
            bmiHistory.add(bmi);
            updateBMIHistoryChart();

            // Update the result label and progress bar
            bmiResultLabel.setText(String.format("Your BMI is: %.2f", bmi));
            updateProgressBar(bmi);

        } catch (NumberFormatException e) {
            bmiResultLabel.setText("Please enter valid numbers for weight and height.");
        }
    }

    private void updateProgressBar(double bmi) {
        double progress;
        Color color;

        // Set progress and color based on BMI ranges
        if (bmi < 18.5) {
            progress = 0.25;
            color = Color.BLUE; // Underweight
        } else if (bmi < 24.9) {
            progress = 0.5;
            color = Color.GREEN; // Normal weight
        } else if (bmi < 29.9) {
            progress = 0.75;
            color = Color.ORANGE; // Overweight
        } else {
            progress = 1.0;
            color = Color.RED; // Obesity
        }

        // Update progress bar
        bmiProgressBar.setProgress(progress);
        bmiProgressBar.setStyle("-fx-accent: " + toHexString(color) + ";");
    }

    private void updateBMIHistoryChart() {
        bmiSeries.getData().clear();
        for (int i = 0; i < bmiHistory.size(); i++) {
            bmiSeries.getData().add(new XYChart.Data<>(i + 1, bmiHistory.get(i)));
        }
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

    // Convert Color to Hex string for CSS
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}





