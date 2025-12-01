package com.empresa.curso.ProyectoGym.dto;
//los dto son objetos simples que sirven para transferir datos entre capas de la aplicacion

import java.util.List;

public class RoutineForm {
 
    private String nombre;
    private List<Integer> exerciseIds;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Integer> getExerciseIds() {
        return exerciseIds;
    }
    public void setExerciseIds(List<Integer> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }

}
