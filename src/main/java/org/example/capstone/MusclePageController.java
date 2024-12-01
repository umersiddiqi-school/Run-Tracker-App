package org.example.capstone;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MusclePageController {

    @FXML
    private TextField quadSize, quadStrength;
    @FXML
    private TextField hamSize, hamStrength;
    @FXML
    private TextField calfSize, calfStrength;
    @FXML
    private TextField gluteSize, gluteStrength;

    private final List<String> quadHistory = new ArrayList<>();
    private final List<String> hamHistory = new ArrayList<>();
    private final List<String> calfHistory = new ArrayList<>();
    private final List<String> gluteHistory = new ArrayList<>();

    @FXML
    public void updateQuadriceps() {
        addToHistory(quadSize, quadStrength, quadHistory, "Quadriceps");
    }

    @FXML
    public void updateHamstrings() {
        addToHistory(hamSize, hamStrength, hamHistory, "Hamstrings");
    }

    @FXML
    public void updateCalves() {
        addToHistory(calfSize, calfStrength, calfHistory, "Calves");
    }

    @FXML
    public void updateGlutes() {
        addToHistory(gluteSize, gluteStrength, gluteHistory, "Glutes");
    }

    private void addToHistory(TextField sizeField, TextField strengthField, List<String> history, String muscleName) {
        double newSize = processInput(sizeField, muscleName + " Size");
        double newStrength = processInput(strengthField, muscleName + " Strength");

        history.add(String.format("size= %.2f, strength= %.2f", newSize, newStrength));

        showAlert(Alert.AlertType.INFORMATION, "Update Successful",
                String.format("%s successfully updated and added to the history.",
                        muscleName, newSize, newStrength));
    }



    @FXML
    public void checkQuadHistory() {
        showHistory(quadHistory, "Quadriceps");
    }

    @FXML
    public void checkHamHistory() {
        showHistory(hamHistory, "Hamstrings");
    }

    @FXML
    public void checkCalfHistory() {
        showHistory(calfHistory, "Calves");
    }

    @FXML
    public void checkGluteHistory() {
        showHistory(gluteHistory, "Glutes");
    }

    private void showHistory(List<String> history, String muscleName) {
        if (history.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, muscleName + " History", "No history available for " + muscleName + ".");
            return;
        }

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(muscleName + " History");
        dialog.setHeaderText(muscleName + " History");

        StringBuilder historyMessage = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            historyMessage.append(String.format("%d) %s\n", i + 1, history.get(i)));
        }

        TextArea textArea = new TextArea(historyMessage.toString());
        textArea.setWrapText(true);
        textArea.setEditable(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(textArea);

        scrollPane.setPrefSize(400, 300);
        dialog.getDialogPane().setContent(scrollPane);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");

        dialog.showAndWait();
    }


    private double processInput(TextField field, String fieldName) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for " + fieldName + ".");
            return 0.0;
        }
    }

    @FXML
    public void analyzeMuscleGroups() {
        double totalStrength = getTotalStrength();

        if (totalStrength == 0) {
            showAlert(Alert.AlertType.ERROR, "No Data", "Please update all muscle group strengths before analyzing.");
            return;
        }

        double quadPercent = (getStrength(quadStrength) / totalStrength) * 100;
        double hamPercent = (getStrength(hamStrength) / totalStrength) * 100;
        double calfPercent = (getStrength(calfStrength) / totalStrength) * 100;
        double glutePercent = (getStrength(gluteStrength) / totalStrength) * 100;

        String weakestMuscle = "Quadriceps";
        double minStrength = getStrength(quadStrength);

        if (getStrength(hamStrength) < minStrength) {
            weakestMuscle = "Hamstrings";
            minStrength = getStrength(hamStrength);
        }
        if (getStrength(calfStrength) < minStrength) {
            weakestMuscle = "Calves";
            minStrength = getStrength(calfStrength);
        }
        if (getStrength(gluteStrength) < minStrength) {
            weakestMuscle = "Glutes";
        }

        String exercises = getExercisesForMuscle(weakestMuscle);

        String result = String.format(
                "Percentage Strength:\n" +
                        "Quadriceps: %.2f%%\nHamstrings: %.2f%%\nCalves: %.2f%%\nGlutes: %.2f%%\n\n" +
                        "Weakest Muscle: %s\n\nRecommended Exercises:\n%s",
                quadPercent, hamPercent, calfPercent, glutePercent, weakestMuscle, exercises
        );

        showAlert(Alert.AlertType.INFORMATION, "Muscle Analysis Result", result);
    }

    private String getExercisesForMuscle(String muscle) {
        switch (muscle) {
            case "Quadriceps":
                return "- Squats\n- Lunges\n- Leg Press\n- Bulgarian Split Squats";
            case "Hamstrings":
                return "- Deadlifts\n- Romanian Deadlifts\n- Glute-Ham Raises\n- Nordic Curls";
            case "Calves":
                return "- Standing Calf Raises\n- Seated Calf Raises\n- Farmer's Walk on Toes\n- Jump Rope";
            case "Glutes":
                return "- Hip Thrusts\n- Glute Bridges\n- Step-Ups\n- Sumo Deadlifts";
            default:
                return "No exercises available.";
        }
    }

    private double getStrength(TextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double getTotalStrength() {
        return getStrength(quadStrength) + getStrength(hamStrength) +
                getStrength(calfStrength) + getStrength(gluteStrength);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        dialogPane.lookup(".button-bar").setStyle("-fx-alignment: center;");

        alert.showAndWait();
    }


    @FXML
    public void goBack() {
        System.out.println("Navigating back to the main page.");
    }
}
