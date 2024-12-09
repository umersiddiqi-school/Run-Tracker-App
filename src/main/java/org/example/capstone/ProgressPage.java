package org.example.capstone;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class ProgressPage extends VBox {

    private LineChart<Number, Number> lineChart;
    private TextField distanceField;
    private TextField timeField;
    private TextField heartRateField;
    private TableView<DataEntry> tableView;
    private ObservableList<DataEntry> dataEntries;
    private XYChart.Series<Number, Number> series;

    public ProgressPage() {
        dataEntries = FXCollections.observableArrayList(); // Central data storage
        setupChart();
        setupInputFields();
        setupTableView();

        this.getStyleClass().add("progress-page");
    }

    private void setupChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Distance (km)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Time (min)");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Performance Chart");

        series = new XYChart.Series<>();
        series.setName("Performance Line");

        lineChart.getData().add(series);

        lineChart.getStyleClass().add("line-chart");

        this.getChildren().add(lineChart);
    }

    private void setupInputFields() {
        distanceField = new TextField();
        distanceField.setPromptText("Enter Distance (km)");
        distanceField.getStyleClass().add("text-field");
        timeField = new TextField();
        timeField.setPromptText("Enter Time (min)");
        timeField.getStyleClass().add("text-field");
        heartRateField = new TextField();
        heartRateField.setPromptText("Enter Heart Rate (bpm)");
        heartRateField.getStyleClass().add("text-field");
        Button addButton = new Button("Add Data");
        addButton.setOnAction(e -> addDataFromInput());
        addButton.getStyleClass().add("button");

        HBox inputBox = new HBox(10, distanceField, timeField, heartRateField, addButton);
        inputBox.setPadding(new Insets(10));

        this.getChildren().add(inputBox);
    }

    private void setupTableView() {
        tableView = new TableView<>();
        tableView.setItems(dataEntries);
        tableView.getStyleClass().add("table-view");
        TableColumn<DataEntry, Double> distanceColumn = new TableColumn<>("Distance (km)");
        distanceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getDistance()).asObject());
        TableColumn<DataEntry, Double> timeColumn = new TableColumn<>("Time (min)");
        timeColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTime()).asObject());
        TableColumn<DataEntry, String> paceColumn = new TableColumn<>("Pace (min/km)");
        paceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPace()));
        TableColumn<DataEntry, Integer> heartRateColumn = new TableColumn<>("Heart Rate (bpm)");
        heartRateColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getHeartRate()).asObject());
        TableColumn<DataEntry, Void> pauseColumn = new TableColumn<>("Pause");
        pauseColumn.setCellFactory(param -> new TableCell<>() {
            private final Button pauseButton = new Button("Pause");

            {
                pauseButton.setOnAction(e -> {
                    DataEntry entry = getTableView().getItems().get(getIndex());
                    entry.setPaused(!entry.isPaused());
                    updateChart();
                    updateRowStyle(entry);
                    pauseButton.setText(entry.isPaused() ? "Resume" : "Pause");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pauseButton);
                    DataEntry entry = getTableView().getItems().get(getIndex());
                    pauseButton.setText(entry.isPaused() ? "Resume" : "Pause");
                }
            }
        });
        TableColumn<DataEntry, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(e -> {
                    DataEntry entry = getTableView().getItems().get(getIndex());
                    deleteDataEntry(entry);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        tableView.getColumns().addAll(distanceColumn, timeColumn, paceColumn, heartRateColumn, pauseColumn, deleteColumn);
        this.getChildren().add(tableView);
    }

    private void addDataFromInput() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double time = Double.parseDouble(timeField.getText());
            int heartRate = Integer.parseInt(heartRateField.getText());
            String pace = String.format("%.2f", time / distance) + " min/km";
            DataEntry newEntry = new DataEntry(distance, time, pace, heartRate);
            dataEntries.add(newEntry);
            addDataToChart(newEntry);
            distanceField.clear();
            timeField.clear();
            heartRateField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please ensure all fields are filled correctly with numbers where required.");
        } catch (ArithmeticException e) {
            showAlert("Invalid Data", "Distance cannot be zero.");
        }
    }

    private void addDataToChart(DataEntry entry) {
        if (!entry.isPaused()) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(entry.getDistance(), entry.getTime());
            series.getData().add(dataPoint);
            dataPoint.nodeProperty().addListener((observable, oldNode, newNode) -> {
                if (newNode != null) {
                    Tooltip tooltip = new Tooltip("Pace: " + entry.getPace() + "\nHeart Rate: " + entry.getHeartRate() + " bpm");
                    Tooltip.install(newNode, tooltip);
                }
            });

            entry.setChartData(dataPoint);
        }
    }

    private void updateChart() {
        series.getData().clear();
        for (DataEntry entry : dataEntries) {
            if (!entry.isPaused()) {
                addDataToChart(entry);
            }
        }
    }
    private void deleteDataEntry(DataEntry entry) {
        dataEntries.remove(entry);
        if (entry.getChartData() != null) {
            series.getData().remove(entry.getChartData());
        }
    }
    private void updateRowStyle(DataEntry entry) {
        tableView.refresh();
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static class DataEntry {
        private final double distance;
        private final double time;
        private final String pace;
        private final int heartRate;
        private SimpleBooleanProperty paused = new SimpleBooleanProperty(false);
        private XYChart.Data<Number, Number> chartData;

        public DataEntry(double distance, double time, String pace, int heartRate) {
            this.distance = distance;
            this.time = time;
            this.pace = pace;
            this.heartRate = heartRate;
        }

        public double getDistance() { return distance; }
        public double getTime() { return time; }
        public String getPace() { return pace; }
        public int getHeartRate() { return heartRate; }
        public boolean isPaused() { return paused.get(); }
        public void setPaused(boolean paused) { this.paused.set(paused); }
        public XYChart.Data<Number, Number> getChartData() { return chartData; }
        public void setChartData(XYChart.Data<Number, Number> chartData) { this.chartData = chartData; }
    }


}





