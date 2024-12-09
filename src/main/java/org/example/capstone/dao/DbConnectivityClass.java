package org.example.capstone.dao;

import org.example.capstone.model.RunningProgress;
import org.example.capstone.service.MyLogger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbConnectivityClass {
    final static String DB_NAME = "csc311capstone";
    public final static String DB_URL = "jdbc:mysql://csc311capstone.mysql.database.azure.com/" + DB_NAME;
    public final static String USERNAME = "vinserr";
    public final static String PASSWORD = "password123!";

    private static Connection connection;
    private final MyLogger lg = new MyLogger();

    public DbConnectivityClass() {
        connectToDatabase();
    }

    public boolean connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            try (Statement statement = connection.createStatement()) {
                String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "username VARCHAR(100) NOT NULL UNIQUE, "
                        + "password VARCHAR(200) NOT NULL, "
                        + "email VARCHAR(200) NOT NULL UNIQUE)";
                statement.executeUpdate(createUsersTable);
                String createBmiTable = "CREATE TABLE IF NOT EXISTS bmi ("
                        + "entry_num INT AUTO_INCREMENT PRIMARY KEY, "
                        + "user_id INT NOT NULL, "
                        + "height DECIMAL(5,2), "
                        + "weight DECIMAL(5,2), "
                        + "bmi_value DECIMAL(5,2), "
                        + "FOREIGN KEY (user_id) REFERENCES users(id))";
                statement.executeUpdate(createBmiTable);
                String createRunningProgressTable = "CREATE TABLE IF NOT EXISTS running_progress ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "user_id INT NOT NULL, "
                        + "distance_km DECIMAL(5,2), "
                        + "time_minutes DECIMAL(5,2), "
                        + "FOREIGN KEY (user_id) REFERENCES users(id))";
                statement.executeUpdate(createRunningProgressTable);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void insertBMI(int userId, double height, double weight, double bmiValue) {
        String getMaxEntryNumQuery = "SELECT MAX(entry_num) FROM bmi WHERE user_id = ?";
        String insertQuery = "INSERT INTO bmi (entry_num, user_id, height, weight, bmi_value) VALUES (?, ?, ?, ?, ?)";
        try {
            if (connection != null) {
                try (PreparedStatement getMaxStmt = connection.prepareStatement(getMaxEntryNumQuery)) {
                    getMaxStmt.setInt(1, userId);
                    ResultSet rs = getMaxStmt.executeQuery();
                    int nextEntryNum = 1;
                    if (rs.next()) {
                        nextEntryNum = rs.getInt(1) + 1;
                    }
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, nextEntryNum);
                        insertStmt.setInt(2, userId);
                        insertStmt.setDouble(3, height);
                        insertStmt.setDouble(4, weight);
                        insertStmt.setDouble(5, bmiValue);
                        insertStmt.executeUpdate();
                        System.out.println("BMI successfully inserted with entry_num: " + nextEntryNum);
                    }
                } catch (SQLException e) {
                    System.out.println("Error inserting BMI: " + e.getMessage());
                }
            } else {
                System.out.println("Database connection is closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertRun(RunningProgress progress) {
        String query = "INSERT INTO running_progress (user_id, distance_km, time_minutes, heart_rate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, progress.getUserId());
            stmt.setDouble(2, progress.getDistanceKm());
            stmt.setDouble(3, progress.getTimeMinutes());
            stmt.setInt(4, progress.getHeartRate());
            stmt.executeUpdate();
            System.out.println("Running Progress successfully inserted!");
        } catch (SQLException e) {
            System.out.println("Error saving Running Progress: " + e.getMessage());
        }
    }
    public static List<Double> getBMIHistory(int userId) {
        List<Double> bmiHistory = new ArrayList<>();
        String query = "SELECT bmi_value FROM bmi WHERE user_id = ? ORDER BY entry_num";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bmiHistory.add(rs.getDouble("bmi_value"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching BMI history: " + e.getMessage());
        }
        return bmiHistory;
    }
    public List<RunningProgress> getRunningProgressData(int userId) {
        List<RunningProgress> progressList = new ArrayList<>();
        String query = "SELECT * FROM running_progress WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double distance = resultSet.getDouble("distance_km");
                double time = resultSet.getDouble("time_minutes");
                int heartRate = resultSet.getInt("heart_rate");
                RunningProgress progress = new RunningProgress(id, userId, distance, time, heartRate);
                progressList.add(progress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressList;
    }
}