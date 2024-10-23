package project.climbinglog.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DateTimeFormat(pattern = "dd.MM.yyyy")

    private Long workoutid;
    private Date date;
    private String location;
    private String notes;


    // 1 workout has only 1 user, userid foreign key
    @ManyToOne 
    @JoinColumn(name = "userid") 
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    private List<Climb> climbs;

    public Workout() {
    }

    public Workout(Date date, String location, String notes) {
        super();
        this.date = date;
        this.location = location;
        this.notes = notes;
    }

    public Long getWorkoutid() {
        return workoutid;
    }

    public void setWorkoutid(Long workoutid) {
        this.workoutid = workoutid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Workout [workoutid=" + workoutid + ", date=" + date + ", location=" + location + ", notes=" + notes + "]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Climb> getClimbs() {
        return climbs;
    }

    public void setClimbs(List<Climb> climbs) {
        this.climbs = climbs;
    }

}
