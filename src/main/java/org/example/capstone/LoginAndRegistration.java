package org.example.capstone;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginAndRegistration extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Login Form layout
        VBox loginForm = new VBox(10);
        loginForm.setPadding(new Insets(20));
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setStyle("-fx-background-color: #555;");



        Label loginTitle = new Label("Run Tracker App");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        usernameField.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");
        usernameField.setOnMouseEntered(e -> usernameField.setStyle("-fx-background-color: #666; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
        usernameField.setOnMouseExited(e -> usernameField.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        passwordField.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");

        passwordField.setOnMouseEntered(e -> passwordField.setStyle("-fx-background-color: #666; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
        passwordField.setOnMouseExited(e -> passwordField.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));

        Button loginButton = new Button("Login");
        Button switchToRegisterButton = new Button("Create Account");

        // Add components to login form
        loginForm.getChildren().addAll(loginTitle, usernameField, passwordField, loginButton, switchToRegisterButton);

        // Registration Form layout (initially hidden)
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

        // Add components to registration form
        registrationForm.getChildren().addAll(registerTitle, regUsername, regEmail, regPassword, regZipCode, registerButton, switchToLoginButton);

        // StackPane to switch between forms
        StackPane root = new StackPane();
        root.getChildren().addAll(loginForm, registrationForm);
        registrationForm.setVisible(false); // Hide registration form initially

        // Button actions to switch forms
        switchToRegisterButton.setOnAction(e -> {
            loginForm.setVisible(false);
            registrationForm.setVisible(true);
        });

        switchToLoginButton.setOnAction(e -> {
            registrationForm.setVisible(false);
            loginForm.setVisible(true);
        });

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

