package org.example.capstone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MusclePage extends VBox {

    // Muscle group histories
    private final List<String> quadHistory = new ArrayList<>();
    private final List<String> hamHistory = new ArrayList<>();
    private final List<String> calfHistory = new ArrayList<>();
    private final List<String> gluteHistory = new ArrayList<>();

    public MusclePage() {
        // Set padding and layout
        setPadding(new Insets(15));
        setSpacing(20);
        getStyleClass().add("muscle-group-tracker");  // Apply the general page style class

        // Title of the page
        Label muscleTitle = new Label("Muscle Group Tracker");
        muscleTitle.getStyleClass().add("muscle-title");  // Apply title style

        // Create muscle input sections
        VBox quadVBox = createMuscleGroup("Quadriceps", quadHistory);
        VBox hamVBox = createMuscleGroup("Hamstrings", hamHistory);
        VBox calfVBox = createMuscleGroup("Calves", calfHistory);
        VBox gluteVBox = createMuscleGroup("Glutes", gluteHistory);

        // Scroll pane to make sure the page is scrollable if content exceeds window size
        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox(muscleTitle, quadVBox, hamVBox, calfVBox, gluteVBox);
        container.setSpacing(15);
        scrollPane.setContent(container);
        scrollPane.setFitToWidth(true);

        // Add scrollPane as the root content
        getChildren().add(scrollPane);

        // Add the CSS file for styling
        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    private VBox createMuscleGroup(String muscleName, List<String> history) {
        VBox muscleVBox = new VBox(15);
        muscleVBox.setPadding(new Insets(10));
        muscleVBox.getStyleClass().add("muscle-group-box");  // Apply muscle group box style

        // Create labels and text fields for size and strength
        Label muscleLabel = new Label(muscleName);
        muscleLabel.getStyleClass().add("muscle-label");  // Apply label style

        // Size and strength fields with proper padding and width
        TextField sizeField = new TextField();
        sizeField.setPromptText(muscleName + " Size (cm)");
        sizeField.setMaxWidth(200);
        sizeField.getStyleClass().add("muscle-size-field");  // Apply size field style

        TextField strengthField = new TextField();
        strengthField.setPromptText(muscleName + " Strength (kg)");
        strengthField.setMaxWidth(200);
        strengthField.getStyleClass().add("muscle-strength-field");  // Apply strength field style

        // Create buttons for updating and viewing history
        Button updateButton = new Button("Update " + muscleName);
        updateButton.getStyleClass().add("update-button");  // Apply update button style
        updateButton.setMaxWidth(150);
        updateButton.setOnAction(e -> updateMuscleGroup(muscleName, sizeField, strengthField, history));

        Button historyButton = new Button("View " + muscleName + " History");
        historyButton.getStyleClass().add("history-button");  // Apply history button style
        historyButton.setMaxWidth(150);
        historyButton.setOnAction(e -> viewMuscleGroupHistory(muscleName, history));

        // Horizontal box for size and strength input fields
        HBox inputBox = new HBox(10, sizeField, strengthField);
        inputBox.setAlignment(Pos.CENTER);

        // Add all components into the VBox for this muscle group
        muscleVBox.getChildren().addAll(muscleLabel, inputBox, updateButton, historyButton);

        return muscleVBox;
    }

    // Update muscle group size and strength
    private void updateMuscleGroup(String muscleName, TextField sizeField, TextField strengthField, List<String> history) {
        try {
            double size = Double.parseDouble(sizeField.getText());
            double strength = Double.parseDouble(strengthField.getText());
            // Add to history or update data
            history.add(String.format("Size: %.2f cm, Strength: %.2f kg", size, strength));
            showAlert(Alert.AlertType.INFORMATION, muscleName + " Update", muscleName + " updated: Size = " + size + " cm, Strength = " + strength + " kg.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for size and strength.");
        }
    }

    // Show muscle group history
    private void viewMuscleGroupHistory(String muscleName, List<String> history) {
        if (history.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, muscleName + " History", "No history available for " + muscleName + ".");
            return;
        }

        StringBuilder historyMessage = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            historyMessage.append(String.format("%d) %s\n", i + 1, history.get(i)));
        }

        Dialog<Void> historyDialog = new Dialog<>();
        historyDialog.setTitle(muscleName + " History");
        historyDialog.setHeaderText(muscleName + " History");

        TextArea textArea = new TextArea(historyMessage.toString());
        textArea.setWrapText(true);
        textArea.setEditable(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(textArea);

        historyDialog.getDialogPane().setContent(scrollPane);
        historyDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        historyDialog.showAndWait();
    }

    // Show alert for updates and error handling
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
