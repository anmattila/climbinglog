package project.climbinglog.domain;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(unique = true)
    private Long workoutid;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date")
    @NotNull(message = "Select day")
    private LocalDate date;

    @Size(min=3, max=30, message = "Write location")
    private String location;
    private String place;
    private String notes;

    // 1 workout has only 1 user, userid foreign key
    @ManyToOne 
    @JsonIgnoreProperties("workouts")
    @JoinColumn(name = "userid") 
    private AppUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    @JsonIgnoreProperties("workout")
    private List<Route> routes = new ArrayList<>();

    public Workout() {
    }

    public Workout(LocalDate date, String location, String place, String notes) {
        super();
        this.date = date;
        this.location = location;
        this.place = place;
        this.notes = notes;
    }

    public Long getWorkoutid() {
        return workoutid;
    }

    public void setWorkoutid(Long workoutid) {
        this.workoutid = workoutid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Workout [workoutid=" + workoutid + ", date=" + date + ", location=" + location + ", place=" + place + ", notes=" + notes + "]";
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    // varmistaa että kiipeilty reitti lisätään treenissä olevaan listaan kiipeilystä 
    // ja reitti tietää mihin treeniin se kuuluu
    public void addRoute(Route route) {
        routes.add(route);              
        route.setWorkout(this); 

    } 
}
