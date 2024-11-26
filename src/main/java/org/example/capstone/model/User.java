package org.example.capstone.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String zipCode; // Added field for zip code

    // Default Constructor
    public User() { }

    // Constructor without ID
    public User(String username, String password, String email, String zipCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.zipCode = zipCode;
    }

    // Constructor with ID
    public User(int id, String username, String password, String email, String zipCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.zipCode = zipCode;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getZipCode() { return zipCode; } // Getter for zip code
    public void setZipCode(String zipCode) { this.zipCode = zipCode; } // Setter for zip code

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", zipCode=" + zipCode + "]";
    }
}
