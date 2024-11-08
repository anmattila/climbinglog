package project.climbinglog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotNull;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "routeid", unique = true)
    private Long routeid;
    @NotNull
    private Type type;
    private String grade;
    private int attempts;

    @ManyToOne
    @JsonIgnoreProperties("route")
    @JoinColumn(name = "workoutid")
    @NotNull
    private Workout workout;

    public Route() {
    }

    public Route(Type type, String grade, int attempts) {
        super();
        this.type = type;
        this.grade = grade;
        this.attempts = attempts;
    }

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "Route [routeid=" + routeid + ", type=" + type + ", grade=" + grade + ", attempts=" + attempts + "]";
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
