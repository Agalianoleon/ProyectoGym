package com.empresa.curso.ProyectoGym.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_time")
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    public Workout() {
    }
    public Workout(Integer id, User user, LocalDateTime fecha, Routine routine) {
        this.id = id;
        this.user = user;
        this.fecha = fecha;
        this.routine = routine;
        //this.routineId = routineId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Routine getRoutine() {
        return routine;
    }
    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

}
