package org.example.capstone;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.capstone.dao.DbConnectivityClass;
import org.example.capstone.dao.UserDao;
import org.example.capstone.model.User;

import java.sql.Connection;

public class LoginAndRegistration extends Application {

    private DbConnectivityClass dbConnectivity;
    private UserDao userDao;

    @Override
    public void start(Stage primaryStage) {
        // Initialize database connectivity
        dbConnectivity = new DbConnectivityClass();
        if (!dbConnectivity.connectToDatabase()) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to the database. Exiting...");
            System.exit(1);
        }

        // Initialize UserDao
        userDao = new UserDao(dbConnectivity.getConnection());

        // Set up the stage with the login scene as the default
        primaryStage.setTitle("Run Tracker App");
        primaryStage.setScene(createLoginScene(primaryStage));
        primaryStage.show();
    }

    /**
     * Creates the Login Scene
     */
    private Scene createLoginScene(Stage stage) {
        VBox loginForm = new VBox(10);
        loginForm.setPadding(new Insets(20));
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setStyle("-fx-background-color: #555;");

        Label loginTitle = new Label("Run Tracker App");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Button switchToRegisterButton = new Button("Create Account");

        // Login action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in both username and password.");
            } else {
                if (userDao.validateLogin(username, password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, " + username + "!");
                    stage.setScene(createDashboardScene(stage, username));
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid username or password.");
                }
            }
        });

        // Switch to Registration action
        switchToRegisterButton.setOnAction(e -> stage.setScene(createRegistrationScene(stage)));

        loginForm.getChildren().addAll(loginTitle, usernameField, passwordField, loginButton, switchToRegisterButton);
        return new Scene(loginForm, 300, 400);
    }

    /**
     * Creates the Registration Scene
     */
    private Scene createRegistrationScene(Stage stage) {
        VBox registrationForm = new VBox(10);
        registrationForm.setPadding(new Insets(20));
        registrationForm.setAlignment(Pos.CENTER);
        registrationForm.setStyle("-fx-background-color: #555;");

        Label registerTitle = new Label("Register");
        TextField regUsername = new TextField();
        regUsername.setPromptText("Username");
        TextField regEmail = new TextField();
        regEmail.setPromptText("Email");
        PasswordField regPassword = new PasswordField();
        regPassword.setPromptText("Password");
        TextField regZipCode = new TextField();
        regZipCode.setPromptText("Zip Code");

        Button registerButton = new Button("Register");
        Button switchToLoginButton = new Button("Back to Login");

        // Registration action
        registerButton.setOnAction(e -> {
            String username = regUsername.getText();
            String email = regEmail.getText();
            String password = regPassword.getText();
            String zipCode = regZipCode.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || zipCode.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "All fields must be filled!");
            } else {
                // Create the new user object
                User newUser = new User(username, password, email, zipCode);

                // Get the database connection
                DbConnectivityClass dbConnectivity = new DbConnectivityClass();
                Connection connection = dbConnectivity.getConnection();

                if (connection != null) {
                    // Create UserDao with the connection
                    UserDao userDao = new UserDao(connection);

                    // Register user
                    if (userDao.registerUser(newUser)) {
                        showAlert(Alert.AlertType.INFORMATION, "Registration Success", "Account created successfully!");
                        stage.setScene(createLoginScene(stage)); // Switch back to login scene after successful registration
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Registration Error", "Failed to create account. Try again!");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to the database.");
                }
            }
        });


        // Switch to Login action
        switchToLoginButton.setOnAction(e -> stage.setScene(createLoginScene(stage)));

        registrationForm.getChildren().addAll(registerTitle, regUsername, regEmail, regPassword, regZipCode, registerButton, switchToLoginButton);
        return new Scene(registrationForm, 300, 400);
    }

    /**
     * Creates the Dashboard Scene
     */
    private Scene createDashboardScene(Stage stage, String username) {
        VBox dashboard = new VBox(20);
        dashboard.setAlignment(Pos.CENTER);
        dashboard.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Button logoutButton = new Button("Logout");

        // Logout action
        logoutButton.setOnAction(e -> stage.setScene(createLoginScene(stage)));

        dashboard.getChildren().addAll(welcomeLabel, logoutButton);
        return new Scene(dashboard, 300, 400);
    }

    /**
     * Utility method to show alerts
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
