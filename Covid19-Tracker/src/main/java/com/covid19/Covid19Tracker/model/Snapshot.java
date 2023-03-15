package com.covid19.Covid19Tracker.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "snapshots")
public class Snapshot {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(nullable = false)
    private String countryName;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private int totalCases;
    @Column(nullable = false)
    private int totalDeaths;
    @Column(nullable = false)
    private int newCases;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(Snapshot newSnapshot) {
        this.countryName = newSnapshot.countryName;
        this.date = newSnapshot.date;
        this.totalCases = newSnapshot.totalCases;
        this.totalDeaths = newSnapshot.totalDeaths;
        this.newCases = newSnapshot.newCases;
    }
}
