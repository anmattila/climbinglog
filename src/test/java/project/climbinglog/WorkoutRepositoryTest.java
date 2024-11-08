package project.climbinglog;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@SpringBootTest
public class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepo;
    @Autowired
    private AppUserRepository userRepo;

    private Workout workout;

    @BeforeEach
    public void setUp() {
        AppUser testuser = new AppUser("name", "word", "USER");
        userRepo.save(testuser);
        workout = new Workout(LocalDate.of(2024, 01, 01), "Helsinki", "outdoors", "good", testuser);
        workoutRepo.save(workout);
    }
    
    @AfterEach
    public void cleanUp() {
        workoutRepo.deleteAll();
        userRepo.deleteAll();
    }
    
    @Test 
    public void addNewWorkout() {
        assertThat(workout.getWorkoutid()).isNotNull();
    }

    @Test
    public void deleteWorkout() {
        workoutRepo.deleteById(workout.getWorkoutid());
        assertThat(workoutRepo.findById(workout.getWorkoutid())).isEmpty();
    }

    @Test
    public void editWorkout() {
        Workout workoutEdit = workoutRepo.findById(workout.getWorkoutid()).orElseThrow();
        workoutEdit.setLocation("BK Pasila");
        workoutEdit.setPlace("indoors");
        workoutRepo.save(workoutEdit);

        Workout editedW = workoutRepo.findById(workout.getWorkoutid()).orElseThrow();
        assertThat(editedW.getLocation()).isEqualTo("BK Pasila");
        assertThat(editedW.getPlace()).isEqualTo("indoors");
        assertThat(editedW.getNotes()).isEqualTo("good");
    }

    @Test
    public void workoutHasRightUser() {
        Workout testworkout = workoutRepo.findById(workout.getWorkoutid()).orElseThrow();
        assertThat(testworkout.getUser().getUsername()).isEqualTo("name");
    }

}
