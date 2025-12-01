package com.empresa.curso.ProyectoGym.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empresa.curso.ProyectoGym.dto.StatsSummary;
import com.empresa.curso.ProyectoGym.model.DailyUserStats;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.repository.DailyUserStatsRepository;
import com.empresa.curso.ProyectoGym.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users/{userId}/stats")
public class StatsController {

    private final UserRepository userRepository;
    private final DailyUserStatsRepository dailyUserStatsRepository;

    public StatsController(UserRepository userRepository, DailyUserStatsRepository dailyUserStatsRepository) {
        this.userRepository = userRepository;
        this.dailyUserStatsRepository = dailyUserStatsRepository;
    }

    @GetMapping
    public String stats(@PathVariable Integer userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to, Model model,
            HttpSession session) {

        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null || !loggedUser.getId().equals(userId)) {
            return "access-denied";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado con id=" + userId));

        LocalDate today = LocalDate.now();

        if (from == null) {
            from = today.minusDays(30);
        }
        if (to == null) {
            to = today;
        }

        List<DailyUserStats> statsList = dailyUserStatsRepository.findByUserAndStatsDateBetweenOrderByStatsDate(user,
                from, to);

        int totalWorkouts = 0;
        int totalVolume = 0;
        int trainingDays = 0;

        for (DailyUserStats s : statsList) {

            // suma workoutsCount
            if (s.getWorkoutsCount() != null) {
                totalWorkouts += s.getWorkoutsCount();
                if (s.getWorkoutsCount() > 0) {
                    trainingDays++;
                }
            }

            // suma volumen del día
            if (s.getTotalVolume() != null) {
                totalVolume += s.getTotalVolume();
            }
        }

        double avgVolumePerWorkout;
        if (totalWorkouts == 0) {
            avgVolumePerWorkout = 0.0;
        } else {
            avgVolumePerWorkout = (double) totalVolume / totalWorkouts;
        }

        StatsSummary summary = new StatsSummary(
                totalWorkouts,
                totalVolume,
                avgVolumePerWorkout,
                trainingDays);

        model.addAttribute("user", loggedUser);
        model.addAttribute("globalStats", summary);
        model.addAttribute("dailyStats", statsList);
        model.addAttribute("from", from);
        model.addAttribute("to", to);

        return "stats";
    }

}
