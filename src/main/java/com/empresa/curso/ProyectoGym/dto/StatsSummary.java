package com.empresa.curso.ProyectoGym.dto;

public class StatsSummary {

    private int totalWorkouts;
    private int totalVolume;
    private double volumePerWorkout;
    private int trainingDays;

    public StatsSummary(int totalWorkouts, int totalVolume, double volumePerWorkout, int trainingDays) {
        this.totalWorkouts = totalWorkouts;
        this.totalVolume = totalVolume;
        this.volumePerWorkout = volumePerWorkout;
        this.trainingDays = trainingDays;
    }

    public StatsSummary() {}

    public int getTotalWorkouts() {
        return totalWorkouts;
    }
    public void setTotalWorkouts(int totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }
    public int getTotalVolume() {
        return totalVolume;
    }
    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }
    public double getVolumePerWorkout() {
        return volumePerWorkout;
    }
    public void setVolumePerWorkout(double volumePerWorkout) {
        this.volumePerWorkout = volumePerWorkout;
    }
    public int getTrainingDays() {
        return trainingDays;
    }
    public void setTrainingDays(int trainingDays) {
        this.trainingDays = trainingDays;
    }

}
