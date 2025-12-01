package com.empresa.curso.ProyectoGym.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.curso.ProyectoGym.model.DailyUserStats;
import com.empresa.curso.ProyectoGym.model.User;

public interface DailyUserStatsRepository extends JpaRepository<DailyUserStats, Integer> {

    List<DailyUserStats> findByUserAndStatsDateBetweenOrderByStatsDate( User user, LocalDate start, LocalDate end);
}
