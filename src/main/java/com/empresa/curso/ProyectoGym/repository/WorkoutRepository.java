package com.empresa.curso.ProyectoGym.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    @Query(" SELECT DISTINCT w.user FROM Workout w WHERE w.fecha BETWEEN :start AND :end")
    List<User> findUsersWithWorkoutsBetween(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
    List<Workout> findByUserOrderByFechaDesc(User user);
    List<Workout> findTop5ByUserOrderByFechaDesc(User user);
    List<Workout> findByUserAndFechaBetween(User user,LocalDateTime start,LocalDateTime end);
}

