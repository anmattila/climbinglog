package project.climbinglog.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String passwordHash;
    
    private String role;

    // user can have many workouts
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    //@JsonIgnoreProperties("workouts")
    //private List<Workout> workouts;
    
    public AppUser() {
    }

    public AppUser(String username, String passwordhash) {
        super();
        this.username = username;
        this.passwordHash = passwordhash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

/*  @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash
                + ", role=" + role + "]";
    }
    public List<Workout> getWorkouts() {
        return workouts;
    }
    
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
*/
     
}

