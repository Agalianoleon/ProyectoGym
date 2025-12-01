package com.empresa.curso.ProyectoGym.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.curso.ProyectoGym.dto.WorkoutSummary;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.model.Workout;
import com.empresa.curso.ProyectoGym.model.WorkoutExercise;
import com.empresa.curso.ProyectoGym.repository.UserRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/users/{userId}/workouts")
public class WorkoutController {
    
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutController(UserRepository userRepository,
                             WorkoutRepository workoutRepository,
                             WorkoutExerciseRepository workoutExerciseRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @GetMapping
    public String list(@PathVariable Integer userId, Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null || !loggedUser.getId().equals(userId)) {
            return "access-denied";
        }
        // Usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado con id=" + userId));

        // Todos los workouts de ese usuario (ordenados por fecha desc)
        List<Workout> workouts = workoutRepository.findByUserOrderByFechaDesc(user);

        List<WorkoutSummary> summaries = new ArrayList<>();

        for (Workout workout : workouts) {

            List<WorkoutExercise> exercises = workoutExerciseRepository.findByWorkout(workout);

            int exerciseCount = exercises.size();
            double totalVolume = 0.0;

            for (WorkoutExercise we : exercises) {
                if (we.getSeries() != null && we.getReps() != null && we.getWeight() != null) {
                    totalVolume += we.getSeries() * we.getReps() * we.getWeight();
                }
            }

            String routineName;
            if (workout.getRoutine() != null && workout.getRoutine().getNombre() != null) {
                routineName = workout.getRoutine().getNombre();
            } else {
                routineName = "Sin rutina";
            }

            WorkoutSummary summary = new WorkoutSummary(
                    workout.getId(),
                    workout.getFecha(),
                    routineName,
                    exerciseCount,
                    totalVolume
            );

            summaries.add(summary);
        }

        model.addAttribute("user", loggedUser);
        model.addAttribute("workouts", summaries);

        return "workouts";
    }
}
