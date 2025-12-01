package com.empresa.curso.ProyectoGym.batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.batch.infrastructure.item.ItemProcessor;

import com.empresa.curso.ProyectoGym.model.DailyUserStats;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.model.Workout;
import com.empresa.curso.ProyectoGym.model.WorkoutExercise;
import com.empresa.curso.ProyectoGym.repository.WorkoutExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

public class UserStatsProcessor implements ItemProcessor<User, DailyUserStats> {

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public UserStatsProcessor(WorkoutRepository workoutRepository,
                              WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @Override
    public DailyUserStats process(User user) {
        System.out.println(">> [Processor] Calculando stats para usuario: " + user.getId());

        LocalDate statsDate = LocalDate.now().minusDays(1);
        LocalDateTime start = statsDate.atStartOfDay();
        LocalDateTime end = statsDate.atTime(LocalTime.MAX);

        // 1) Workouts de este usuario ayer
        List<Workout> workouts =workoutRepository.findByUserAndFechaBetween(user, start, end);

        int workoutsCount = workouts.size();
        int totalVolume = 0;
        Set<String> muscleGroupsSet = new HashSet<>();

        // 2) Recorremos cada workout y sus ejercicios
        for (Workout workout : workouts) {
            List<WorkoutExercise> exercises =
                    workoutExerciseRepository.findByWorkout(workout);

            for (WorkoutExercise we : exercises) {
                if (we.getSeries() != null &&
                    we.getReps() != null &&
                    we.getWeight() != null) {

                    double volume = we.getSeries() * we.getReps() * we.getWeight();
                    totalVolume += (int) volume; // puedes dejarlo como int
                }

                if (we.getExercise() != null &&
                    we.getExercise().getGrupoMuscular() != null) {

                    muscleGroupsSet.add(we.getExercise().getGrupoMuscular());
                }
            }
        }

        // 3) Creamos la entidad DailyUserStats
        DailyUserStats stats = new DailyUserStats();
        stats.setUser(user);
        stats.setStatsDate(statsDate);
        stats.setWorkoutsCount(workoutsCount);
        stats.setTotalVolume(totalVolume);

        // Unimos los grupos musculares en una string tipo "Pecho, Espalda, Pierna"
        if (muscleGroupsSet.isEmpty()) {
            stats.setMuscleGroups("");
        } else {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String g : muscleGroupsSet) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(g);
                first = false;
            }
            stats.setMuscleGroups(sb.toString());
        }

        System.out.println(">> [Processor] Usuario " + user.getId()
                + " - workouts=" + workoutsCount
                + " volumen=" + totalVolume
                + " grupos=" + stats.getMuscleGroups());

        return stats;
    }
}