package project.climbinglog;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.climbinglog.web.RouteController;
import project.climbinglog.web.WorkoutController;
import project.climbinglog.web.LogController;

@SpringBootTest
public class ClimbinglogApplicationTests {

	@Autowired
	private WorkoutController workoutController;
	@Autowired
	private RouteController routeController;
	@Autowired
	private LogController logController;

	@Test
	public void workoutLoads() {
		assertThat(workoutController).isNotNull();
	}

	@Test
	public void routeLoads() {
		assertThat(routeController).isNotNull();
	}

	@Test
	public void logLoads() {
		assertThat(logController).isNotNull();
	}

}
