package project.climbinglog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Climb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "climbid", unique = true)
    private Long climbid;
    private Type type;
    private String grade;
    private String notes;

    @ManyToOne
    @JsonIgnoreProperties("climb")
    @JoinColumn(name = "workoutid")
    // nullable = false saa kaatumaan
    private Workout workout;

    public Climb() {
    }

    public Climb(Type type, String grade, String notes) {
        super();
        this.type = type;
        this.grade = grade;
        this.notes = notes;
    }

    public void setClimbid(Long climbid) {
        this.climbid = climbid;
    }

    public Long getClimbid() {
        return climbid;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Climb [climbid=" + climbid + ", type=" + type + ", grade=" + grade + ", notes=" + notes + "]";
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
