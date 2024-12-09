package org.example.capstone.model;

public class RunningProgress {
    private int id;
    private int userId;
    private double distanceKm;
    private double timeMinutes;
    private int heartRate;  // New field for heart rate

    // Constructors
    public RunningProgress() { }

    public RunningProgress(int userId, double distanceKm, double timeMinutes, int heartRate) {
        this.userId = userId;
        this.distanceKm = distanceKm;
        this.timeMinutes = timeMinutes;
        this.heartRate = heartRate;
    }

    public RunningProgress(int id, int userId, double distanceKm, double timeMinutes, int heartRate) {
        this.id = id;
        this.userId = userId;
        this.distanceKm = distanceKm;
        this.timeMinutes = timeMinutes;
        this.heartRate = heartRate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public double getTimeMinutes() { return timeMinutes; }
    public void setTimeMinutes(double timeMinutes) { this.timeMinutes = timeMinutes; }

    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    @Override
    public String toString() {
        return "RunningProgress [id=" + id + ", userId=" + userId + ", distanceKm=" + distanceKm +
                ", timeMinutes=" + timeMinutes + ", heartRate=" + heartRate + "]";
    }
}
