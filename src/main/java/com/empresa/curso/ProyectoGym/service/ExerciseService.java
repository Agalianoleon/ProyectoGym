package com.empresa.curso.ProyectoGym.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.empresa.curso.ProyectoGym.model.Exercise;
import com.empresa.curso.ProyectoGym.repository.ExerciseRepository;

@Service
public class ExerciseService {
    //lo que hago aqui en realidad se puede hacer solo con el repositorio pero luego lo evolucionore
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Integer id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public void deleteExercise(Integer id) {
        exerciseRepository.deleteById(id);
    }

    public List<Exercise> getMostPopularExercises(int limit) {
        return exerciseRepository.findMostPopularExercises(PageRequest.of(0,limit));
    }
    
}
