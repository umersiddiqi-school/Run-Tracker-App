package org.example.capstone.model;

import java.time.LocalDate;

public class RunningProgress {
    private int id;
    private int userId;
    private double distanceKm;
    private double timeMinutes;
    private LocalDate date;

    // Constructors
    public RunningProgress() { }

    public RunningProgress(int userId, double distanceKm, double timeMinutes, LocalDate date) {
        this.userId = userId;
        this.distanceKm = distanceKm;
        this.timeMinutes = timeMinutes;
        this.date = date;
    }

    public RunningProgress(int id, int userId, double distanceKm, double timeMinutes, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.distanceKm = distanceKm;
        this.timeMinutes = timeMinutes;
        this.date = date;
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

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "RunningProgress [id=" + id + ", userId=" + userId + ", distanceKm=" + distanceKm + ", timeMinutes=" + timeMinutes + ", date=" + date + "]";
    }
}