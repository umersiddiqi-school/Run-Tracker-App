package org.example.capstone.service;

import java.util.prefs.Preferences;

public class UserSession {
    private static final UserSession instance = new UserSession();
    private String userName;
    private int userId;
    private final Preferences preferences;

    private UserSession() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
    }

    public static UserSession getInstance() {
        return instance;
    }

    public synchronized void login(String userName, int userId) {
        this.userId = userId;
        this.userName = userName;
        preferences.put("userName", userName);
        preferences.putInt("userId", userId); // Store userId in preferences
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId; // Provide a getter for userId
    }
}

