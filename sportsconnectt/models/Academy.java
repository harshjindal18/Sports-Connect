package com.example.sportsconnectt.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private int id;

    private String name;
    private String sportsCategory;

    private double latitude;
    private double longitude;

    @ElementCollection // Handles lists in JPA
    private List<String> availableSlots;

    // No-argument constructor (required by JPA)
    public Academy() {
    }

    // Constructor with arguments
    public Academy(int id, String name, String sportsCategory, double latitude, double longitude, List<String> availableSlots) {
        this.id = id;
        this.name = name;
        this.sportsCategory = sportsCategory;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableSlots = availableSlots;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportsCategory() {
        return sportsCategory;
    }

    public void setSportsCategory(String sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
