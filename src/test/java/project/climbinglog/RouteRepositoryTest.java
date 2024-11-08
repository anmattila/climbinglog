package project.climbinglog;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;
import project.climbinglog.domain.Route;
import project.climbinglog.domain.RouteRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;
import project.climbinglog.domain.Type;

@SpringBootTest
public class RouteRepositoryTest {

    @Autowired
    private AppUserRepository userRepo;
    @Autowired 
    private WorkoutRepository workoutRepo;
    @Autowired
    private RouteRepository routeRepo;
    
    private Workout workout;
    private Route route;

    @BeforeEach
    public void setUp() {
        AppUser testuser = new AppUser("name", "word", "USER");
        userRepo.save(testuser);
        workout = new Workout(LocalDate.of(2024, 01, 01), "Helsinki", "outdoors", "good", testuser);
        route = new Route(Type.BOULDER, "6", 2);
        workout.addRoute(route);
        workoutRepo.save(workout);
    }

    @AfterEach
    public void cleanUp() {
        routeRepo.deleteAll();
        workoutRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    public void createRoute() {
        assertThat(route.getRouteid()).isNotNull();
    }

    @Test
    public void deleteRoute() {
        routeRepo.deleteById(route.getRouteid());
        assertThat(routeRepo.findById(route.getRouteid())).isEmpty();
    }
    
    @Test
    public void findAllRoutesByWorkout(AppUser testuser2) {
        workout = new Workout(LocalDate.of(2024, 02, 02), "Pasila", "indoors", "good", testuser2);
        Long workoutid = (long) 1;
        List<Route> routes = routeRepo.findAllByWorkoutid(workoutid);
        assertThat(routes).isNotNull();
    }

}
