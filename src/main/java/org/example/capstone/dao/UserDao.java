package org.example.capstone.dao;

import org.example.capstone.model.User;

import java.sql.*;

public class UserDao {
    private Connection connection;

    // Constructor that accepts a Connection
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    // Method to validate login credentials
    public boolean validateLogin(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // True if user exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a new user
    public boolean registerUser(User newUser) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPassword());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Get the generated ID for the new user
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newUser.setId(generatedKeys.getInt(1)); // Set the ID for the new user object
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add a method to get a user by username in UserDao
    public User getUserByUsername(String username) {
        String query = "SELECT id, username, email FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    return new User(username, "", email, userId); // Assuming User constructor can handle this
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
