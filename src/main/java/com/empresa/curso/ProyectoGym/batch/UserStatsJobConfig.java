package com.empresa.curso.ProyectoGym.batch;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.empresa.curso.ProyectoGym.model.DailyUserStats;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.repository.WorkoutExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class UserStatsJobConfig {

   // 1) Reader
    @Bean
    public ItemReader<User> userStatsReader(WorkoutRepository workoutRepository) {
        return new UserStatsReader(workoutRepository);
    }

    // 2) Processor
    @Bean
    public ItemProcessor<User, DailyUserStats> userStatsProcessor(
            WorkoutRepository workoutRepository,
            WorkoutExerciseRepository workoutExerciseRepository) {

        return new UserStatsProcessor(workoutRepository, workoutExerciseRepository);
    }

    // 3) Writer
    @Bean
    public ItemWriter<DailyUserStats> userStatsWriter(EntityManagerFactory emf) {
        return new JpaItemWriter<>(emf);
    }

    // 4) Step
    @Bean
public Step userStatsStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          ItemReader<User> userStatsReader,
                          ItemProcessor<User, DailyUserStats> userStatsProcessor,
                          ItemWriter<DailyUserStats> userStatsWriter) {

    return new StepBuilder("userStatsStep", jobRepository)
            .<User, DailyUserStats>chunk(5)               
            .reader(userStatsReader)
            .processor(userStatsProcessor)
            .writer(userStatsWriter)
            .transactionManager(transactionManager)      
            .build();
}

    // 5) Job
    @Bean
    public Job userStatsJob(JobRepository jobRepository, Step userStatsStep) {
        return new JobBuilder("userStatsJob", jobRepository).start(userStatsStep)
                                                                  .build();
    }
}

