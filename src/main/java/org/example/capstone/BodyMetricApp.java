package org.example.capstone;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.capstone.service.UserSession;

public class BodyMetricApp extends Application {

    private BorderPane mainLayout;
    private SidebarMenu sidebarMenu;
    private PageManager pageManager;
    private boolean sidebarVisible = true;

    @Override
    public void start(Stage primaryStage) {
        UserSession userSession = UserSession.getInstance();
        String username = userSession.getUserName();
        int userId = userSession.getUserId();
        // Top Section (Personal Information)
        TopSection topSection = new TopSection(username);
        topSection.getTopSection().getStyleClass().add("top-section");
        // Sidebar Menu
        sidebarMenu = new SidebarMenu(this);
        sidebarMenu.getSidebar().getStyleClass().add("sidebar");
        // Main Layout
        mainLayout = new BorderPane();
        mainLayout.setTop(topSection.getTopSection());
        mainLayout.setLeft(sidebarMenu.getSidebar());
        // Page Manager (Default Home Page)
        pageManager = new PageManager(mainLayout, this, userSession);
        pageManager.loadHomePage();
        setTheme("light");
        // Sidebar Toggle Button
        Button toggleSidebarButton = new Button("☰");
        toggleSidebarButton.getStyleClass().add("toggle-sidebar-button");
        toggleSidebarButton.setOnAction(e -> toggleSidebar());
        // Place Toggle Button on Top Left
        BorderPane.setAlignment(toggleSidebarButton, javafx.geometry.Pos.TOP_LEFT);
        mainLayout.setTop(new BorderPane(topSection.getTopSection(), null, null, null, toggleSidebarButton));
        // Scene and Stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Run Tracker App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void setTheme(String theme){
        String themeFile = theme.equals("dark") ? "/night-mode.css" : "/day-mode.css";
        String themePath = getClass().getResource(themeFile).toExternalForm();
        mainLayout.getStylesheets().clear();
        mainLayout.getStylesheets().add(themePath);
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
