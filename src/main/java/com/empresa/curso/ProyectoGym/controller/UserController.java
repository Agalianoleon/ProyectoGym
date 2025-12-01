package com.empresa.curso.ProyectoGym.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.curso.ProyectoGym.dto.WorkoutSummary;
import com.empresa.curso.ProyectoGym.model.DailyUserStats;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.model.Workout;
import com.empresa.curso.ProyectoGym.model.WorkoutExercise;
import com.empresa.curso.ProyectoGym.repository.DailyUserStatsRepository;
import com.empresa.curso.ProyectoGym.repository.UserRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutExerciseRepository;
import com.empresa.curso.ProyectoGym.repository.WorkoutRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final DailyUserStatsRepository dailyUserStatsRepository;

    public UserController(UserRepository userRepository, WorkoutRepository workoutRepository,
                          WorkoutExerciseRepository workoutExerciseRepository, DailyUserStatsRepository dailyUserStatsRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.dailyUserStatsRepository = dailyUserStatsRepository;
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Integer id, Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null || !loggedUser.getId().equals(id)) {
            return "access-denied";
        }
        System.out.println("Login ok.Session id: " + session.getId());
        // ---- Usuario ----
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado con id=" + id));

        model.addAttribute("user", loggedUser);

        // ---- Últimos 5 entrenos (sin streams) ----
        List<Workout> lastWorkouts = workoutRepository.findTop5ByUserOrderByFechaDesc(user);

        List<WorkoutSummary> recentWorkouts = new ArrayList<>();

        for (Workout workout : lastWorkouts) {

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
                    totalVolume);

            recentWorkouts.add(summary);
        }

        model.addAttribute("recentWorkouts", recentWorkouts);

        // ---- Stats rápidas (últimos 7 días, mes actual), también sin streams ----

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);
        LocalDate firstOfMonth = today.withDayOfMonth(1);

        List<DailyUserStats> last7Stats = dailyUserStatsRepository.findByUserAndStatsDateBetweenOrderByStatsDate(user,
                sevenDaysAgo, today);

        int last7Workouts = 0;
        int last7Volume = 0;

        for (DailyUserStats s : last7Stats) {
            if (s.getWorkoutsCount() != null) {
                last7Workouts += s.getWorkoutsCount();
            }
            if (s.getTotalVolume() != null) {
                last7Volume += s.getTotalVolume();
            }
        }

        List<DailyUserStats> thisMonthStats = dailyUserStatsRepository
                .findByUserAndStatsDateBetweenOrderByStatsDate(user, firstOfMonth, today);

        long trainedDaysThisMonth = 0;
        for (DailyUserStats s : thisMonthStats) {
            if (s.getWorkoutsCount() != null && s.getWorkoutsCount() > 0) {
                trainedDaysThisMonth++;
            }
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("last7DaysWorkouts", last7Workouts);
        stats.put("totalVolumeLast7Days", last7Volume);
        stats.put("trainedDaysThisMonth", trainedDaysThisMonth);

        model.addAttribute("stats", stats);
        return "user";
    }
}
