package org.example.capstone.model;

public class User {
    private String username;
    private String password;
    private String email;
    private int id;
    // Constructor without id, for registration
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = -1;
    }
    public User(String username, String password, String email, int id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }
    // Getters
    public String getUsername() {
        return username;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setId(int id) {
        this.id = id;
    }
}
