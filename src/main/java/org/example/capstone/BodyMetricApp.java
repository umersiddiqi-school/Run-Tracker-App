package org.example.capstone;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BodyMetricApp extends Application {

    private BorderPane mainLayout;
    private SidebarMenu sidebarMenu;
    private PageManager pageManager;
    private boolean sidebarVisible = true;

    @Override
    public void start(Stage primaryStage) {
        // Top Section (Personal Information)
        TopSection topSection = new TopSection("John Doe", 30);

        // Sidebar Menu
        sidebarMenu = new SidebarMenu(this);

        // Main Layout
        mainLayout = new BorderPane();
        mainLayout.setTop(topSection.getTopSection());
        mainLayout.setLeft(sidebarMenu.getSidebar());

        // Page Manager (Default Home Page)
        pageManager = new PageManager(mainLayout);
        pageManager.loadHomePage();

        // Sidebar Toggle Button
        Button toggleSidebarButton = new Button("☰");
        toggleSidebarButton.setStyle("-fx-font-size: fill;");
        toggleSidebarButton.setOnAction(e -> toggleSidebar());
        toggleSidebarButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;");

        toggleSidebarButton.setOnMouseEntered(e -> toggleSidebarButton.setStyle("-fx-background-color: #666; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));
        toggleSidebarButton.setOnMouseExited(e -> toggleSidebarButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 0;"));

        // Place Toggle Button on Top Left
        BorderPane.setAlignment(toggleSidebarButton, javafx.geometry.Pos.TOP_LEFT);
        mainLayout.setTop(topSection.getTopSection());
        mainLayout.setLeft(sidebarMenu.getSidebar());
        mainLayout.setTop(new BorderPane(topSection.getTopSection(), null, null, null, toggleSidebarButton));

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Run Tracker App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PageManager getPageManager() {
        return pageManager;
    }

    // Method to toggle sidebar visibility
    private void toggleSidebar() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebarMenu.getSidebar());

        if (sidebarVisible) {
            transition.setToX(-sidebarMenu.getSidebar().getWidth());
        } else {
            transition.setToX(0);
        }

        transition.play();
        sidebarVisible = !sidebarVisible;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
