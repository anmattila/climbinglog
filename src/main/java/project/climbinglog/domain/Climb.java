package project.climbinglog.domain;

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

    private Long climbid;
    private String type;
    private String grade;
    private String place;
    
    @ManyToOne
    @JoinColumn(name = "workoutid")
    private Workout workout; 
    
    public Climb() {
    }
    
    public Climb(String type, String grade, String place) {
        super();
        this.type = type;
        this.grade = grade;
        this.place = place;
    }
    
    public void setClimbid(Long climbid) {
        this.climbid = climbid;
    }

    public Long getClimbid() {
        return climbid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Climb [climbid=" + climbid + ", type=" + type + ", grade=" + grade + ", place=" + place + "]";
    }

    public Workout getWorkouts() {
        return workout;
    }

    public void setWorkouts(Workout workouts) {
        this.workout = workouts;
    }
    
}
