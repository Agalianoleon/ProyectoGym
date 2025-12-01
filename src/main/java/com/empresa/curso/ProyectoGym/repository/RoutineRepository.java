package com.empresa.curso.ProyectoGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.curso.ProyectoGym.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {

}
