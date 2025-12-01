package com.empresa.curso.ProyectoGym.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DailyUserStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "stats_date")
    private LocalDate statsDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "workouts_count")
    private Integer workoutsCount;

    @Column(name = "total_volume")
    private Integer totalVolume;

    @Column(name = "muscle_groups")
    private String muscleGroups;

    public DailyUserStats() {
    }
    public DailyUserStats(Integer id, LocalDate statsDate, User user, Integer workoutsCount, Integer totalVolume,
            String muscleGroups) {
        this.id = id;
        this.statsDate = statsDate;
        this.user = user;
        this.workoutsCount = workoutsCount;
        this.totalVolume = totalVolume;
        this.muscleGroups = muscleGroups;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getStatsDate() {
        return statsDate;
    }
    public void setStatsDate(LocalDate statsDate) {
        this.statsDate = statsDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getWorkoutsCount() {
        return workoutsCount;
    }
    public void setWorkoutsCount(Integer workoutsCount) {
        this.workoutsCount = workoutsCount;
    }
    public Integer getTotalVolume() {
        return totalVolume;
    }
    public void setTotalVolume(Integer totalVolume) {
        this.totalVolume = totalVolume;
    }
    public String getMuscleGroups() {
        return muscleGroups;
    }
    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups = muscleGroups;
    }
}
