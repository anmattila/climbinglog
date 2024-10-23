package project.climbinglog.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long userid;
    private String name;
    private String username;
    private String passwordHash;
    private String role;

    // user can have many workouts
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Workout> workouts;
    
    public User() {
    }

    public User(String name, String username) {
        super();
        this.name = name;
        this.username = username;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userid=" + userid + ", name=" + name + ", username=" + username + ", passwordHash=" + passwordHash
                + ", role=" + role + ", workouts=" + workouts + "]";
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
    
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
     
}

