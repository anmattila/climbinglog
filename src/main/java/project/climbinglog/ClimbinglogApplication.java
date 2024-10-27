package project.climbinglog;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.climbinglog.domain.Climb;
import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@SpringBootApplication
public class ClimbinglogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbinglogApplication.class, args);
	}

	@Bean
	public CommandLineRunner logdemo(WorkoutRepository reposWorkout, ClimbRepository reposClimb) {
		return (args) -> {

			//SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
			Workout demoW1 = new Workout(LocalDate.of(2024,10,23), "Ristikko", "indoors", "versitale session");
			
			Climb demoC1 = new Climb("boulder", "5", "");
			Climb demoC2 = new Climb("top rope", "7A", "");
			
			demoW1.addClimb(demoC1);
			demoW1.addClimb(demoC2);
			reposWorkout.save(demoW1);
					
			for (Climb climb : reposClimb.findAll()) {
				System.out.println(climb.toString());
			}
			for (Workout workout : reposWorkout.findAll()) {
				System.out.println(workout.toString());
			}
		};
	}

}
 