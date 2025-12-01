package com.empresa.curso.ProyectoGym.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.empresa.curso.ProyectoGym.model.Workout;
import com.empresa.curso.ProyectoGym.model.WorkoutExercise;
import com.empresa.curso.ProyectoGym.repository.WorkoutExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

@Controller
@RequestMapping("/workouts")
public class WorkoutDetailController {
    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutDetailController(WorkoutRepository workoutRepository,
                                   WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<WorkoutExercise> exercises = workoutExerciseRepository.findByWorkout(workout);

        model.addAttribute("workout", workout);
        model.addAttribute("exercises", exercises);

        return "workout-detail"; 
    }
}
