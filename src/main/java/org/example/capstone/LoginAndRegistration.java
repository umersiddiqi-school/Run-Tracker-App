package org.example.capstone;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.capstone.dao.DbConnectivityClass;
import org.example.capstone.dao.UserDao;
import org.example.capstone.model.User;
import org.example.capstone.service.UserSession;

import java.nio.file.Paths;
import java.sql.SQLException;

public class LoginAndRegistration extends Application {

    private DbConnectivityClass dbConnectivity;
    private UserDao userDao;
    private StackPane rootPane;
    private StackPane loginPanel;
    private VBox loginForm;
    private VBox registrationForm;

    /*
    Hey, I have no idea why it only works when you pull it into a project named "capstone".
    I don't know what changed, but one day it stopped working unless you did so.
     */

    @Override
    public void start(Stage primaryStage) throws SQLException {
        primaryStage.setTitle("RunnerUp!");

        // Initialize database connectivity
        dbConnectivity = new DbConnectivityClass();
        if (!dbConnectivity.connectToDatabase()) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to the database. Exiting...");
            System.exit(1);
        }
        userDao = new UserDao(dbConnectivity.getConnection());
        // Create root pane with background image
        rootPane = new StackPane();
        rootPane.setBackground(new Background(new BackgroundImage(
                new Image(Paths.get("src/main/resources/org/example/capstone/runnerPhoto.png").toUri().toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        )));
        loginForm = createLoginForm(primaryStage);
        registrationForm = createRegistrationForm();
        loginPanel = new StackPane(loginForm, registrationForm);
        loginPanel.setMaxWidth(300);
        loginPanel.setMaxHeight(400);
        loginPanel.setStyle("-fx-background-color: rgba(211, 211, 211, 0.9); -fx-background-radius: 20;");
        registrationForm.setVisible(false);

        rootPane.getChildren().add(loginPanel);

        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates the Login Form with a button to switch to the registration form.
     */
    private VBox createLoginForm(Stage primaryStage) {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER);
        form.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;");
        form.setMaxWidth(300);
        form.setMaxHeight(400);

        Label titleLabel = new Label("RunnerUP!");
        titleLabel.setStyle("-fx-font-size: 28; -fx-font-weight: bold;");

        TextField loginUsername = new TextField();
        loginUsername.setPromptText("Username");

        PasswordField loginPassword = new PasswordField();
        loginPassword.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = loginUsername.getText();
            String password = loginPassword.getText();
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in all fields.");
            } else {
                if (userDao.validateLogin(username, password)) {
                    // Fetch the user ID (you can modify validateLogin to return the user object if needed)
                    User user = userDao.getUserByUsername(username); // Assuming this method is available
                    if (user != null) {
                        // Create UserSession and save user details (username, userId)
                        UserSession.getInstance().login(user.getUsername(), user.getId());

                        // Redirect to BodyMetricApp
                        BodyMetricApp bodyMetricApp = new BodyMetricApp();
                        try {
                            bodyMetricApp.start(primaryStage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            showAlert(Alert.AlertType.ERROR, "Redirection Error", "Unable to load BodyMetricApp.");
                        }
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid username or password.");
                }
            }
        });


        Button switchToRegisterButton = new Button("Register");
        switchToRegisterButton.setOnAction(e -> showRegistrationForm());

        form.getChildren().addAll(titleLabel, loginUsername, loginPassword, loginButton, switchToRegisterButton);
        return form;
    }

    /**
     * Creates the Registration Form with a button to switch back to the login form.
     */
    private VBox createRegistrationForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER);
        form.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); -fx-background-radius: 20;");
        form.setMaxWidth(300);
        form.setMaxHeight(400);

        Label titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        TextField regUsername = new TextField();
        regUsername.setPromptText("Username");

        TextField regEmail = new TextField();
        regEmail.setPromptText("Email");

        PasswordField regPassword = new PasswordField();
        regPassword.setPromptText("Password");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String username = regUsername.getText();
            String email = regEmail.getText();
            String password = regPassword.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "All fields must be filled!");
            } else {
                // Create User without zip code
                User newUser = new User(username, password, email);
                if (userDao.registerUser(newUser)) {
                    showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Your account has been created!");
                    showLoginForm();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Registration Error", "Failed to create account. Try again!");
                }
            }
        });

        Button switchToLoginButton = new Button("Back to Login");
        switchToLoginButton.setOnAction(e -> showLoginForm());

        form.getChildren().addAll(titleLabel, regUsername, regEmail, regPassword, registerButton, switchToLoginButton);
        return form;
    }

    /**
     * Shows the registration form and blurs the login form.
     */
    private void showRegistrationForm() {
        loginForm.setEffect(new GaussianBlur(10));
        registrationForm.setVisible(true);
    }

    /**
     * Hides the registration form and removes the blur effect from the login form.
     */
    private void showLoginForm() {
        loginForm.setEffect(null);
        registrationForm.setVisible(false);
    }

    /**
     * Utility method to show alerts.
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
