package com.empresa.curso.ProyectoGym.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.model.Workout;
import com.empresa.curso.ProyectoGym.model.WorkoutExercise;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Integer> {

    @Query("SELECT DISTINCT e.grupoMuscular FROM WorkoutExercise we JOIN we.workout w JOIN we.exercise e WHERE w.user = :user  AND w.fecha BETWEEN :start AND :end")
    List<String> findDistinctMuscleGroupsForUserAndDate(
            @Param("user") User user,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    List<WorkoutExercise> findByWorkout(Workout workout);

}
