package com.empresa.curso.ProyectoGym.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToMany
    @JoinTable(name = "routine_exercise",
        joinColumns = @JoinColumn(name = "routine_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<Exercise> exercises = new ArrayList<>();

    public Routine() {
    }

    public Routine(Integer id, String nombre, List<Exercise> exercises) {
        this.id = id;
        this.nombre = nombre;
        this.exercises = exercises;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Exercise> getExercises() {
        return exercises;
    }
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
    @Override
    public String toString() {
        return "Routine [id=" + id + ", nombre=" + nombre + ", exercises=" + exercises + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Routine other = (Routine) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    
}
