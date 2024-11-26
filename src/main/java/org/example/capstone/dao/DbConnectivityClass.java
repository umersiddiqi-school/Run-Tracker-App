package org.example.capstone.dao;

import org.example.capstone.service.MyLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectivityClass {
    final static String DB_NAME = "csc311capstone";
    public final static String DB_URL = "jdbc:mysql://csc311capstone.mysql.database.azure.com/" + DB_NAME;
    public final static String USERNAME = "vinserr";
    public final static String PASSWORD = "password123!";

    private Connection connection; // Add this field
    private final MyLogger lg = new MyLogger();

    public DbConnectivityClass() {
        connectToDatabase();
    }

    public boolean connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Create tables if they don't exist
            try (Statement statement = connection.createStatement()) {
                String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "username VARCHAR(100) NOT NULL UNIQUE, "
                        + "password VARCHAR(200) NOT NULL, "
                        + "email VARCHAR(200) NOT NULL UNIQUE)";
                statement.executeUpdate(createUsersTable);

                String createBmiTable = "CREATE TABLE IF NOT EXISTS bmi ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
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
                        + "date DATE, "
                        + "FOREIGN KEY (user_id) REFERENCES users(id))";
                statement.executeUpdate(createRunningProgressTable);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Provide access to the connection
    public Connection getConnection() {
        return connection;
    }

    // Close connection when done
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
