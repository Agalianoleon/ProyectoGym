package com.empresa.curso.ProyectoGym.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.curso.ProyectoGym.model.Exercise;
@Repository// tecnicamente no es necesario poner esta anotacion porque JpaRepository ya la tiene
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    @Query("SELECT e FROM Routine r JOIN r.exercises e GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    List<Exercise> findMostPopularExercises(Pageable pageable); // ejemplo de metodo personalizado
}
