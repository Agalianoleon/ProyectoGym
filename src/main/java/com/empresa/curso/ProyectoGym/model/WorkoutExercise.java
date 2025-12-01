package com.empresa.curso.ProyectoGym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer series;

    private Integer reps;
    @Column(name = "peso")
    private Double weight;

    public WorkoutExercise() {
    }
    public WorkoutExercise(Integer id, Workout workout, Exercise exercise, Integer series, Integer reps, Double weight) {
        this.id = id;
        this.workout = workout;
        this.exercise = exercise;
        this.series = series;
        this.reps = reps;
        this.weight = weight;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
    public Exercise getExercise() {
        return exercise;
    }
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
    public Integer getSeries() {
        return series;
    }
    public void setSeries(Integer series) {
        this.series = series;
    }
    public Integer getReps() {
        return reps;
    }
    public void setReps(Integer reps) {
        this.reps = reps;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
