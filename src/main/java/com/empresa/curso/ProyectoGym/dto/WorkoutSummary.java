package com.empresa.curso.ProyectoGym.dto;

import java.time.LocalDateTime;

public class WorkoutSummary {
        
        private Integer id;
        private LocalDateTime fecha;
        private String routineName;
        private int exerciseCount;
        private double totalVolume;

        public WorkoutSummary(Integer id, LocalDateTime fecha, String routineName, int exerciseCount,
                        double totalVolume) {
                this.id = id;
                this.fecha = fecha;
                this.routineName = routineName;
                this.exerciseCount = exerciseCount;
                this.totalVolume = totalVolume;
        }

        public WorkoutSummary() {
        }

        public Integer getId() {
                return id;
        }
        public void setId(Integer id) {
                this.id = id;
        }
        public LocalDateTime getFecha() {
                return fecha;
        }
        public void setFecha(LocalDateTime fecha) {
                this.fecha = fecha;
        }
        public String getRoutineName() {
                return routineName;
        }
        public void setRoutineName(String routineName) {
                this.routineName = routineName;
        }
        public int getExerciseCount() {
                return exerciseCount;
        }
        public void setExerciseCount(int exerciseCount) {
                this.exerciseCount = exerciseCount;
        }
        public double getTotalVolume() {
                return totalVolume;
        }
        public void setTotalVolume(double totalVolume) {
                this.totalVolume = totalVolume;
        }

    
}
