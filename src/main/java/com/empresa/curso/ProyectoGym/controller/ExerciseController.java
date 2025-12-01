package com.empresa.curso.ProyectoGym.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.empresa.curso.ProyectoGym.service.ExerciseService;
import com.empresa.curso.ProyectoGym.model.Exercise;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
        public String ExerciseList(Model model) {
            
            List<Exercise> exercises = exerciseService.getAllExercises();
            Exercise newExercise = new Exercise();

            model.addAttribute("exerciseForm", newExercise);
            model.addAttribute("exercises", exercises);
            return "exercises";
        }
    @PostMapping //@ModelAttribute sirve para enlazar automaticamente los datos del formulario con el objeto 
    public String createExercise(@ModelAttribute("exercises") Exercise exercise) {
        exerciseService.saveExercise(exercise);
        return "redirect:/exercises"; //cuando se hace un redirect se vuelve a llamar la metodo que maneja esa ruta
    }

    @PostMapping ("/{id}/delete") //@pathvariable sirve para capturar el id que viene en la url
    public String deleteExercise(@PathVariable Integer id) {
        exerciseService.deleteExercise(id);
        return "redirect:/exercises";
    }
    
}
