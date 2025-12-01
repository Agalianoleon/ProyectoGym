package com.empresa.curso.ProyectoGym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.empresa.curso.ProyectoGym.model.Exercise;
import com.empresa.curso.ProyectoGym.model.Routine;
import com.empresa.curso.ProyectoGym.repository.ExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.RoutineRepository;

@Service
public class RoutineSerivce {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;

    public RoutineSerivce(RoutineRepository routineRepository, ExerciseRepository exerciseRepository) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    public Routine createRoutine(String nombre, List<Integer> exerciseIds) {
        Routine routine = new Routine();
        routine.setNombre(nombre);
        List<Exercise> exercises = exerciseRepository.findAllById(exerciseIds);
        routine.setExercises(exercises);
        return routineRepository.save(routine);
    }
    

}
