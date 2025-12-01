package com.empresa.curso.ProyectoGym.batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.infrastructure.item.ItemReader;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

public class UserStatsReader implements ItemReader<User> {

    private final WorkoutRepository workoutRepository;
    private Iterator<User> iterator;

    public UserStatsReader(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public User read() {

        if (iterator == null) {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            LocalDateTime start = yesterday.atStartOfDay();
            LocalDateTime end = yesterday.atTime(LocalTime.MAX);

            List<User> users = workoutRepository.findUsersWithWorkoutsBetween(start, end);
            System.out.println(">> [Reader] Usuarios con entrenos ayer: " + users.size());

            iterator = users.iterator();
        }

        if (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println(">> [Reader] Leyendo usuario: " + user.getId());
            return user;
        } else {
            System.out.println(">> [Reader] No hay más usuarios, devolviendo null");
            return null; // Indica fin del stream
        }
    }
}
