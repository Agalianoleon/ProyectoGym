package com.empresa.curso.ProyectoGym.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.curso.ProyectoGym.dto.RoutineForm;
import com.empresa.curso.ProyectoGym.model.Routine;
import com.empresa.curso.ProyectoGym.service.ExerciseService;
import com.empresa.curso.ProyectoGym.service.RoutineSerivce;

@Controller
@RequestMapping("/routines")
public class RoutineController {
    private final RoutineSerivce routineService;
    private final ExerciseService exerciseService;

    public RoutineController(RoutineSerivce routineService, ExerciseService exerciseService) {
        this.routineService = routineService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public String RoutineList(Model model) {
        
        List<Routine> routines = routineService.getAllRoutines();
        model.addAttribute("routines", routines);
        return "routines";
    }

    @GetMapping("/new")
    public String showCreateRoutineForm(Model model) {
        RoutineForm routineForm = new RoutineForm();
        model.addAttribute("routineForm", routineForm);
        model.addAttribute("exercises", exerciseService.getAllExercises());
        model.addAttribute("popularExercises", exerciseService.getMostPopularExercises(5));
        return "createroutine";
    }

    @PostMapping
    public String createRoutine(@ModelAttribute("RoutineForm") RoutineForm routineForm) {
        routineService.createRoutine(routineForm.getNombre(), routineForm.getExerciseIds());
        return "redirect:/routines";
    }

}
