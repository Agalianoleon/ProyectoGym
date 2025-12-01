package com.empresa.curso.ProyectoGym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    
    @Column(name = "grupo_muscular")
    private String grupoMuscular;

    private String tipo;
    
    //el constructor vacio es necesario para JPA porque crea instancias de la clase mediante reflexion
    public Exercise() {
    }

    public Exercise(Integer id, String nombre, String grupoMuscular, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.grupoMuscular = grupoMuscular;
        this.tipo = tipo;
    }
    //getters y setters
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
    
    public String getGrupoMuscular() {
        return grupoMuscular;
    }
    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
