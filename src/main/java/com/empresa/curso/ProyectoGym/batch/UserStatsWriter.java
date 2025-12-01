package com.empresa.curso.ProyectoGym.batch;

import org.springframework.batch.infrastructure.item.database.JpaItemWriter;

import com.empresa.curso.ProyectoGym.model.DailyUserStats;

import jakarta.persistence.EntityManagerFactory;



public class UserStatsWriter extends JpaItemWriter<DailyUserStats> {

    public UserStatsWriter(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
        //TODO Auto-generated constructor stub
    }
    

}
