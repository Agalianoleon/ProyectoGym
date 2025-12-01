package com.empresa.curso.ProyectoGym.dto;

import java.time.LocalDate;


public class UserDailyAggregation {

    private Integer userId;
    private LocalDate fecha;
    private Integer workoutsCount;
    private Integer totalVolume;
    private String muscleGroups;

    public UserDailyAggregation(Integer userId, LocalDate fecha, Integer workoutsCount, Integer totalVolume,
            String muscleGroups) {
        this.userId = userId;
        this.fecha = fecha;
        this.workoutsCount = workoutsCount;
        this.totalVolume = totalVolume;
        this.muscleGroups = muscleGroups;
    }
    public UserDailyAggregation() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
